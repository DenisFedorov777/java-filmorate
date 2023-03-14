package ru.yandex.practicum.filmorate.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
@Data
public class UserController {

    private final Map<Long, User> userStorage = new HashMap<>();
    private Long counter = 1L;

    @PostMapping
    public User addUser(@Valid @RequestBody User user) throws ValidationException {
        UserValidator.validate(user);
        if (userStorage.values().stream().noneMatch(u -> u.getLogin().equals(user.getLogin()))) {
            user.setId(counter++);
            userStorage.put(user.getId(), user);
            log.info("Пользователь '{}' успешно добавлен", user.getLogin());
        }
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        if (userStorage.containsKey(user.getId())) {
            userStorage.put(user.getId(), user);
            log.info("Данные пользователя '{}' успешно изменены.", user.getLogin());
            return user;
        } else {
            log.error("Данные пользователя '{}' не изменены.", user.getName());
            throw new ValidationException("Ошибка изменения данных пользователя.");
        }
    }

    @GetMapping
    public List<User> findAll() {
        log.debug("Текущее количество пользователей: {}", userStorage.size());
        return new ArrayList<>(userStorage.values());
    }
}
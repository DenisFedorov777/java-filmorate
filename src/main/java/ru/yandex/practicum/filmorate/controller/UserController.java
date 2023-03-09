package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.*;

@RestController
@Slf4j
public class UserController {

    private final Map<Long, User> userStorage = new HashMap();
    private Long counter = 1L;

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        user.setId(counter++);
        userStorage.put(user.getId(), user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@Valid @RequestBody User user) {
        if (userStorage.containsKey(user.getId())) {
            userStorage.put(user.getId(), user);
            return user;
        } else {
            throw new  ValidationException("Не тот код");
        }
    }

    @GetMapping("/users")
    public Set<User> findAll() {
        log.debug("Текущее количество пользователей: {}", userStorage.size());
        return new HashSet<>(userStorage.values());
    }
}
package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public class UserValidator {
    public static void validate(User user) throws ValidationException {
        if (user.getName() == null || user.getName().isBlank()) {
            log.info("Если имя не заполнено - используется логин.");
            user.setName(user.getLogin());
        }
        if (user.getLogin() == null || user.getLogin().equals("") || user.getLogin().contains(" ")) {
            log.info("Логин не может быть пустым");
            throw new ValidationException("Логин не может быть пустым");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            log.info("Email не может быть пустым.");
            throw new ValidationException("Email не может быть пустым.");
        }
        if (!user.getEmail().contains("@")) {
            log.info("Email должен содержать символ @.");
            throw new ValidationException("Email должен содержать символ @.");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.info("Некорректно указан возраст.");
            throw new ValidationException("Некорректно указан возраст.");
        }
    }
}

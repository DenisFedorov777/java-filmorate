package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.Month;

@Slf4j
public class FilmValidator {
    private static final LocalDate RELIES_DATE_FIRST_FILM = LocalDate.of(1895, Month.DECEMBER, 28);
    public static boolean validate(Film film) throws ValidationException {
        if (film.getName() == null || film.getName().equals("")) {
            log.error("Название фильма не может быть пустым");
            throw new ValidationException("Название фильма пустое.");
        }
        if (film.getDescription().length() > 200) {
            log.info("Описание слишком длинное");
            throw new ValidationException("Описание больше 200 символов.");
        }
        if (film.getDuration() <= 0) {
            log.info("Длительность фильма должна быть положительной.");
            throw new ValidationException("Длительность фильма меньше 1 минуты.");
        }
        if (film.getReleaseDate().isBefore(RELIES_DATE_FIRST_FILM)) {
            log.info("Дата выхода фильма в показ позже 28.12.1985");
            throw new ValidationException("Дата выхода фильма в показ позже 28.12.1985");
        }
        return false;
    }
}

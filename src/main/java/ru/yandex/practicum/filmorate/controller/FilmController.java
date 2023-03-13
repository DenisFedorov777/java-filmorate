package ru.yandex.practicum.filmorate.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/films")
@RestController
@Slf4j
@Data
public class FilmController {
    private Long counter = 1L;

    private final Map<Long, Film> filmStorage = new HashMap<>();

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) throws ValidationException {
        FilmValidator.validate(film);
        if (filmStorage.values().stream().noneMatch(u -> u.getName().equals(film.getName()))) {
            film.setId(counter++);
            filmStorage.put(film.getId(), film);
            log.error("Фильм '{}' добавлен", film.getName());
            return film;
        } else {
            log.error("Фильм '{}' не добавлен.", film.getName());
            throw new RuntimeException("Ошибка добавления фильма");
        }
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (filmStorage.containsKey(film.getId())) {
            filmStorage.put(film.getId(), film);
            log.info("Фильм '{}' обновлен", film.getName());
            return film;
        } else {
            log.error("Фильм '{}' не найден.", film.getName());
            throw new RuntimeException("В хранилище нет такого фильма.");
        }
    }

    @GetMapping
    public List<Film> getFilm() {
        log.debug("Текущее количество фильмов: {}", filmStorage.size());
        return new ArrayList<>(filmStorage.values());
    }
}
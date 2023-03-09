package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
public class FilmController {
    private final LocalDate dateEnd = LocalDate.of(1895, Month.DECEMBER, 28);
    private Long counter = 1L;

    private Map<Long, Film> filmStorage = new HashMap<>();

    @PostMapping("/films")
    public Film addFilm(@Valid @RequestBody Film film) {
        if (film.getReleaseDate().isAfter(dateEnd)) {
            film.setId(counter++);
            filmStorage.put(film.getId(), film);
        } else {
            throw new ValidationException("Ошибка добавления фильма.");
        }
        return film;
    }

    @PutMapping("/films")
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (film.getReleaseDate().isAfter(dateEnd) && filmStorage.containsKey(film.getId())) {
            filmStorage.put(film.getId(), film);
        } else {
            throw new ValidationException("Ошибка обновления данных фильма.");
        }
        return film;
    }

    @GetMapping("/films")
    public Set<Film> getFilm() {
        log.debug("Текущее количество фильмов: {}", filmStorage.size());
        return new HashSet<>(filmStorage.values());
    }
}
package ru.yandex.practicum.filmorate.storage.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> storage = new HashMap<>();

    private Long nextId = 1L;

    private Long generateId() {
        return nextId++;
    }

    @Override
    public Film addFilm(final Film film) {
        film.setId(generateId());
        storage.put(film.getId(), film);
        return film;
    }

    @Override
    public Optional<Film> getFilm(final Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteFilm(final Long id) {
        storage.remove(id);
    }

    @Override
    public Film updateFilm(final Film film) {
        storage.put(film.getId(), film);
        return film;
    }

    @Override
    public Collection<Film> getAllFilms() {
        return storage.values();
    }
}
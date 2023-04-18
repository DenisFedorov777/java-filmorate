package ru.yandex.practicum.filmorate.storage.impl.memory;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmDao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryFilmStorage implements FilmDao {

    private final Map<Long, Film> storage = new HashMap<>();

    private Long nextId = 1L;

    private Long generateId() {
        return nextId++;
    }

    @Override
    public Film create(final Film film) {
        film.setId(generateId());
        storage.put(film.getId(), film);
        return film;
    }

    @Override
    public Optional<Film> findById(final Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(final Long id) {
        storage.remove(id);
    }

    @Override
    public Film update(final Film film) {
        storage.put(film.getId(), film);
        return film;
    }

    @Override
    public Collection<Film> findAll() {
        return storage.values();
    }
}
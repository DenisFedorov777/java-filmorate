package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmDao {

    Film create(Film film);

    Optional<Film> findById(Long id);

    void delete(Long id);

    Film update(Film film);

    Collection<Film> findAll();
}
package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmStorage {

    Film addFilm(Film film);

    Optional<Film> getFilm(Long id);

    void deleteFilm(Long id);

    Film updateFilm(Film film);

    Collection<Film> getAllFilms();
}
package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;
import java.util.Optional;

public interface FilmGenreDao {

    Optional<List<Genre>> findByFilmId(Long id);

    void updateFilmGenre(Long filmId, Long genreId);

    void deleteAllGenresByFilmId(Long filmId);
}
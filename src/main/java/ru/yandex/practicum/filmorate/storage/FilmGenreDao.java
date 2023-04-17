package ru.yandex.practicum.filmorate.storage;

import java.util.List;
import java.util.Optional;
import ru.yandex.practicum.filmorate.model.Genre;

public interface FilmGenreDao {

  Optional<List<Genre>> findByFilmId(Long id);

  void updateFilmGenre(Long filmId, Long genreId);
  void deleteAllGenresByFilmId(Long filmId);
}

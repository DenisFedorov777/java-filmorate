package ru.yandex.practicum.filmorate.storage.impl.h2;

import java.util.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.FilmGenreDao;
import ru.yandex.practicum.filmorate.storage.GenreDao;
import ru.yandex.practicum.filmorate.storage.impl.h2.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.storage.impl.h2.sql.Constants;

@Repository
public class GenreRepository implements GenreDao, FilmGenreDao {
  private final JdbcTemplate jdbcTemplate;
  private final GenreMapper genreMapper;

  public GenreRepository(JdbcTemplate jdbcTemplate, GenreMapper genreMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.genreMapper = genreMapper;
  }

  @Override
  public Collection<Genre> findAll() {
    return jdbcTemplate.query(Constants.SELECT_GENRE_ALL, genreMapper);
  }

  @Override
  public Optional<Genre> findById(Long id) {
    try {

      return Optional.ofNullable(
          jdbcTemplate.queryForObject(Constants.SELECT_GENRE_BY_ID, genreMapper, id));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<Genre>> findByFilmId(Long id) {
    try {
      final List<Genre> genreList =
          jdbcTemplate.query(Constants.SELECT_GENRES_BY_FILM_ID, genreMapper, id);

      return Optional.of(genreList);

    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public void updateFilmGenre(Long filmId, Long genreId) {
    jdbcTemplate.update(Constants.INSERT_FILMS_GENRES, filmId, genreId);
  }

  @Override
  public void deleteAllGenresByFilmId(Long filmId) {
    jdbcTemplate.update(Constants.DELETE_FILMS_GENRES_BY_FILM_ID, filmId);
  }
}

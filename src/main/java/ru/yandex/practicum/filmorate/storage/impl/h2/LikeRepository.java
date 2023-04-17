package ru.yandex.practicum.filmorate.storage.impl.h2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.storage.LikeDao;
import ru.yandex.practicum.filmorate.storage.impl.h2.sql.Constants;

@Repository
public class LikeRepository implements LikeDao {
  private final JdbcTemplate jdbcTemplate;

  public LikeRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void create(Long filmId, Long userId) {
    jdbcTemplate.update(Constants.INSERT_LIKE_BY_FILM_ID_AND_USER_ID, filmId, userId);
  }

  @Override
  public void delete(Long filmId, Long userId) {
    jdbcTemplate.update(Constants.DELETE_LIKE_BY_FILM_ID_AND_USER_ID, filmId, userId);
  }

  @Override
  public Long selectCountLikeByFilmId(Long filmId) {
    return jdbcTemplate.queryForObject(Constants.SELECT_LIKE_COUNT_BY_FILM_ID, Long.class,filmId);
  }
}

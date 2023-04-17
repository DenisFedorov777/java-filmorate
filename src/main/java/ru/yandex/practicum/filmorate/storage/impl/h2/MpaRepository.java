package ru.yandex.practicum.filmorate.storage.impl.h2;

import java.util.Collection;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaDao;
import ru.yandex.practicum.filmorate.storage.impl.h2.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.storage.impl.h2.sql.Constants;

@Repository
public class MpaRepository implements MpaDao {
  private final JdbcTemplate jdbcTemplate;
  private final MpaMapper mpaMapper;

  public MpaRepository(JdbcTemplate jdbcTemplate, MpaMapper mpaMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.mpaMapper = mpaMapper;
  }

  @Override
  public Collection<Mpa> findAll() {
    return jdbcTemplate.query(Constants.SELECT_MPA_ALL, mpaMapper);
  }

  @Override
  public Optional<Mpa> findById(Long id) {
    try {
      return Optional.ofNullable(
          jdbcTemplate.queryForObject(Constants.SELECT_MPA_BY_ID, mpaMapper, id));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Mpa> findByFilmId(Long id) {
    try {
      return Optional.ofNullable(
          jdbcTemplate.queryForObject(Constants.SELECT_MPA_BY_FILM_ID, mpaMapper, id));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }
}

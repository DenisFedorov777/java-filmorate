package ru.yandex.practicum.filmorate.storage.impl.h2.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Mpa;

@Component
public class MpaMapper implements RowMapper<Mpa> {
  @Override
  public Mpa mapRow(ResultSet rs, int rowNum) throws SQLException {
    return Mpa.builder().id(rs.getLong("id")).name(rs.getString("name")).build();
  }
}

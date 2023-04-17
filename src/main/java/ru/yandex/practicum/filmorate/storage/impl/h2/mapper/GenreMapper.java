package ru.yandex.practicum.filmorate.storage.impl.h2.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Genre;

@Component
public class GenreMapper implements RowMapper<Genre> {
  @Override
  public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
    return Genre.builder().id(rs.getLong("id")).name(rs.getString("name")).build();
  }
}

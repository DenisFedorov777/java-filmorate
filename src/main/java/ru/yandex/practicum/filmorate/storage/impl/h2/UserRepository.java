package ru.yandex.practicum.filmorate.storage.impl.h2;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserDao;
import ru.yandex.practicum.filmorate.storage.impl.h2.mapper.UserMapper;
import ru.yandex.practicum.filmorate.storage.impl.h2.sql.Constants;

@Repository("userRepository")
public class UserRepository implements UserDao {

  private final JdbcTemplate jdbcTemplate;
  private final UserMapper userMapper;

  public UserRepository(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.userMapper = userMapper;
  }

  @Override
  public User create(User user) {
    KeyHolder kh = new GeneratedKeyHolder();

    final int updated =
        jdbcTemplate.update(
            con -> {
              final PreparedStatement ps =
                  con.prepareStatement(Constants.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
              ps.setString(1, user.getEmail());
              ps.setString(2, user.getLogin());
              ps.setString(3, user.getName());
              ps.setDate(4, Date.valueOf(user.getBirthday()));
              return ps;
            },
            kh);

    if (updated == 0) {
      return null;
    }
    final Long userId = kh.getKeyAs(Long.class);
    user.setId(userId);

    return user;
  }

  @Override
  public User update(User user) {
    jdbcTemplate.update(
        Constants.UPDATE_USER_BY_ID,
        user.getEmail(),
        user.getLogin(),
        user.getName(),
        user.getBirthday(),
        user.getId());
    return user;
  }

  @Override
  public Optional<User> findById(Long id) {
    try {
      return Optional.ofNullable(
          jdbcTemplate.queryForObject(Constants.SELECT_USER_BY_ID, userMapper, id));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Deprecated(since = "h2 implementations")
  @Override
  public void deleteById(Long id) {
    jdbcTemplate.update(Constants.DELETE_USER_BY_ID, id);
  }

  @Override
  public Collection<User> findAll() {
    return jdbcTemplate.query(Constants.SELECT_USER_ALL, userMapper);
  }
}

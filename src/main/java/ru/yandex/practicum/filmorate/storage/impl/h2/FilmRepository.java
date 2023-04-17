package ru.yandex.practicum.filmorate.storage.impl.h2;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmDao;
import ru.yandex.practicum.filmorate.storage.impl.h2.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.storage.impl.h2.sql.Constants;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;

@Repository("filmRepository")
public class FilmRepository implements FilmDao {

    private final JdbcTemplate jdbcTemplate;
    private final FilmMapper filmMapper;

    public FilmRepository(JdbcTemplate jdbcTemplate, FilmMapper filmMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.filmMapper = filmMapper;
    }

    @Override
    public Film create(Film film) {

        KeyHolder kh = new GeneratedKeyHolder();

        final int updated =
                jdbcTemplate.update(
                        con -> {
                            final PreparedStatement ps =
                                    con.prepareStatement(Constants.INSERT_FILM, Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, film.getName());
                            ps.setString(2, film.getDescription());
                            ps.setDate(3, Date.valueOf(film.getReleaseDate()));
                            ps.setLong(4, film.getDuration());
                            ps.setLong(5, film.getMpa().getId());
                            return ps;
                        },
                        kh);

        if (updated == 0) {
            return null;
        }

        final Long filmId = kh.getKeyAs(Long.class);
        film.setId(filmId);

        return film;
    }

    @Override
    public Optional<Film> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(Constants.SELECT_FILMS_BY_FILM_ID, filmMapper, id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Deprecated(since = "h2 implementations")
    @Override
    public void delete(Long id) {
        jdbcTemplate.update(Constants.DELETE_FILM_BY_ID, id);
    }

    @Override
    public Film update(Film film) {
        int update =
                jdbcTemplate.update(
                        Constants.UPDATE_FILM_BY_ID,
                        film.getName(),
                        film.getDescription(),
                        film.getReleaseDate(),
                        film.getDuration(),
                        film.getMpa().getId(),
                        film.getId());

        if (update == 0) {
            return null;
        }

        return film;
    }

    @Override
    public Collection<Film> findAll() {
        return jdbcTemplate.query(Constants.SELECT_ALL_FILMS, filmMapper);
    }
}

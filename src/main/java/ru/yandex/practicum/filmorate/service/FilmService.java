package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.FilmDao;
import ru.yandex.practicum.filmorate.storage.FilmGenreDao;
import ru.yandex.practicum.filmorate.storage.GenreDao;
import ru.yandex.practicum.filmorate.storage.MpaDao;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmDao filmDao;
    private final LikeService likeService;
    private final MpaDao mpaDao;
    private final GenreDao genreDao;
    private final FilmGenreDao filmGenreDao;

    public FilmService(
            @Qualifier("filmRepository") FilmDao filmDao,
            LikeService likeService,
            MpaDao mpaDao,
            GenreDao genreDao,
            FilmGenreDao filmGenreDao) {
        this.filmDao = filmDao;
        this.likeService = likeService;
        this.mpaDao = mpaDao;
        this.genreDao = genreDao;
        this.filmGenreDao = filmGenreDao;
    }

    public Film addFilm(final Film film) {
        final Film addedFilm = filmDao.create(film);

        if (film.getMpa().getId() != null) {
            setMpaInCurrentFilm(film.getId(), addedFilm);
        }

        if (!film.getGenres().isEmpty()) {
            final Set<Genre> genres = film.getGenres();

            filmGenreDao.deleteAllGenresByFilmId(film.getId());

            genres.forEach((genre) -> filmGenreDao.updateFilmGenre(film.getId(), genre.getId()));
            filmGenreDao
                    .findByFilmId(film.getId())
                    .ifPresent(genreList -> addedFilm.setGenres(new HashSet<>(genreList)));
        }
        return addedFilm;
    }

    public Film updateFilm(final Film film) {
        filmDao
                .findById(film.getId())
                .orElseThrow(() -> new NotFoundException("Фильм не найден по id"));

        final Film updateFilm = filmDao.update(film);

        if (film.getMpa().getId() != null) {
            setMpaInCurrentFilm(film.getId(), updateFilm);
        }

        if (!film.getGenres().isEmpty()) {
            final Set<Genre> genres = film.getGenres();

            filmGenreDao.deleteAllGenresByFilmId(film.getId());

            genres.forEach((genre) -> filmGenreDao.updateFilmGenre(film.getId(), genre.getId()));
            filmGenreDao
                    .findByFilmId(film.getId())
                    .ifPresent(genreList -> updateFilm.setGenres(new HashSet<>(genreList)));
        } else {
            filmGenreDao.deleteAllGenresByFilmId(film.getId());
        }

        return updateFilm;
    }

    private void setMpaInCurrentFilm(Long filmId, Film updateFilm) {
        mpaDao.findByFilmId(filmId).ifPresent(updateFilm::setMpa);
    }

    public Collection<Film> findAllFilms() {
        final Collection<Film> films = filmDao.findAll();
        films.forEach(this::extractFilmProperties);
        return films;
    }

    public Film findFilmById(final Long id) {
        final Film film =
                filmDao.findById(id).orElseThrow(() -> new NotFoundException("Фильм не найден по id"));

        extractFilmProperties(film);
        return film;
    }

    public Collection<Film> findPopularFilms(final int count) {
        final List<Film> collect =
                findAllFilms().stream()
                        .sorted(Comparator.comparingLong(Film::getLikes).reversed())
                        .limit(count)
                        .collect(Collectors.toList());
        return collect;
    }

    private void extractFilmProperties(Film film) {
        film.setLikes(likeService.selectCountLikeByFilmId(film.getId()));

        filmGenreDao
                .findByFilmId(film.getId())
                .ifPresent(genresList -> film.setGenres(new HashSet<>(genresList)));

        setMpaInCurrentFilm(film.getId(), film);
    }
}
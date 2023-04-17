package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmStorage filmStorage;

    private final UserStorage userStorage;

    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film findFilmById(final Long id) {
        return filmStorage.getFilm(id)
                .orElseThrow(() -> new NotFoundException("Фильм не найден по id"));
    }

    public Film addFilm(final Film film) {
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(final Film film) {
        filmStorage.getFilm(film.getId())
                .orElseThrow(() -> new NotFoundException("Фильм не найден по id"));
        return filmStorage.updateFilm(film);
    }

    public void deleteFilm(final Long id) {
        filmStorage.getFilm(id)
                .orElseThrow(() -> new NotFoundException("Фильм не найден по id"));
        filmStorage.deleteFilm(id);
    }

    public Collection<Film> findAllFilms() {
        return filmStorage.getAllFilms();
    }

    public void addLike(final Long filmId, final Long userId) {
        Film film = filmStorage.getFilm(filmId)
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));

        User user = userStorage.findUserById(userId).orElseThrow(() -> new NotFoundException(String
                .format("Пользователь с id %s не найден", userId)));
        film.addLike(user.getId());
    }

    public void deleteLike(final Long filmId, final Long userId) {
        Film film = filmStorage.getFilm(filmId)
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));
        User user = userStorage.findUserById(userId).orElseThrow(() -> new NotFoundException(String
                .format("Пользователь с id %s не найден", userId)));
        film.deleteLike(user.getId());
        filmStorage.updateFilm(film);
    }

    public Collection<Film> findPopularFilms(final int count) {
        final Collection<Film> allFilms = filmStorage.getAllFilms();
        final Comparator<Film> filmComparator = new Comparator<>() {
            @Override
            public int compare(Film o1, Film o2) {
                return o2.getLikes().size() - o1.getLikes().size();
            }
        };
        return allFilms.stream()
                .sorted(filmComparator)
                .limit(count)
                .collect(Collectors.toList());
    }
}
package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.FilmDao;
import ru.yandex.practicum.filmorate.storage.LikeDao;
import ru.yandex.practicum.filmorate.storage.UserDao;

@Service
public class LikeService {

    private final LikeDao likeDao;
    private final FilmDao filmDao;
    private final UserDao userDao;

    public LikeService(
            LikeDao likeDao,
            @Qualifier("filmRepository") FilmDao filmDao,
            @Qualifier("userRepository") UserDao userDao) {
        this.likeDao = likeDao;
        this.filmDao = filmDao;
        this.userDao = userDao;
    }

    public void create(Long filmId, Long userId) {
        filmDao.findById(filmId).orElseThrow(() -> new NotFoundException("Нет такого фильма"));
        userDao.findById(userId).orElseThrow(() -> new NotFoundException("Нет такого пользователя"));
        likeDao.create(filmId, userId);
    }

    public void delete(Long filmId, Long userId) {
        filmDao.findById(filmId).orElseThrow(() -> new NotFoundException("Нет такого фильма"));
        userDao.findById(userId).orElseThrow(() -> new NotFoundException("Нет такого пользователя"));
        likeDao.delete(filmId, userId);
    }

    public Long selectCountLikeByFilmId(Long filmId) {
        return likeDao.selectCountLikeByFilmId(filmId);
    }
}
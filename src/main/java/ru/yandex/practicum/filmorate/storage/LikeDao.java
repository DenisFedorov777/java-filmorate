package ru.yandex.practicum.filmorate.storage;


public interface LikeDao {

  void create(Long filmId, Long userId);

  void delete(Long filmId, Long userId);

  Long selectCountLikeByFilmId(Long filmId);

}

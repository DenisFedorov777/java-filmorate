package ru.yandex.practicum.filmorate.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.GenreDao;

@Slf4j
@Service
public class GenreService {

  private final GenreDao genreDao;

  public GenreService(GenreDao genreDao) {
    this.genreDao = genreDao;
  }

  public Collection<Genre> findAll() {
    return genreDao.findAll().stream()
        .sorted((Comparator.comparing(Genre::getId)))
        .collect(Collectors.toList());
  }

  public Genre findById(Long id) {
    return genreDao
        .findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Такого жанра с id: %s", id)));
  }
}

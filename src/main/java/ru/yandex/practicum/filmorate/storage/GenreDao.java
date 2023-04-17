package ru.yandex.practicum.filmorate.storage;

import java.util.Collection;
import java.util.Optional;
import ru.yandex.practicum.filmorate.model.Genre;

public interface GenreDao {

  Collection<Genre> findAll();

  Optional<Genre> findById(Long id);
}

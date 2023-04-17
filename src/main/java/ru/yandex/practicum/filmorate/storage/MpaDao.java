package ru.yandex.practicum.filmorate.storage;

import java.util.Collection;
import java.util.Optional;
import ru.yandex.practicum.filmorate.model.Mpa;

public interface MpaDao {

  Collection<Mpa> findAll();

  Optional<Mpa> findById(Long id);

  Optional<Mpa> findByFilmId(Long id);
}

package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.Optional;

public interface MpaDao {

    Collection<Mpa> findAll();

    Optional<Mpa> findById(Long id);

    Optional<Mpa> findByFilmId(Long id);
}
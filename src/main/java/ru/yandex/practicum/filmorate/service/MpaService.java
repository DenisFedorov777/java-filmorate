package ru.yandex.practicum.filmorate.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.impl.h2.MpaRepository;

@Slf4j
@Service
public class MpaService {

  private final MpaRepository mpaRepository;

  public MpaService(MpaRepository mpaRepository) {
    this.mpaRepository = mpaRepository;
  }

  public Collection<Mpa> findAll() {
    return mpaRepository.findAll().stream()
        .sorted(Comparator.comparing(Mpa::getId))
        .collect(Collectors.toList());
  }

  public Mpa findById(Long id) {
    return mpaRepository
        .findById(id)
        .orElseThrow(
            () -> new NotFoundException(String.format("Такого жанра с id: %s не найдено", id)));
  }
}

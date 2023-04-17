package ru.yandex.practicum.filmorate.controller;

import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.MpaService;

@RestController
@RequestMapping("/mpa")
public class MpaController {

  private final MpaService mpaService;

  public MpaController(MpaService mpaService) {
    this.mpaService = mpaService;
  }

  @GetMapping
  public ResponseEntity<Collection<Mpa>> getAll() {
    return ResponseEntity.ok(mpaService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mpa> getById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(mpaService.findById(id));
  }
}

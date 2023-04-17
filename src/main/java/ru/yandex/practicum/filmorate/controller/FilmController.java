package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<Film> create(@Valid @RequestBody Film film) {
        return ResponseEntity.ok(filmService.addFilm(film));
    }

    @PutMapping
    public ResponseEntity<Film> update(@Valid @RequestBody Film film) {
        return ResponseEntity.ok(filmService.updateFilm(film));
    }

    @GetMapping
    public ResponseEntity<Collection<Film>> findAllFilms() {
        return ResponseEntity.ok(filmService.findAllFilms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> findFilmById(@PathVariable Long id) {
        return ResponseEntity.ok(filmService.findFilmById(id));
    }

    @PutMapping("/{id}/like/{userId}")
    public ResponseEntity<Void> addLike(@PathVariable Long id, @PathVariable Long userId) {
        filmService.addLike(id, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/like/{userId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id, @PathVariable Long userId) {
        filmService.deleteLike(id, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/popular")
    public ResponseEntity<Collection<Film>> findPopularFilms(
            @RequestParam(name = "count", defaultValue = "10") int count) {
        return ResponseEntity.ok(filmService.findPopularFilms(count));
    }
}
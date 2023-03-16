package ru.yandex.practicum.filmorate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmValidatorTest {

    private Film.FilmBuilder builder;
    private Film film;

    @BeforeEach
    public void beforeEch() {
        builder = Film.builder();
    }

    @Test
    public void shouldThrowExceptionWhenReleaseDateIsBefore28_12_1895() {
        film = builder.name("Терминатор").description("I'l be back!")
                .releaseDate(LocalDate.of(1895, 12, 27)).duration(194L).build();
        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        film = builder.name("").description("I'l be back!")
                .releaseDate(LocalDate.of(2000, 5, 5)).duration(194L).build();
        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    public void shouldNotThrowExceptionWhenReleaseDateIs28_12_1895() {
        film = builder.name("Терминатор").description("I'l be back!")
                .releaseDate(LocalDate.of(1895, 12, 28)).duration(194L).build();
        assertDoesNotThrow(() -> FilmValidator.validate(film));
    }

    @Test
    public void shouldSuccessfullyValidateCorrectFilm() {
        film = builder.name("Терминатор").description("I'l be back!")
                .releaseDate(LocalDate.of(1992, 3, 15)).duration(180L).build();
        assertDoesNotThrow(() -> FilmValidator.validate(film));
    }

    @Test
    public void shouldThrowExceptionWhenDurationIsNegative() {
        film = builder.name("Терминатор").description("I'l be back!")
                .releaseDate(LocalDate.of(1997, 12, 25)).duration(-1L).build();
        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    public void shouldNotThrowExceptionWhenDurationIsPositive() {
        film = builder.name("Чебурашка").description("I'l be back!")
                .releaseDate(LocalDate.of(1995, 10, 1)).duration(1L).build();
        assertDoesNotThrow(() -> FilmValidator.validate(film));
    }

    @Test
    public void shouldThrowExceptionWhenDurationIsZero() {
        film = builder.name("Терминатор").description("I'l be back!")
                .releaseDate(LocalDate.of(1997, 12, 13)).duration(0L).build();
        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    public void shouldThrowExceptionWhenDescriptionIsLonger200Symbols() {
        film = builder.name("Терминатор").description(StringUtils.repeat("a", 201))
                .releaseDate(LocalDate.of(1997, 12, 17)).duration(1L).build();
        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    public void shouldNotThrowExceptionWhenDescriptionIsNotLonger200Symbols() {
        film = builder.name("Терминатор").description(StringUtils.repeat("a", 200))
                .releaseDate(LocalDate.of(1997, 12, 19)).duration(1L).build();
        assertDoesNotThrow(() -> FilmValidator.validate(film));
    }
}
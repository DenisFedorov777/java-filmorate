package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.model.constraints.FilmReleaseDateConstrain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Film {

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @Setter
    @Builder.Default
    Long likes = 0L;
    @Setter
    Long id;
    @NotBlank
    String name;

    @Size(min = 1, max = 200)
    String description;

    @NotNull
    @FilmReleaseDateConstrain
    LocalDate releaseDate;
    @Positive
    Long duration;
    @EqualsAndHashCode.Exclude
    @Setter
    @Builder.Default
    Mpa mpa = new Mpa();
    @EqualsAndHashCode.Exclude
    @Setter
    @Builder.Default
    Set<Genre> genres = new HashSet<>();
}
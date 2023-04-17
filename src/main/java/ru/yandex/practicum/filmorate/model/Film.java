package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
public class Film {

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @Setter
    @Builder.Default
    private Long likes = 0L;
    @Setter
    private Long id;
    @NotBlank
    private String name;

    @Size(min = 1, max = 200)
    private String description;

    @NotNull
    @FilmReleaseDateConstrain
    private LocalDate releaseDate;
    @Positive
    private Long duration;
    @EqualsAndHashCode.Exclude
    @Setter
    @Builder.Default
    private Mpa mpa = new Mpa();
    @EqualsAndHashCode.Exclude
    @Setter
    @Builder.Default
    private Set<Genre> genres = new HashSet<>();
}
package ru.yandex.practicum.filmorate.model;

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

    private final Set<Long> likes = new HashSet<>();
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

    public void addLike(final Long userId) {
        likes.add(userId);
    }

    public void deleteLike(final Long userId) {
        likes.remove(userId);
    }
}
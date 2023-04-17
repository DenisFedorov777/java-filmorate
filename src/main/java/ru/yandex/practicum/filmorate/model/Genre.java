package ru.yandex.practicum.filmorate.model;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Genre {

    private Long id;
    private String name;
}
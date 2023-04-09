package ru.yandex.practicum.filmorate.model;

import lombok.*;
import ru.yandex.practicum.filmorate.model.constraints.UserNullNameConstrain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@UserNullNameConstrain
public class User {

    private final Set<Long> friends = new HashSet<>();
    @Setter
    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String login;
    private String name;
    @NotNull
    @PastOrPresent
    private LocalDate birthday;

    public void addFriend(final Long id) {
        friends.add(id);
    }

    public void deleteFriend(final Long id) {
        friends.remove(id);
    }
}
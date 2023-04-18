package ru.yandex.practicum.filmorate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.model.constraints.UserNullNameConstrain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@UserNullNameConstrain
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    final Set<Long> friends = new HashSet<>();
    @Setter
    Long id;
    @NotBlank
    @Email
    String email;
    @NotBlank
    String login;
    String name;
    @NotNull
    @PastOrPresent
    LocalDate birthday;

    public void addFriend(final Long id) {
        friends.add(id);
    }

    public void deleteFriend(final Long id) {
        friends.remove(id);
    }
}
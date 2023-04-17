package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    User update(User user);

    Collection<User> findAll();
}
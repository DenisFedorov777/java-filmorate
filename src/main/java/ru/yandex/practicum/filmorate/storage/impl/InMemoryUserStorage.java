package ru.yandex.practicum.filmorate.storage.impl;

import lombok.Builder.Default;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {

    @Default
    private final Map<Long, User> storage = new HashMap<>();
    private Long nextId = 1L;

    private Long generateId() {
        return nextId++;
    }

    @Override
    public User addUser(User user) {
        user.setId(this.generateId());
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findUserById(final Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteUser(final Long id) {
        storage.remove(id);
    }

    @Override
    public User updateUser(final User user) {
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> findAllUsers() {
        return storage.values();
    }
}
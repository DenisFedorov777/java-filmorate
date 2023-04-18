package ru.yandex.practicum.filmorate.storage.impl.memory;

import lombok.Builder.Default;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserDao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserStorage implements UserDao {

    @Default
    private final Map<Long, User> storage = new HashMap<>();
    private Long nextId = 1L;

    private Long generateId() {
        return nextId++;
    }

    @Override
    public User create(User user) {
        user.setId(this.generateId());
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(final Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(final Long id) {
        storage.remove(id);
    }

    @Override
    public User update(final User user) {
        storage.put(user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> findAll() {
        return storage.values();
    }
}
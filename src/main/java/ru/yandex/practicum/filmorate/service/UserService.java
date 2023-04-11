package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserStorage userStorage;

    public User findUserById(final Long id) {
        return userStorage
                .findUserById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Пользователь с id %s не найден", id)));
    }

    public Collection<User> findUserFriends(final Long id) {
        final User userById = userStorage
                .findUserById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        final Set<Long> gotFriendsId = userById.getFriends();
        List<User> resultUserFriends = new ArrayList<>();
        gotFriendsId.forEach(friendId -> {
            userStorage.findUserById(friendId).ifPresent(resultUserFriends::add);
        });
        return resultUserFriends;
    }

    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    public User updateUser(final User user) {
        userStorage
                .findUserById(user.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id %s не найден", user.getId())));
        return userStorage.updateUser(user);
    }

    public Collection<User> findAllUsers() {
        return userStorage.findAllUsers();
    }

    public void addFriend(Long userId, Long friendId) {
        User firstUser = userStorage.findUserById(userId)
                .orElseThrow(() -> new NotFoundException("First User not found"));
        User secondUser = userStorage.findUserById(friendId)
                .orElseThrow(() -> new NotFoundException("User Friend not found by id"));
        firstUser.addFriend(friendId);
        secondUser.addFriend(userId);
    }

    public void deleteFriend(Long userId, Long friendId) {
        User firstUser = userStorage.findUserById(userId)
                .orElseThrow(() -> new NotFoundException("First User not found"));
        User secondUser = userStorage.findUserById(friendId)
                .orElseThrow(() -> new NotFoundException("User Friend not found by id"));
        firstUser.deleteFriend(friendId);
        secondUser.deleteFriend(userId);
        userStorage.updateUser(firstUser);
        userStorage.updateUser(secondUser);
    }

    public Collection<User> findCommonFriends(final Long userId, final Long otherId) {
        User firstUser = userStorage.findUserById(userId)
                .orElseThrow(() -> new NotFoundException("First User not found"));
        User secondUser = userStorage.findUserById(otherId)
                .orElseThrow(() -> new NotFoundException("User Friend not found by id"));

        Set<Long> commonFriendsIds = new HashSet<>(firstUser.getFriends());
        commonFriendsIds.retainAll(secondUser.getFriends());

        return commonFriendsIds.stream()
                .map(userStorage::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
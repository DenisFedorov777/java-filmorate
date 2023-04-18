package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;
import ru.yandex.practicum.filmorate.storage.UserDao;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final UserDao userDao;
    private final FriendshipService friendshipService;

    public UserService(
            @Qualifier("userRepository") UserDao userDao, FriendshipService friendshipService) {
        this.userDao = userDao;
        this.friendshipService = friendshipService;
    }

    public User create(User user) {
        return userDao.create(user);
    }

    public User getUserById(final Long id) {
        return userDao
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Пользователь с id %s не найден", id)));
    }

    public User update(final User user) {
        userDao
                .findById(user.getId())
                .orElseThrow(
                        () ->
                                new NotFoundException(
                                        String.format("Пользователь с id %s не найден", user.getId())));
        return userDao.update(user);
    }

    public Collection<User> getAll() {
        return userDao.findAll().stream()
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    public Collection<User> getFriendsByUserId(final Long id) {
        userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return friendshipService.getFriendsByUserId(id);
    }

    @Transactional
    public void addFriend(Long userId, Long friendId) {
        userDao
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id='" + userId + "' not found"));
        userDao
                .findById(friendId)
                .orElseThrow(() -> new NotFoundException("User with id='" + friendId + "' not found"));

        List<User> friend = friendshipService.getFriend(userId, friendId);

        if (friend.isEmpty()) {
            friendshipService.addFriend(userId, friendId);
        }
    }

    @Transactional
    public void deleteFriend(Long userId, Long friendId) {
        friendshipService.deleteFriendship(userId, friendId);
    }

    @Transactional
    public Collection<User> findCommonFriends(final Long userId, final Long otherId) {
        return friendshipService.getCommonFriends(userId, otherId);
    }
}
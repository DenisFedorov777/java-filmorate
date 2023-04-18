package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FriendshipDao {

    void addFriend(Long userId, Long friendId);

    List<User> getFriend(Long userId, Long friendId);

    void deleteFriendship(Long userId, Long friendId);

    List<User> getFriendsByUserId(Long id);

    List<User> getCommonFriends(Long userId, Long friendId);
}
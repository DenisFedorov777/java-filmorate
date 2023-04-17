package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendshipDao;

import java.util.List;

@Service
public class FriendshipService {

    private final FriendshipDao friendshipDao;

    public FriendshipService(FriendshipDao friendshipDao) {
        this.friendshipDao = friendshipDao;
    }

    public void addFriend(Long userId, Long friendId) {
        friendshipDao.addFriend(userId, friendId);
    }

    public List<User> getFriend(Long id, Long friendId) {
        return friendshipDao.getFriend(id, friendId);
    }

    public void deleteFriendship(Long userId, Long friendId) {
        friendshipDao.deleteFriendship(userId, friendId);
    }

    public List<User> getFriendsByUserId(Long id) {
        return friendshipDao.getFriendsByUserId(id);
    }

    public List<User> getCommonFriends(Long userId, Long friendId) {
        return friendshipDao.getCommonFriends(userId, friendId);
    }
}
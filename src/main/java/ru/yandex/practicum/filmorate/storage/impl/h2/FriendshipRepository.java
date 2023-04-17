package ru.yandex.practicum.filmorate.storage.impl.h2;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendshipDao;
import ru.yandex.practicum.filmorate.storage.impl.h2.mapper.UserMapper;
import ru.yandex.practicum.filmorate.storage.impl.h2.sql.Constants;

@Repository
public class FriendshipRepository implements FriendshipDao {

    private final JdbcTemplate jdbcTemplate;

    public FriendshipRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        jdbcTemplate.update(Constants.INSERT_FRIENDSHIPS,
                userId, friendId, 0);
    }

    @Override
    public List<User> getFriend(Long userId, Long friendId) {

        return jdbcTemplate.query(
                Constants.SELECT_FRIENDSHIPS_FRIEND_BY_USER_ID,
                new UserMapper(), userId, friendId);
    }

    @Override
    public void deleteFriendship(Long userId, Long friendId) {
        jdbcTemplate.update(Constants.DELETE_FRIENDSHIPS_BY_USER_ID, userId, friendId);
    }

    @Override
    public List<User> getFriendsByUserId(Long id) {
        return jdbcTemplate.query(
                Constants.SELECT_FRIENDSHIPS_FRIENDS_BY_USER_ID,
                new UserMapper(), id);
    }

    @Override
    public List<User> getCommonFriends(Long userId, Long friendId) {

        return jdbcTemplate.query(Constants.SELECT_FRIENDSHIPS_COMMON_FRIENDS,
                new UserMapper(), userId, friendId);
    }
}

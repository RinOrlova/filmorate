package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.converter.UserConverter;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashSet;

@RequiredArgsConstructor
@Repository
public class ConnectionDbStorage {

    private final JdbcTemplate jdbcTemplate;
    private final UserConverter userConverter;

    public User addFriend(User fromUser, User toUser) {
        jdbcTemplate.update("insert into connection(user1_id, user2_id, status) VALUES(?,?,?)",
                fromUser.getId(),
                toUser.getId(),
                false
        );
        return fromUser;
    }

    public User removeFriend(User fromUser, User friendToDelete) {
        jdbcTemplate.update("delete * from connection where user1_id = ?, user2_id = ?",
                fromUser.getId(),
                friendToDelete.getId()
        );
        jdbcTemplate.update("delete * from connection where user1_id = ?, user2_id = ?",
                friendToDelete.getId(),
                fromUser.getId()
        );
        return fromUser;
    }

    public Collection<User> getFriends(User user) {
        Collection<User> friends = new HashSet<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(
                "select app_user.* from connection join app_user on connection.user2_id = app_user.id where connection.user1_id = ?",
                user.getId()
        );
        while (rowSet.next()) {
            User friend = userConverter.convert(rowSet);
            friends.add(friend);
        }
        return friends;
    }

    public Collection<User> getCommonFriends(User user1, User user2) {
        Collection<User> commonFriends = new HashSet<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(
                "select * from app_user where id in (select user2_id from connection where user1_id = ? intersect distinct select user2_id from connection where user1_id = ?) and not in ?, ?",
                user1.getId(),
                user2.getId(),
                user1.getId(),
                user2.getId()
        );
        while (rowSet.next()) {
            User commonFriend = userConverter.convert(rowSet);
            commonFriends.add(commonFriend);
        }
        return commonFriends;
    }
}

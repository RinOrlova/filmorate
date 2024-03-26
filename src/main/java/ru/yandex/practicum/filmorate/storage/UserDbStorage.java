package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.filmorate.converter.UserConverter;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDbStorage implements UserStorage{

    private final JdbcTemplate jdbcTemplate;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public User addUser(User user) {
        jdbcTemplate.update("insert into app_user(name, login, email, birthday) VALUES(?,?,?,?)",
                user.getName(),
                user.getLogin(),
                user.getEmail(),
                user.getBirthday()
                );
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        jdbcTemplate.update("update app_user set name = ?, login = ?, email = ?, birthday = ? where id = ?",
                user.getName(),
                user.getLogin(),
                user.getEmail(),
                user.getBirthday(),
                user.getId());
        return user;
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from app_user");
        Map<Integer, User> allUsers = new HashMap<>();
        while(rowSet.next()) {
            User user = userConverter.convert(rowSet);
            allUsers.put(user.getId(), user);
        }
        return allUsers;
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from app_user where id = ?", id);
        if(rowSet.next()) {
            User user = userConverter.convert(rowSet);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}

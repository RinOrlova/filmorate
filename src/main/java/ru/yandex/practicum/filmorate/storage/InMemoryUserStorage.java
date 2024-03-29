package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 0;

    @Override
    public User addUser(User user) {
        log.info("Attempt to add user {}", user);
        Integer userId = getNextValidId();
        User userWithId = user.toBuilder()
                .id(userId)
                .build();
        users.put(userWithId.getId(), userWithId);
        log.info("User with id={} successfully added", userId);
        return userWithId;
    }

    @Override
    public User updateUser(User user) {
        Integer userId = user.getId();
        if (users.containsKey(userId)) {
            log.info("Attempt to change user with id={}", userId);
            users.put(user.getId(), user);
            log.info("User with id={} successfully updated", userId);
            return user;
        } else {
            log.warn("User with id={} is not present", userId);
        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        return users;
    }

    private Integer getNextValidId() {
        nextId++;
        return nextId;
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }
}

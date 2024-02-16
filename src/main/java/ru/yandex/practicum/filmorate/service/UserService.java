package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        log.info("Attempt to add user {}", user);
        String userId = UUID.randomUUID().toString();
        User userWithId = user.toBuilder()
                .id(userId)
                .build();
        users.put(userWithId.getId(), userWithId);
        log.info("User with id={} successfully added", userId);
    }

    public void updateUser(User user) {
        String userId = user.getId();
        if (users.containsKey(userId)) {
            log.info("Attempt to change user with id={}", userId);
            users.put(user.getId(), user);
            log.info("User with id={} successfully updated", userId);
        } else {
            log.warn("User with id={} is not present", userId);
        }
    }

    public Map<String, User> getAllUsers() {
        return users;
    }
}

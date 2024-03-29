package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserStorage userStorage;

    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public Map<Integer, User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User addFriend(Integer userId, Integer friendId) {
        User user = getUserFromStorage(userId);
        User friend = getUserFromStorage(friendId);
        user.getFriendsIds().add(friendId);
        userStorage.updateUser(user);
        friend.getFriendsIds().add(userId);
        userStorage.updateUser(friend);
        return user;
    }

    public User removeFriend(Integer userId, Integer friendId) {
        User user = getUserFromStorage(userId);
        User friend = getUserFromStorage(friendId);
        user.getFriendsIds().remove(friendId);
        userStorage.updateUser(user);
        friend.getFriendsIds().remove(userId);
        userStorage.updateUser(friend);
        return user;
    }

    public Collection<User> getFriendsCollection(Integer userId) {
        User user = getUserFromStorage(userId);
        return user.getFriendsIds().stream()
                .map(this::getUserFromStorage)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Collection<User> getCommonFriends(Integer userId, Integer friendId) {
        User user = getUserFromStorage(userId);
        User friend = getUserFromStorage(friendId);
        return getCommonUsers(user, friend);
    }

    private Collection<User> getCommonUsers(User user, User friend) {
        return user.getFriendsIds().stream()
                .filter(id -> friend.getFriendsIds().contains(id))
                .map(this::getUserFromStorage)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public User getUserFromStorage(Integer id) {
        return userStorage.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}

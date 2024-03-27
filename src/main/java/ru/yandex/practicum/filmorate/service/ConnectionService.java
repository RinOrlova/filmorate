package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.ConnectionDbStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ConnectionService {

    private final ConnectionDbStorage connectionDbStorage;
    @Qualifier("userDbStorage")
    private final UserStorage userStorage;

    public User addFriend(Integer fromUserId, Integer toUserId) {
        User user1 = userStorage.getUserFromStorage(fromUserId);
        User user2 = userStorage.getUserFromStorage(toUserId);
        return connectionDbStorage.addFriend(user1, user2);
    }

    public User removeFriend(Integer fromUserId, Integer friendToDeleteId) {
        User user1 = userStorage.getUserFromStorage(fromUserId);
        User user2 = userStorage.getUserFromStorage(friendToDeleteId);
        return connectionDbStorage.removeFriend(user1, user2);
    }

    public Collection<User> getFriends(Integer userId) {
        User user = userStorage.getUserFromStorage(userId);
        return connectionDbStorage.getFriends(user);
    }

    public Collection<User> getCommonFriends(Integer user1Id, Integer user2Id) {
        User user1 = userStorage.getUserFromStorage(user1Id);
        User user2 = userStorage.getUserFromStorage(user2Id);
        return connectionDbStorage.getCommonFriends(user1, user2);
    }
}

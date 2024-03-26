package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserStorage {

    User addUser(User user);

    User updateUser(User user);

    Map<Integer, User> getAllUsers();

    Optional<User> getUserById(Integer id);

    default User getUserFromStorage(Integer id){
        return getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}

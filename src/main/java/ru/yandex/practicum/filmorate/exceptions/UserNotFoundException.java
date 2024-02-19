package ru.yandex.practicum.filmorate.exceptions;

public class UserNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "User by id=%s not found";

    public UserNotFoundException(Integer userId) {
        super(String.format(DEFAULT_MESSAGE, userId));
    }
}

package ru.yandex.practicum.filmorate.exceptions;

public abstract class AbstractNotFoundException extends RuntimeException {

    public AbstractNotFoundException(String message) {
        super(message);
    }
}

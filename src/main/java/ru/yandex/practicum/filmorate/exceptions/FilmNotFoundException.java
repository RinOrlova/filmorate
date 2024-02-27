package ru.yandex.practicum.filmorate.exceptions;

public class FilmNotFoundException extends AbstractNotFoundException {

    private static final String DEFAULT_MESSAGE = "Film by id=%s not found";

    public FilmNotFoundException(Integer filmId) {
        super(String.format(DEFAULT_MESSAGE, filmId));
    }
}

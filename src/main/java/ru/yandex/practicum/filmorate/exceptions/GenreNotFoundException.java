package ru.yandex.practicum.filmorate.exceptions;

public class GenreNotFoundException extends AbstractNotFoundException {

    public static final String DEFAULT_MESSAGE = "Genre by id=%s not found";
    public GenreNotFoundException(Integer genreId) {
        super(String.format(DEFAULT_MESSAGE, genreId));
    }
}

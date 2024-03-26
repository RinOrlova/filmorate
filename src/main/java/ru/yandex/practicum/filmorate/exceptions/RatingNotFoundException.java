package ru.yandex.practicum.filmorate.exceptions;

public class RatingNotFoundException extends AbstractNotFoundException {

    public static final String DEFAULT_MESSAGE = "Rating by id=%s not found";
    public RatingNotFoundException(Integer ratingId) {
        super(String.format(DEFAULT_MESSAGE, ratingId));
    }
}

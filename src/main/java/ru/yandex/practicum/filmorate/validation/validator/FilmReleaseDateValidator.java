package ru.yandex.practicum.filmorate.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.validation.annotation.ValidFilmReleaseDate;

import java.time.LocalDate;

public class FilmReleaseDateValidator implements ConstraintValidator <ValidFilmReleaseDate, LocalDate> {

    public static final LocalDate FIRST_FILM_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isBefore(FIRST_FILM_RELEASE_DATE);
    }
}

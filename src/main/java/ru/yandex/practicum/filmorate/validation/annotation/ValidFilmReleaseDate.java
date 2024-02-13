package ru.yandex.practicum.filmorate.validation.annotation;

import jakarta.validation.Constraint;
import ru.yandex.practicum.filmorate.validation.validator.FilmReleaseDateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = FilmReleaseDateValidator.class)
public @interface ValidFilmReleaseDate {
}

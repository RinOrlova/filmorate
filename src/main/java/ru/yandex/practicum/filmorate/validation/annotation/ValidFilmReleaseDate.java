package ru.yandex.practicum.filmorate.validation.annotation;

import ru.yandex.practicum.filmorate.validation.validator.FilmReleaseDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = FilmReleaseDateValidator.class)
public @interface ValidFilmReleaseDate {
    String message() default "The date must not be before 28.12.1895";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {
    public static final String VALID_DESCRIPTION = "Test description";
    private static final String VALID_UUID = UUID.randomUUID().toString();
    public static final String VALID_NAME = "Poor Things";
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void filmValidationPositiveCase() {
        Film film = Film.builder()
                .id(VALID_UUID)
                .description(VALID_DESCRIPTION)
                .name(VALID_NAME)
                .duration(141)
                .releaseDate(LocalDate.of(2023, 9, 1))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty());
    }

    @Test
    void filmValidationInvalidIdNegativeCase() {
        Film film = Film.builder()
                .id("")
                .description(VALID_DESCRIPTION)
                .name("Poor Things")
                .duration(141)
                .releaseDate(LocalDate.of(2023, 9, 1))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must be a valid UUID", violations.iterator().next().getMessage());
    }

    @Test
    void filmValidationInvalidDescriptionNegativeCase() {
        Film film = Film.builder()
                .id(VALID_UUID)
                .description(createLongString(201))
                .name("Poor Things")
                .duration(141)
                .releaseDate(LocalDate.of(2023, 9, 1))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("size must be between 0 and 200", violations.iterator().next().getMessage());
    }

    @Test
    void filmValidationInvalidNameNegativeCase() {
        Film film = Film.builder()
                .id(VALID_UUID)
                .description(createLongString(200))
                .name("   ")
                .duration(141)
                .releaseDate(LocalDate.of(2023, 9, 1))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void filmValidationInvalidDurationNegativeCase() {
        Film film = Film.builder()
                .id(VALID_UUID)
                .description(VALID_DESCRIPTION)
                .name(VALID_NAME)
                .duration(0)
                .releaseDate(LocalDate.of(1895, 12, 28))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    void filmValidationInvalidReleaseDateNegativeCase() {
        Film film = Film.builder()
                .id(VALID_UUID)
                .description(VALID_DESCRIPTION)
                .name(VALID_NAME)
                .duration(141)
                .releaseDate(LocalDate.of(1895, 12, 27))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("The date must not be before 28.12.1895", violations.iterator().next().getMessage());
    }

    private static String createLongString(int length) {
        return "a".repeat(Math.max(0, length));
    }

}
package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final String VALID_UUID = UUID.randomUUID().toString();
    private static final String VALID_LOGIN = "paranoid_android";
    private static final String VALID_EMAIL = "paranoid@gmail.com";
    private static final String VALID_NAME = "Thom Yorke";
    private static final User VALID_USER = User.builder()
            .id(VALID_UUID)
            .login(VALID_LOGIN)
            .email(VALID_EMAIL)
            .name(VALID_NAME)
            .birthday(LocalDate.of(1991, 12, 23))
            .build();

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void userValidationPositiveCase() {
        Set<ConstraintViolation<User>> violations = validator.validate(VALID_USER);
        assertTrue(violations.isEmpty());
    }

    @Test
    void userValidationInvalidIdNegativeCase() {
        User user = VALID_USER.toBuilder().id("").build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must be a valid UUID", violations.iterator().next().getMessage());
    }

    @Test
    void userValidationInvalidEmailNegativeCase() {
        User user = VALID_USER.toBuilder().email("@yandex.ru").build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must be a well-formed email address", violations.iterator().next().getMessage());
    }

    @Test
    void userValidationInvalidLoginNegativeCase1() {
        User user = VALID_USER.toBuilder().login("paranoid android").build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must match \"^\\S+$\"", violations.iterator().next().getMessage());
    }

    @Test
    void userValidationInvalidLoginNegativeCase2() {
        User user = VALID_USER.toBuilder().login("  ").build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
        Set<String> constraintViolationsMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
        assertEquals(Set.of("must match \"^\\S+$\"", "must not be blank"), constraintViolationsMessages);
    }

    @Test
    void userValidationInvalidBirthdayNegativeCase() {
        User user = VALID_USER.toBuilder().birthday(LocalDate.of(2026, 12, 12)).build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("must be a date in the past or in the present", violations.iterator().next().getMessage());
    }
}
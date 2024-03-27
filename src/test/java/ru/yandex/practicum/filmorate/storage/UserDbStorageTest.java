package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDbStorageTest {

    private final JdbcTemplate jdbcTemplate;

    @Test
    public void testFindUserById() {
        // Подготавливаем данные для теста
      //  User newUser = new User(1, "user@email.ru", "vanya123", "Ivan Petrov",
//                LocalDate.of(1990, 1, 1));
//        UserDbStorage userStorage = new UserDbStorage(jdbcTemplate);
//        userStorage.save(newUser);
//
//        // вызываем тестируемый метод
//        User savedUser = userStorage.get(1);
//
//        // проверяем утверждения
//        assertThat(savedUser)
//                .isNotNull() // проверяем, что объект не равен null
//                .usingRecursiveComparison()
//                .isEqualTo(newUser);
    }

}

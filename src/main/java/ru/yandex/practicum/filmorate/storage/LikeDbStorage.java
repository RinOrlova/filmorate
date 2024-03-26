package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

@RequiredArgsConstructor
@Repository
public class LikeDbStorage {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public Film addLike(Film film, User user) {
        jdbcTemplate.update("insert into film_x_user(film_id, user_id) VALUES(?,?)",
                film.getId(),
                user.getId()
        );
        return film;
    }

    @Transactional
    public Film removeLike(Film film, User user) {
        jdbcTemplate.update("delete from film_x_user where film_id = ?, user_id = ?",
                film.getId(),
                user.getId()
        );
        return film;
    }

}

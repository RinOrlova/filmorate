package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.converter.FilmConverter;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;
    private final FilmConverter filmConverter;

    @Override
    public Film addFilm(Film film) {
        return null;
    }

    @Override
    public Film updateFilm(Film film) {
        return null;
    }

    @Override
    public List<Film> getListOfAllFilms() {
        return null;
    }

    @Override
    public Optional<Film> getFilmById(Integer id) {
        return Optional.empty();
    }
}

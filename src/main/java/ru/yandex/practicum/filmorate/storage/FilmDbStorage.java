package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.filmorate.converter.FilmConverter;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;
    private final FilmConverter filmConverter;

    @Override
    @Transactional
    public Film addFilm(Film film) {
        jdbcTemplate.update("insert into film(name, description, releaseDate, duration) VALUES(?,?,?,?)",
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration()
        );
        return film;
    }

    @Override
    @Transactional
    public Film updateFilm(Film film) {
        jdbcTemplate.update("update film set name = ?, description = ?, releaseDate = ?, duration = ? where id = ?",
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getId()
        );
        return film;
    }

    @Override
    public Map<Integer, Film> getAllFilms() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from film");
        Map<Integer, Film> films = new HashMap<>();
        while (rowSet.next()) {
            Film film = filmConverter.convert(rowSet);
            films.put(film.getId(), film);
        }
        return films;
    }

    @Override
    public Optional<Film> getFilmById(Integer id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from film where id = ?", id);
        if (rowSet.next()) {
            Film film = filmConverter.convert(rowSet);
            return Optional.of(film);
        } else {
            return Optional.empty();
        }
    }
}

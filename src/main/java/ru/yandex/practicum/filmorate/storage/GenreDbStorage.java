package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.enums.GenreType;
import ru.yandex.practicum.filmorate.exceptions.GenreNotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.HashSet;

@RequiredArgsConstructor
@Repository
public class GenreDbStorage {

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private final JdbcTemplate jdbcTemplate;

    public Collection<Genre> getAllGenres() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from genre");
        Collection <Genre> genres = new HashSet<>();
        while(rowSet.next()) {
            Genre genre = new Genre(
                    rowSet.getInt(ID_COLUMN),
                    GenreType.valueOf(rowSet.getString(NAME_COLUMN))
            );
            genres.add(genre);
        }
        return genres;
    }

    public Genre getGenreById(Integer id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from genre where id = ?", id);
        if(rowSet.next()){
            return new Genre(
                    rowSet.getInt(ID_COLUMN),
                    GenreType.valueOf(rowSet.getString(NAME_COLUMN))
            );
        }
        throw new GenreNotFoundException(id);
    }
}

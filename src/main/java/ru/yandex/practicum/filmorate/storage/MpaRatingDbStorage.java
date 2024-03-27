package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.enums.MpaRating;
import ru.yandex.practicum.filmorate.exceptions.RatingNotFoundException;
import ru.yandex.practicum.filmorate.model.Rating;

import java.util.Collection;
import java.util.HashSet;

@RequiredArgsConstructor
@Repository
public class MpaRatingDbStorage {
    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "NAME";
    private final JdbcTemplate jdbcTemplate;


    public Collection<Rating> getListOfRatings() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from mpa_rating");
        Collection<Rating> ratings = new HashSet<>();
        while (rowSet.next()) {
            Rating rating = new Rating(
                    rowSet.getInt(ID_COLUMN),
                    MpaRating.valueOf(rowSet.getString(NAME_COLUMN))
            );
            ratings.add(rating);
        }
        return ratings;
    }

    public Rating getRatingById(Integer id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from mpa_rating where id = ?", id);
        if (rowSet.next()) {
            return new Rating(
                    rowSet.getInt(ID_COLUMN),
                    MpaRating.valueOf(rowSet.getString(NAME_COLUMN))
            );
        }
        throw new RatingNotFoundException(id);
    }
}

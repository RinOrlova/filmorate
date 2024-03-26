package ru.yandex.practicum.filmorate.converter;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

@Component
public class FilmConverter {

    public Film convert(SqlRowSet rowSet){
        return Film.builder()
                // TODO add values from rowSet
                // TODO extract all column names to constants

                .build();
    }

}

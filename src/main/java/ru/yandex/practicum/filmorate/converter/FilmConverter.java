package ru.yandex.practicum.filmorate.converter;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

@Component
public class FilmConverter {

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "NAME";
    private static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    private static final String RELEASE_DATE_COLUMN = "RELEASEDATE";
    private static final String DURATION_COLUMN = "DURATION";

    public Film convert(SqlRowSet rowSet) {
        return Film.builder()
                .id(rowSet.getInt(ID_COLUMN))
                .name(rowSet.getString(NAME_COLUMN))
                .description(rowSet.getString(DESCRIPTION_COLUMN))
                .releaseDate(rowSet.getDate(RELEASE_DATE_COLUMN).toLocalDate())
                .duration(rowSet.getInt(DURATION_COLUMN))
                .build();
    }

}

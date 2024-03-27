package ru.yandex.practicum.filmorate.converter;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

@Component
public class UserConverter {


    private static final String ID_COLUMN = "ID";
    private static final String EMAIL_COLUMN = "EMAIL";
    private static final String LOGIN_COLUMN = "LOGIN";
    private static final String BIRTHDAY_COLUMN = "BIRTHDAY";

    public User convert(SqlRowSet rowSet) {
        return User.builder()
                .id(rowSet.getInt(ID_COLUMN))
                .email(rowSet.getString(EMAIL_COLUMN))
                .login(rowSet.getString(LOGIN_COLUMN))
                .birthday(rowSet.getDate(BIRTHDAY_COLUMN).toLocalDate())
                // TODO add friends
                .build();

    }
}

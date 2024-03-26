package ru.yandex.practicum.filmorate.converter;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

@Component
public class UserConverter {


    private static final String ID_COLUMN = "id";
    private static final String EMAIL_COLUMN = "email";
    private static final String LOGIN_COLUMN = "login";
    private static final String BIRTHDAY_COLUMN = "birthday";

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

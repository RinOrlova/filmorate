package ru.yandex.practicum.filmorate.model;

import lombok.Value;
import ru.yandex.practicum.filmorate.enums.GenreType;

@Value
public class Genre {

    int id;
    GenreType genreType;

}

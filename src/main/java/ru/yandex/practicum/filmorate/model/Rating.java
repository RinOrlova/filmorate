package ru.yandex.practicum.filmorate.model;

import lombok.Value;
import ru.yandex.practicum.filmorate.enums.MpaRating;

@Value
public class Rating {

    int id;
    MpaRating mpaRating;

}

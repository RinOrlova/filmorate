package ru.yandex.practicum.filmorate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiPath {

    public static final String FILMS_PATH = "films";
    public static final String USERS_PATH = "users";
    public static final String GENRES_PATH = "genres";
    public static final String RATING_PATH = "mpa";
    public static final String BY_ID_PATH = "/{id}";
    public static final String FRIENDS_PATH = "/{id}/friends";
    public static final String FRIENDS_BY_FRIEND_ID_PATH = FRIENDS_PATH + "/{friendId}";
    public static final String COMMON_FRIENDS_PATH = FRIENDS_PATH + "/common/{otherId}";
    public static final String FILM_LIKES_PATH = "/{id}/like/{userId}";
    public static final String POPULAR_FILMS_PATH = "/popular";


}

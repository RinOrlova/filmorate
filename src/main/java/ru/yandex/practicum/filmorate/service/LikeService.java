package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.LikeDbStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

@RequiredArgsConstructor
@Service
public class LikeService {
    @Qualifier("filmDbStorage")
    private final FilmStorage filmStorage;
    @Qualifier("userDbStorage")
    private final UserStorage userStorage;
    private final LikeDbStorage likeDbStorage;


    // TODO use FilmStorage with Qualifier
    // TODO use UserStorage with Qualifier
    // TODO create and use LikeDbStorage (Qualifier not needed as there's 1 matching Spring bean)

    // TODO add method to add like (film_id, user_id) and check that data by film_id and user_id are present in UserStorage and FilmStorage
    // TODO add method to remove like (film_id, user_id)

    public Film addLike(Integer filmId, Integer userId) {
        Film film = filmStorage.getFilmFromStorage(filmId);
        User user = userStorage.getUserFromStorage(userId);
        return likeDbStorage.addLike(film, user);
    }

    public Film removeLike(Integer filmId, Integer userId) {
        Film film = filmStorage.getFilmFromStorage(filmId);
        User user = userStorage.getUserFromStorage(userId);
        if (film.getLikes().contains(user.getId())) {
            film.getLikes().remove(user.getId());
            return likeDbStorage.removeLike(film, user);
        } else {
            throw new UserNotFoundException(userId);
        }
    }


}

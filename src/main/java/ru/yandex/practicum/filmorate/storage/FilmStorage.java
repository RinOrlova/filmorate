package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmStorage {

    Film addFilm(Film film);

    Film updateFilm(Film film);

    List<Film> getListOfAllFilms();

    Optional<Film> getFilmById(Integer id);

    default Film getFilmFromStorage(Integer filmId) {
        return getFilmById(filmId).orElseThrow(() -> new FilmNotFoundException(filmId));
    }

}

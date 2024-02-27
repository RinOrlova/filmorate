package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {

    private static final int DEFAULT_LIMIT = 10;
    private final FilmStorage filmStorage;

    public Film addLike(Integer filmId, Integer userId) {
        Film film = getFilmFromStorage(filmId);
        film.getLikes().add(userId);
        filmStorage.updateFilm(film);
        return film;
    }

    public Film removeLike(Integer filmId, Integer userId) {
        Film film = getFilmFromStorage(filmId);
        film.getLikes().remove(userId);
        filmStorage.updateFilm(film);
        return film;
    }

    private Film getFilmFromStorage(Integer filmId) {
        return filmStorage.getFilmById(filmId).orElseThrow(() -> new FilmNotFoundException(filmId));
    }

    public Collection<Film> getPopularFilms(Integer count) {
        return filmStorage.getListOfAllFilms().stream()
                .sorted(Comparator.comparing(film -> film.getLikes().size()))
                .limit(getFilmsLimit(count))
                .collect(Collectors.toList());
    }

    private long getFilmsLimit(Integer count) {
        if(count != null) {
            if(count > 0) {
                return count;
            }
        }
        return DEFAULT_LIMIT;
    }


}

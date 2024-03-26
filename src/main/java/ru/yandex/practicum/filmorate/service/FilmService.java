package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {

    private static final int DEFAULT_LIMIT = 10;
    @Qualifier("filmDbStorage")
    private final FilmStorage filmStorage;

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public List<Film> getListOfAllFilms() {
        return filmStorage.getListOfAllFilms();
    }

    public Film getFilmById(Integer id) {
        return filmStorage.getFilmFromStorage(id);
    }

    public Collection<Film> getPopularFilms(Integer count) {
        return filmStorage.getListOfAllFilms().stream()
                .sorted(Comparator.comparing(film -> ((Film) film).getLikes().size()).reversed())
                .limit(getFilmsLimit(count))
                .collect(Collectors.toList());
    }

    private long getFilmsLimit(Integer count) {
        if (count != null) {
            if (count > 0) {
                return count;
            }
        }
        return DEFAULT_LIMIT;
    }
}

package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class InMemoryFilmStorage implements FilmStorage {


    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 0;

    public Film addFilm(Film film) {
        log.info("Attempt to add film {}", film);
        Integer filmId = getNextValidId();
        Film filmWithId = film.toBuilder()
                .id(filmId)
                .build();
        films.put(filmWithId.getId(), filmWithId);
        log.info("Film with id={} successfully added", filmId);
        return filmWithId;
    }

    private Integer getNextValidId() {
        return id += 1;
    }

    public Film updateFilm(Film film) {
        Integer filmId = film.getId();
        if (films.containsKey(filmId)) {
            log.info("Attempt to change film with id={}", filmId);
            films.put(filmId, film);
            log.info("Film with id={} successfully updated", filmId);
            return film;
        } else {
            log.warn("Film with id={} is not present", filmId);
        }
        throw new FilmNotFoundException(filmId);
    }

    public List<Film> getListOfAllFilms() {
        return new ArrayList<>(films.values());
    }

}

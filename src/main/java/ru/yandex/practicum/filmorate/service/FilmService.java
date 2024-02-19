package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {

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

    public Optional<Film> updateFilm(Film film) {
        Integer filmId = film.getId();
        if (films.containsKey(filmId)) {
            log.info("Attempt to change film with id={}", filmId);
            films.put(filmId, film);
            log.info("Film with id={} successfully updated", filmId);
            return Optional.of(film);
        } else {
            log.warn("Film with id={} is not present", filmId);
        }
        return Optional.empty();
    }

    public List<Film> getListOfAllFilms() {
        return new ArrayList<>(films.values());
    }
}

package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class FilmService {

    private final Map<String, Film> films = new HashMap<>();

    public void addFilm(Film film) {
        log.info("Attempt to add film {}", film);
        String filmId = UUID.randomUUID().toString();
        Film filmWithId = film.toBuilder()
                .id(filmId)
                .build();
        films.put(filmWithId.getId(), filmWithId);
        log.info("Film with id={} successfully added", filmId);
    }

    public void updateFilm(Film film) {
        String filmId = film.getId();
        if (films.containsKey(filmId)) {
            log.info("Attempt to change film with id={}", filmId);
            films.put(filmId, film);
            log.info("Film with id={} successfully updated", filmId);
        } else {
            log.warn("Film with id={} is not present", filmId);
        }
    }

    public Map<String, Film> getAllFilms() {
        return films;
    }
}

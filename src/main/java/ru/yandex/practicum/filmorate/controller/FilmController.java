package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(ApiPath.FILM_PATH)
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<Film> add(@Valid @RequestBody Film film) {
        Film result = filmService.addFilm(film);
        return ResponseEntity.status(200)
                .body(result);
    }

    @PutMapping
    public ResponseEntity<Film> change(@Valid @RequestBody Film film) {
        Film result = filmService.updateFilm(film);
        return ResponseEntity.status(200)
                .body(result);
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return new ArrayList<>(filmService.getListOfAllFilms().values());
    }

    @GetMapping(ApiPath.BY_ID_PATH)
    public ResponseEntity<Film> getFilmById(@PathVariable(name = "id") Integer id) {
        Film result = filmService.getFilmById(id);
        return ResponseEntity.status(200)
                .body(result);
    }

    @GetMapping(ApiPath.POPULAR_FILMS_PATH)
    public ResponseEntity<Collection<Film>> getPopularFilms(@RequestParam(name = "count", required = false) Integer count) {
        Collection<Film> popularFilms = filmService.getPopularFilms(count);
        return ResponseEntity.status(200)
                .body(popularFilms);
    }

}

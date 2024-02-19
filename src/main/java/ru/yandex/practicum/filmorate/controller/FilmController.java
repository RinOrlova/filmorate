package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPath.FILMS_PATH)
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public @ResponseBody ResponseEntity<Film> add(@Valid @RequestBody Film film) {
        Film result = filmService.addFilm(film);
        return ResponseEntity.status(200)
                .body(result);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<Film> change(@Valid @RequestBody Film film) {
        try {
            Film result = filmService.updateFilm(film);
            return ResponseEntity.status(200)
                    .body(result);
        } catch (FilmNotFoundException exception) {
            return ResponseEntity.status(404)
                    .body(film);
        }
    }

    @GetMapping
    public @ResponseBody List<Film> getFilms() {
        return filmService.getListOfAllFilms();
    }
}

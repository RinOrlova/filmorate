package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public @ResponseBody ResponseEntity<Optional<Film>> change(@Valid @RequestBody Film film) {
        Optional<Film> result = filmService.updateFilm(film);
        ResponseEntity<Optional<Film>> response;
        if (result.isEmpty()) {
            response = ResponseEntity.status(404)
                    .body(result);
        } else {
            response = ResponseEntity.status(200)
                    .body(result);
        }
        return response;

    }

    @GetMapping
    public @ResponseBody List<Film> getFilms() {
        return filmService.getListOfAllFilms();
    }
}

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
    public @ResponseBody ResponseEntity<Object> add(@Valid @RequestBody Film film) {
        Optional<Film> result = filmService.addFilm(film);
        ResponseEntity<Object> response;
        if (result.isEmpty()) {
            response = ResponseEntity.status(500)
                    .body(result);
        } else {
            response = ResponseEntity.status(200)
                    .body(result);
        }
        return response;
    }

    @PutMapping
    public @ResponseBody ResponseEntity<Object> change(@Valid @RequestBody Film film) {
        Optional<Film> result = filmService.updateFilm(film);
        ResponseEntity<Object> response;
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

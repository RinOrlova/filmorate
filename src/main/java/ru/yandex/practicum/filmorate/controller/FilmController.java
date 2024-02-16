package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(ApiPath.FILMS_PATH)
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public void add(@Valid @RequestBody Film film) {
        filmService.addFilm(film);
    }

    @PutMapping
    public void change(@Valid @RequestBody Film film) {
        filmService.updateFilm(film);
    }

    @GetMapping
    public @ResponseBody Map<String, Film> getFilms() {
        return filmService.getAllFilms();
    }
}

package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(ApiPath.FILMS_PATH)
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final FilmStorage filmStorage;

    @PostMapping
    public @ResponseBody ResponseEntity<Film> add(@Valid @RequestBody Film film) {
        Film result = filmStorage.addFilm(film);
        return ResponseEntity.status(200)
                .body(result);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<Film> change(@Valid @RequestBody Film film) {
            Film result = filmStorage.updateFilm(film);
            return ResponseEntity.status(200)
                    .body(result);
    }

    @GetMapping
    public @ResponseBody List<Film> getFilms() {
        return filmStorage.getListOfAllFilms();
    }

    @PutMapping(ApiPath.FILM_LIKES_PATH)
    public @ResponseBody ResponseEntity<Film> addLike(@PathVariable Integer id,
                                                      @PathVariable Integer userId) {
        Film result = filmService.addLike(id, userId);
        return ResponseEntity.status(200)
                .body(result);
    }

    @DeleteMapping(ApiPath.FILM_LIKES_PATH)
    public @ResponseBody ResponseEntity<Film> deleteLike(@PathVariable Integer id,
                                                      @PathVariable Integer userId) {
        Film result = filmService.removeLike(id, userId);
        return ResponseEntity.status(200)
                .body(result);
    }

    @GetMapping(ApiPath.POPULAR_FILMS_PATH)
    public @ResponseBody ResponseEntity<Collection<Film>> getPopularFilms(@RequestParam(name = "count") Integer count){
        Collection<Film> popularFilms = filmService.getPopularFilms(count);
        return ResponseEntity.status(200)
                .body(popularFilms);
    }

}

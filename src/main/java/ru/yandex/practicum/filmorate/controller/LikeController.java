package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.LikeService;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPath.FILMS_PATH)
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PutMapping(ApiPath.FILM_LIKES_PATH)
    public ResponseEntity<Film> addLike(@PathVariable Integer id,
                                        @PathVariable Integer userId) {
        Film result = likeService.addLike(id, userId);
        return ResponseEntity.status(200)
                .body(result);
    }

    @DeleteMapping(ApiPath.FILM_LIKES_PATH)
    public ResponseEntity<Film> deleteLike(@PathVariable Integer id,
                                           @PathVariable Integer userId) {
        Film result = likeService.removeLike(id, userId);
        return ResponseEntity.status(200)
                .body(result);
    }

}

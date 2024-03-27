package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.LikeService;

@RestController
@RequestMapping(ApiPath.FILM_PATH)
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PutMapping(ApiPath.FILM_LIKES_PATH)
    public ResponseEntity<Film> addLike(@PathVariable(name = "id") Integer id,
                                        @PathVariable(name = "userId") Integer userId) {
        Film result = likeService.addLike(id, userId);
        return ResponseEntity.status(200)
                .body(result);
    }

    @DeleteMapping(ApiPath.FILM_LIKES_PATH)
    public ResponseEntity<Film> deleteLike(@PathVariable(name = "id") Integer id,
                                           @PathVariable(name = "userId") Integer userId) {
        Film result = likeService.removeLike(id, userId);
        return ResponseEntity.status(200)
                .body(result);
    }

}

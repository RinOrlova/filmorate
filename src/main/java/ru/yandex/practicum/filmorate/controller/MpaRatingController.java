package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.service.MpaRatingService;

import java.util.Collection;

@RestController
@RequestMapping(ApiPath.RATING_PATH)
@RequiredArgsConstructor
public class MpaRatingController {

    private final MpaRatingService mpaRatingService;

    @GetMapping
    public Collection<Rating> getAllRatings(){
        return mpaRatingService.getListOfRatings();
    }
    @GetMapping
    @RequestMapping(ApiPath.BY_ID_PATH)
    public Rating getRatingById(@PathVariable Integer id){
        return mpaRatingService.getGenreById(id);
    }
}

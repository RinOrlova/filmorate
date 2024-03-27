package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.storage.MpaRatingDbStorage;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MpaRatingService {
    private final MpaRatingDbStorage mpaRatingDbStorage;

    public Collection<Rating> getListOfRatings() {
        return mpaRatingDbStorage.getListOfRatings();
    }

    public Rating getGenreById(Integer id) {
        return mpaRatingDbStorage.getRatingById(id);
    }
}

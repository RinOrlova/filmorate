package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.GenreDbStorage;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GenreService {

    private GenreDbStorage genreDbStorage;

    public Collection<Genre> getListOfGenres() {
        return genreDbStorage.getAllGenres();
    }

    public Genre getGenreById(Integer id) {
        return genreDbStorage.getGenreById(id);
    }
}

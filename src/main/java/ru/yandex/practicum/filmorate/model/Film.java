package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;
import ru.yandex.practicum.filmorate.validation.annotation.ValidFilmReleaseDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Value
@Builder(toBuilder = true)
public class Film {

    @Nullable
    Integer id;
    @NotBlank String name;
    @Size(max = 200) String description;
    @NotBlank String genre;
    @ValidFilmReleaseDate
    LocalDate releaseDate;
    @Positive int duration;
    @NotBlank String mpaRating;
    Set<Integer> likes = new HashSet<>();


}

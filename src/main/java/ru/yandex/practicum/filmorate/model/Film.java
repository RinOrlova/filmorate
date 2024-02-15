package ru.yandex.practicum.filmorate.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.UUID;
import ru.yandex.practicum.filmorate.validation.annotation.ValidFilmReleaseDate;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class Film {

    @Nullable
    @UUID String id;
    @NotBlank String name;
    @Size(max = 200) String description;
    @ValidFilmReleaseDate
    LocalDate releaseDate;
    @Positive int duration;


}

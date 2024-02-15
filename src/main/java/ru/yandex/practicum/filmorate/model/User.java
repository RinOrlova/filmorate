package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;
import ru.yandex.practicum.filmorate.validation.annotation.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class User {

    @Nullable
    @UUID
    final String id;
    @Email String email;
    @NotBlank
    @Pattern(regexp = "^\\S+$")
    final String login;
    @Nullable
    String name;
    @PastOrPresent LocalDate birthday;


}

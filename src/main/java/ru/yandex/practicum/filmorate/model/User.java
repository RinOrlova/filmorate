package ru.yandex.practicum.filmorate.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class User {

    @Nullable @UUID final String id;
    @Email String email;
    @NotBlank @Pattern(regexp = "^\\S+$") final String login;
    @Nullable String name;
    @PastOrPresent LocalDateTime birthday;


}

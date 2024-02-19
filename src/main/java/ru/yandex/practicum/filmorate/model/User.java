package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Optional;

@Value
public class User {

    @Nullable
    Integer id;
    @Email String email;
    @NotBlank
    @Pattern(regexp = "^\\S+$")
    String login;
    @Nullable
    String name;
    @PastOrPresent LocalDate birthday;

    @Builder(toBuilder = true)
    public User(@Nullable Integer id,
                String email,
                String login,
                @Nullable String name,
                LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = Optional.ofNullable(name).orElse(login);
        this.birthday = birthday;
    }
}

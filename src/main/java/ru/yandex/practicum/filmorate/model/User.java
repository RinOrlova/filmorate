package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Value
public class User implements Comparable<User> {

    @Nullable
    Integer id;
    @Email String email;
    @NotBlank
    @Pattern(regexp = "^\\S+$")
    String login;
    @Nullable
    String name;
    @PastOrPresent LocalDate birthday;
    Set<Integer> friendsIds;

    @Builder(toBuilder = true)
    public User(@Nullable Integer id,
                String email,
                String login,
                @Nullable String name,
                LocalDate birthday,
                @Nullable Set<Integer> friendsIds) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.friendsIds = friendsIds != null ? friendsIds : new HashSet<>();
        this.name = getName(login, name);
        this.birthday = birthday;
    }

    private static String getName(String login, String name) {
        Optional<String> optName = Optional.ofNullable(name);
        if (optName.isPresent()) {
            String nonNullName = optName.get();
            if (StringUtils.hasText(nonNullName)) {
                return name;
            }
        }
        return login;
    }

    @Override
    public int compareTo(User o) {
        Integer otherId = o.getId();
        Integer id = this.getId();
        if (id != null) {
            if (otherId != null) {
                return Integer.compare(id, otherId);
            }
            return -1;
        } else if (otherId != null) {
            return 1;
        }
        return 0;

    }
}

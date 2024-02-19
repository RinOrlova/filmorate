package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiPath.USERS_PATH)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public @ResponseBody ResponseEntity<User> add(@Valid @RequestBody User user) {
        User result = userService.addUser(user);
        return ResponseEntity.status(200)
                .body(result);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<User> change(@Valid @RequestBody User user) {
        try {
            User result = userService.updateUser(user);
            return ResponseEntity.status(200)
                    .body(result);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(404)
                    .body(user);
        }
    }

    @GetMapping
    public @ResponseBody List<User> getUsers() {
        return new ArrayList<>(userService.getAllUsers().values());
    }
}

package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public @ResponseBody ResponseEntity<Optional<User>> change(@Valid @RequestBody User user) {
        Optional<User> result = userService.updateUser(user);
        ResponseEntity<Optional<User>> response;
        if (result.isEmpty()) {
            response = ResponseEntity.status(404)
                    .body(result);
        } else {
            response = ResponseEntity.status(200)
                    .body(result);
        }
        return response;
    }

    @GetMapping
    public @ResponseBody List<User> getUsers() {
        return new ArrayList<>(userService.getAllUsers().values());
    }
}

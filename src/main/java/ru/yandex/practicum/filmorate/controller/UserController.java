package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(ApiPath.USERS_PATH)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void add(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping
    public void change(@Valid @RequestBody User user) {
        userService.updateUser(user);
    }

    @GetMapping
    public @ResponseBody Map<String, User> getUsers() {
        return userService.getAllUsers();
    }
}

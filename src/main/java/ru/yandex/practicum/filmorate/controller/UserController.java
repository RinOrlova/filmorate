package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Map;

@RestController
@RequestMapping(ApiPath.USERS_PATH)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(ApiPath.ADD_USER)
    public void add(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping(value = ApiPath.CHANGE_USER)
    public void change(@Valid @RequestBody User user) {
        userService.updateUser(user);
    }

    @GetMapping(ApiPath.GET_ALL_USERS)
    public @ResponseBody Map<String, User> getUsers() {
        return userService.getAllUsers();
    }
}

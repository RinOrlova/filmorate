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

@RestController
@RequestMapping(ApiPath.USER_PATH)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> add(@Valid @RequestBody User user) {
        User result = userService.addUser(user);
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping
    public ResponseEntity<User> change(@Valid @RequestBody User user) {
        User result = userService.updateUser(user);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>(userService.getAllUsers().values());
    }

    @GetMapping(ApiPath.BY_ID_PATH)
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Integer id) {
        User result = userService.getUserFromStorage(id);
        return ResponseEntity.status(200).body(result);
    }

}
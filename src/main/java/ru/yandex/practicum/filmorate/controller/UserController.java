package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
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
        User result = userService.updateUser(user);
        return ResponseEntity.status(200)
                .body(result);
    }

    @GetMapping
    public @ResponseBody List<User> getUsers() {
        return new ArrayList<>(userService.getAllUsers().values());
    }

    @GetMapping(ApiPath.USER_BY_ID_PATH)
    public @ResponseBody ResponseEntity<User> getUserById(@PathVariable(name = "id") Integer id) {
        User result = userService.getUserFromStorage(id);
        return ResponseEntity.status(200)
                .body(result);
    }

    @PutMapping(ApiPath.FRIENDS_BY_FRIEND_ID_PATH)
    public @ResponseBody ResponseEntity<User> addFriend(@PathVariable Integer id,
                                                        @PathVariable Integer friendId) {
        User result = userService.addFriend(id, friendId);
        return ResponseEntity.status(200)
                .body(result);
    }

    @DeleteMapping(ApiPath.FRIENDS_BY_FRIEND_ID_PATH)
    public @ResponseBody ResponseEntity<User> deleteFriend(@PathVariable Integer id,
                                                           @PathVariable Integer friendId) {
        User result = userService.removeFriend(id, friendId);
        return ResponseEntity.status(200)
                .body(result);
    }

    @GetMapping(ApiPath.FRIENDS_PATH)
    public @ResponseBody ResponseEntity<Collection<User>> getFriends(@PathVariable Integer id) {
        Collection<User> friends = userService.getFriendsCollection(id);
        return ResponseEntity.status(200)
                .body(friends);
    }

    @GetMapping(ApiPath.COMMON_FRIENDS_PATH)
    public @ResponseBody ResponseEntity<Collection<User>> getCommonFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        Collection<User> commonFriends = userService.getCommonFriends(id, otherId);
        return ResponseEntity.status(200)
                .body(commonFriends);
    }

}
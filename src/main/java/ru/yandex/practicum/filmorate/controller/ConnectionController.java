package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.ApiPath;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ConnectionService;

import java.util.Collection;

@RestController
@RequestMapping(ApiPath.USER_PATH)
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @PutMapping(ApiPath.FRIENDS_BY_FRIEND_ID_PATH)
    public User addFriend(@PathVariable(name = "id") Integer id, @PathVariable(name = "friendId") Integer friendId) {
        return connectionService.addFriend(id, friendId);
    }

    // DELETE remove friend
    @DeleteMapping(ApiPath.FRIENDS_BY_FRIEND_ID_PATH)
    public User deleteFriend(@PathVariable(name = "id") Integer id, @PathVariable(name = "friendId") Integer friendId) {
        return connectionService.removeFriend(id, friendId);
    }

    @GetMapping(ApiPath.FRIENDS_PATH)
    public Collection<User> getFriendsByUserId(@PathVariable(name = "id") Integer id) {
        return connectionService.getFriends(id);
    }

    @GetMapping(ApiPath.COMMON_FRIENDS_PATH)
    public Collection<User> getCommonsFriends(@PathVariable(name = "id") Integer id, @PathVariable(name = "otherId") Integer otherId) {
        return connectionService.getCommonFriends(id, otherId);
    }
}
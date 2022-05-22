package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserStorage userStorage;

    @GetMapping
    public Collection<User> allUsers() {
        return userStorage.getAllUsers().values();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userStorage.getBiUserId(id);
    }

    @PostMapping
    public User add(@Valid @RequestBody User user) {
        return userStorage.createUser(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return userStorage.updateUser(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addToFriends(@PathVariable Integer id, @PathVariable Integer friendId) {
        return userService.addFriends(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFromFriends(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.removeFriends(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public Collection<User> getUserFriends(@PathVariable Integer id) {
        return userService.getFriends(id);
    }
}
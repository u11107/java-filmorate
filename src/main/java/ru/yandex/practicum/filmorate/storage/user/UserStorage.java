package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;

public interface UserStorage {
    Map<Integer, User> allUsers();

    User add(User user);

    User update(User user);

    void remove(Integer id);
}

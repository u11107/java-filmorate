package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    List<User> getAll();

    Optional<User> getFindById(Integer id);

    User createUser(User user);

    User updateUser(User user);

    void addFriend(Integer userId, Integer friendId);

    void confirmFriend(Integer userId, Integer friendId);

    void deleteFriend(Integer userId, Integer friendId);

    boolean isUserExist(Integer id);
}

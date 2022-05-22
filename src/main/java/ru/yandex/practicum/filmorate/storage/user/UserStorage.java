package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;

public interface UserStorage {

    //создания пользователя
    User createUser(User user) throws ValidationException;

    //изменения данных пользователя
    User updateUser(User user) throws ValidationException;

    //удаления всех пользователей
    void deleteUsers();

    //удаление пользователя по id
    void getRemoveUserById(Integer id);

    //получение пользователя по id
    User getBiUserId(Integer id);

    //получения всех пользователей
    Map<Integer, User> getAllUsers();
}

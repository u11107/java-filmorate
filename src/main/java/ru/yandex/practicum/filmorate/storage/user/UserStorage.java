package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;
import java.util.Map;
public interface UserStorage {

//  Получение всех пользователей
    Map<Integer, User> getAllUsers();
//  Создание пользователя
    User addUser(User user);
//  Обновление данных о пользователе
    User updateUser(User user);
//  Удаление пользователя
    void removeUser(Integer id);
}

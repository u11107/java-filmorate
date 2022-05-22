package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {

    private final Map<Integer, User> users = new HashMap<>();
    private int id = 1;

    @Override
    public User createUser(User user) throws ValidationException {
        if(checkValidUser(user)) {
            user.setId(id);
            users.put(id, user);
            id++;
            log.info("Пользователь {} добавлен", user);
        }
        return user;
    }

    @Override
    public User updateUser(User user) throws ValidationException {
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("пользователь " + user.getId());
        }
        User updateUser = users.get(user.getId());
        if(checkValidUser(user)) {
            updateUser.setEmail(user.getEmail());
            updateUser.setLogin(user.getLogin());
            updateUser.setBirthday(user.getBirthday());
            updateUser.setName(user.getName());
            users.put(user.getId(), updateUser);
            log.info("Данные о пользователе {} обновлены", updateUser);
        }
        return null;
    }

    @Override
    public void deleteUsers() {
       users.clear();
       log.info("Все пользователи удалены");
    }

    @Override
    public void getRemoveUserById(Integer id) {
        users.remove(id);
        log.info("Пользователь c id {} удален", id);
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        log.info("Получены все пользователи");
        return users;
    }

    @Override
    public User getBiUserId(Integer id) {
        if (!users.containsKey(id)) {
            throw new ValidationException("Пользователь с запрошенным " + users.get(id) + " не найден");
        }
        log.info("Получен пользователь с id {}", id);
        return users.get(id);
    }

    public boolean checkValidUser(User user) {
        if (user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            log.error("Введен пустой email или отсутствует символ @.");
            throw new ValidationException("Email не может быть пустым и должен содержать символ @.");
        }
        if (user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            log.error("Введен пустой логин или логин содержит пробелы.");
            throw new ValidationException("Логин не может быть пустым или содержать пробелы.");
        }
        if (user.getName().isBlank() || user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Введена дата рождения из будущего.");
            throw new ValidationException("Дата рождения не может быть в будущем.");
        }
        return true;
    }
}
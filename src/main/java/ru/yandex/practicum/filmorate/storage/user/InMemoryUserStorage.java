package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
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

    public Map<Integer, User> getAllUsers() {
        return users;
    }

    public User addUser(User user) {
        if (checkValidData(user)) {
            user.setId(id);
            users.put(id, user);
            id++;
            log.debug("Добавлен пользователь {}.", user);
        }
        return user;
    }

    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("пользователь " + user.getId());
        }
        User updateUser = users.get(user.getId());
        if (validator(user)) {
            updateUser.setEmail(user.getEmail());
            updateUser.setLogin(user.getLogin());
            updateUser.setName(user.getName());
            updateUser.setBirthday(user.getBirthday());
            users.put(user.getId(), updateUser);
            log.debug("Обновлены данные пользователя {}.", updateUser);
        }
        return updateUser;
    }

    public void removeUser(Integer id) {
        users.remove(id);
        log.debug("Удален пользователь {}", id);
    }

    private boolean checkValidData(User user) {
        if (user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            log.error("Введен пустой email или отсутствует символ @.");
            throw new ValidationException("Email не может быть пустым");
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

    private boolean validator(User user) {
        if (checkValidData(user)) {
            if (!users.containsKey(user.getId())) {
                log.error("Введен неверный id.");
                throw new ValidationException("Пользователя с id" + user.getId() + " нет.");
            }
        }
        return true;
    }
}

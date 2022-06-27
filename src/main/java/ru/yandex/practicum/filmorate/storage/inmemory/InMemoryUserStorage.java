package ru.yandex.practicum.filmorate.storage.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    private int id = 1;
    private final Map<Integer, User> users = new HashMap<>();

    public List<User> getAll() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return new ArrayList<>(users.values());
    }

    public Optional<User> getFindById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }


    public User updateUser(User user) {
        users.put(user.getId(), user);
        log.debug("Пользователь обновлен: {}", user);
        return user;
    }

    public User createUser(User user) {
        int idUser = generateId();
        user.setId(idUser);
        users.put(idUser, user);
        log.debug("Добавлен пользователь: {}", user);
        return user;
    }

    public void addFriend(Integer userId, Integer friendId) {
        getFindById(userId).get().addFriend(friendId);
        log.debug("Добавлен для пользователя c id {} друг с id {}", userId, friendId);
    }

    @Override
    public void confirmFriend(Integer userId, Integer friendId) {
    }

    public boolean isUserExist(Integer id) {
        return users.containsKey(id);
    }

    private int generateId() {
        return id++;
    }

    public void deleteFriend(Integer userId, Integer friendId) {
        getFindById(userId).get().deleteFriend(friendId);
        log.debug("Удален для пользователя c id {} друг с id {}", userId, friendId);
    }
}

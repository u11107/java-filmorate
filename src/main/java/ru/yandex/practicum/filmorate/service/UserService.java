package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserStorage userStorage;
    private Map<Integer, User> userFriendsMap;

    public User addFriends(Integer id, Integer idFrend) {
        userFriendsMap = userStorage.getAllUsers();
        if(!userStorage.getAllUsers().containsKey(id)) {
            throw new NotFoundException("пользователь" + id);
        }
        if(!userStorage.getAllUsers().containsKey(idFrend)) {
            throw new NotFoundException("пользователь" + id);
        }
        userFriendsMap.get(id).getFriends().add(idFrend);
        userFriendsMap.get(idFrend).getFriends().add(id);
        log.info("Новый друг добавлен");
        return userStorage.getBiUserId(idFrend);
    }

    public void removeFriends(Integer id, Integer removeId) {
        userFriendsMap = userStorage.getAllUsers();
        if (!userStorage.getAllUsers().containsKey(id)) {
            throw new NotFoundException("пользователь" + id);
        }
        if (!userStorage.getAllUsers().containsKey(removeId)) {
            throw new NotFoundException("пользователь" + removeId);
        }
        userFriendsMap.get(id).getFriends().remove(removeId);
        userFriendsMap.get(removeId).getFriends().remove(id);
        log.info("Друг удален");
    }

    public Collection<User> getFriends(Integer id) {
        List<User> friends = new ArrayList<>();
        if (!userStorage.getAllUsers().containsKey(id)) {
            throw new NotFoundException("пользователь " + id);
        }
        Set<Integer> userFr = userStorage.getAllUsers().get(id).getFriends();
        for (Integer user : userFr) {
            friends.add(userStorage.getAllUsers().get(user));
        }
        log.info("Получен список друзей");
        return friends;
    }
}

package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Map<Integer, User> allUsers() {
        return userStorage.allUsers();
    }

    public User add(User user) {
        return userStorage.add(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public User addToFriends(Integer id, Integer friendId) {
        Map<Integer, User> userMap = userStorage.allUsers();
        if (!userStorage.allUsers().containsKey(id)) {
            throw new NotFoundException("пользователь" + id);
        }
        if (!userStorage.allUsers().containsKey(friendId)) {
            throw new NotFoundException("пользователь" + friendId);
        }
        userMap.get(id).getFriends().add(friendId);
        userMap.get(friendId).getFriends().add(id);
        return getUser(friendId);
    }

    public void removeFromFriends(Integer id, Integer removeFromId) {
        Map<Integer, User> userMap = userStorage.allUsers();
        if (!userStorage.allUsers().containsKey(id)) {
            throw new NotFoundException("пользователь" + id);
        }
        if (!userStorage.allUsers().containsKey(removeFromId)) {
            throw new NotFoundException("пользователь" + removeFromId);
        }
        userMap.get(id).getFriends().remove(removeFromId);
        userMap.get(removeFromId).getFriends().remove(id);
    }

    public Collection<User> getUserFriends(Integer id) {
        List<User> friends = new ArrayList<>();
        if (!userStorage.allUsers().containsKey(id)) {
            throw new NotFoundException("пользователь " + id);
        }
        Set<Integer> userSet = userStorage.allUsers().get(id).getFriends();
        for (Integer user : userSet) {
            friends.add(userStorage.allUsers().get(user));
        }
        return friends;
    }

    public Collection<User> getMutualFriends(Integer id, Integer id1) {
        List<User> friendsNames = new ArrayList<>();
        if (!userStorage.allUsers().containsKey(id)) {
            throw new NotFoundException("пользователь " + id);
        }
        if (!userStorage.allUsers().containsKey(id1)) {
            throw new NotFoundException("пользователь " + id1);
        }
        Set<Integer> userSet = userStorage.allUsers().get(id).getFriends();
        Set<Integer> userSet1 = userStorage.allUsers().get(id1).getFriends();
        for (Integer user : userSet) {
            if (userSet1.contains(user)) {
                friendsNames.add(userStorage.allUsers().get(user));
            }
        }
        return friendsNames;
    }

    public User getUser(Integer id) {
        if (!userStorage.allUsers().containsKey(id)) {
            throw new NotFoundException("пользователь " + id);
        }
        return userStorage.allUsers().get(id);
    }
}
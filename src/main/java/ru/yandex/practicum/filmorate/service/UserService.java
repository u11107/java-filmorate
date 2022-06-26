package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Friends;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.FriendsStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserStorage userStorage;
    private final FriendsStorage friendsStorage;

    @Autowired
    public UserService(@Qualifier("userDbStorage") UserStorage userStorage, FriendsStorage friendsStorage) {
        this.userStorage = userStorage;
        this.friendsStorage = friendsStorage;
    }

    // поиск общих друзей для двух пользователей
    public Set<Long> getCommonFriends(Long user1, Long user2) {
        if (friendsStorage.getFriends(Math.
                toIntExact(user1)) == null || friendsStorage.getFriends(Math.
                toIntExact(user2)) == null) {
            return new HashSet<>();
        }
        return findFriendsById(user1).stream()
                .distinct()
                .filter(findFriendsById(user2)::contains)
                .collect(Collectors.toSet());
    }

    // получение всех пользователей
    public Collection<User> getAllUsers() {
        return (Collection<User>) userStorage.getAllUsers();
    }

    // создание пользователя
    public User createUser(User user) {
        return userStorage.addUser(user);
    }

    // обновление пользователя
    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    // удаление пользователя
    public void deleteUser(Long id) {
        userStorage.deleteUser(id);
    }

    // поиск по id
    public User findUserById(Long id) {
        return userStorage.getUser(id);
    }

    // получение списка друзей
    public Collection<Long> findFriendsById(Long id) {
        return friendsStorage.getFriends(id);
    }

    // добавление дружбы
    public void addFriend(Long id, Long friendId) {
        User user = findUserById(id);
        User friend = findUserById(friendId);
        friendsStorage.addFriend(Friends.builder().user(user).friend(friend).build());
    }

    // удаление дружбы
    public void removeFriend(Long id, Long friendId) {
        User user = findUserById(id);
        User friend = findUserById(friendId);
        friendsStorage.deleteFriend(Friends.builder().user(user).friend(friend).build());
    }
}
package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.Friends;

import java.util.Collection;

public interface FriendsStorage {
//добавление друга
    void addFriend(Friends friends);
//удаление из друзей
    void deleteFriend(Friends friends);
//получение всех друзей
    Collection<Long> getFriends(long userId);
}

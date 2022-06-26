package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Like;

import java.util.Collection;

public interface LikeStorage {

    // поставить лайк
    void addLike(Like like);
    // удалить лайк
    void deleteLike(Like like);
    // получить популярные фильмы
    Collection<Film> getPopular(int count);
}

package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Like;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.LikeStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;


@Service
@Slf4j
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;
    private final LikeStorage likeStorage;

    // зависимость UserStorage внедрена для проверки существования пользователей, которые ставят лайки
    @Autowired
    public FilmService(@Qualifier("filmDbStorage") FilmStorage filmStorage,
                       @Qualifier("userDbStorage") UserStorage userStorage,
                       LikeStorage likeStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.likeStorage = likeStorage;
    }

    // получение списка всех фильмов
    public Collection<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    // добавление нового фильма
    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    // обновление фильма
    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    // поиск фильма
    public Film getFilm(long id) {
        return filmStorage.getFilm(id);
    }

    // удаление фильма
    public void deleteFilm(long id) {
        filmStorage.deleteFilm(id);
    }

    // получаем 2 id, с помощью билдера делаем из них лайк, а затем передаем его в хранилище
    public void addLike(long filmId, long userId) {
        likeStorage.addLike(Like.builder()
                .film(getFilm(filmId))
                .user(userStorage.getUser(userId))
                .build());
    }

    // получаем 2 id, с помощью билдера делаем из них лайк, а затем передаем его в хранилище
    public void deleteLike(long filmId, long userId) {
        likeStorage.deleteLike(Like.builder()
                .film(getFilm(filmId))
                .user(userStorage.getUser(userId))
                .build());
    }

    // получаем список популярных фильмов
    public Collection<Film> getPopular(int count) {
        return likeStorage.getPopular(count);
    }

    public Collection<Genre> getGenres(Integer id) {
        return filmStorage.getGenre(id);
    }

    public Collection<Rating> getMpaa(Integer id) {
        return filmStorage.getRating(id);
    }
}
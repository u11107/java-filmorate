package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

public interface FilmStorage {

//  Получение всех фильмов
    Map<Integer, Film> getAllFilms();
//  Добавление фильма
    Film addFilm(Film film);
//  Обновление данных о фильме
    Film updateFilm(Film film);
//  Удаление фильма
    void removeFilm(Integer id);
}

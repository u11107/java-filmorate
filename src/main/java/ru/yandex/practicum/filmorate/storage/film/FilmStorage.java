package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Map;

public interface FilmStorage {

    //добавления фильма
    Film createFilm(Film film) throws ValidationException;

    //обновление данных о фильме
    Film updateFilm(Film film) throws ValidationException;

    //получение всех фильмов
    Map<Integer, Film> getAllFilms();

    //удаления фильма
    void deleteFilm(Film film);

    // получение фильма по id
    Film getFilmById(Integer id);
}

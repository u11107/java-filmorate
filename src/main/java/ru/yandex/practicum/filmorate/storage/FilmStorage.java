package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmStorage {
    List<Film> getAllFilm();

    Optional<Film> getById(Integer id);

    Film createFilm(Film film);

    Film updateFilm(Film film);

    void addLike(Integer filmId, Integer userId);

    void deleteLike(Integer filmId, Integer userId);

    boolean isFilmExist(Integer id);
}

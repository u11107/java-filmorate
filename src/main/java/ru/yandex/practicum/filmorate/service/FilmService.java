package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Map<Integer, Film> allFilms() {
        return filmStorage.getAllFilms();
    }

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public void removeFilm(Integer id) {
        filmStorage.removeFilm(id);
    }

    public void addLike(Integer filmId, Integer userId) {
        if (!filmStorage.getAllFilms().containsKey(filmId)) {
            throw new NotFoundException("фильм" + filmId);
        }
        filmStorage.getAllFilms().get(filmId).getLikes().add(userId);
    }

    public void removeLike(Integer filmId, Integer userId) {
        if (!filmStorage.getAllFilms().containsKey(filmId)) {
            throw new NotFoundException("фильм" + filmId);
        }
        if (!getFilm(filmId).getLikes().contains(userId)) {
            throw new NotFoundException("лайк пользователя " + userId);
        }
        filmStorage.getAllFilms().get(filmId).getLikes().remove(userId);
    }

    public Collection<Film> getTopTenFilms(Integer count) {
        if (count > 0 && count < filmStorage.getAllFilms().size()) {
            return filmStorage.getAllFilms().values().stream()
                    .sorted((film1, film2) -> Integer.compare(film2.getLikes().size(), film1.getLikes().size()))
                    .limit(count).collect(Collectors.toList());
        }
        return filmStorage.getAllFilms().values().stream()
                .sorted((film1, film2) -> Integer.compare(film2.getLikes().size(), film1.getLikes().size()))
                .limit(filmStorage.getAllFilms().size()).collect(Collectors.toList());
    }

    public Film getFilm(Integer id) {
        if (!filmStorage.getAllFilms().containsKey(id)) {
            throw new NotFoundException("фильм" + id);
        }
        return filmStorage.getAllFilms().get(id);
    }
}
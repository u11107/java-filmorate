package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage filmStorage;

    // добавление лайка
    public void addLike(Integer filmId, Integer userId) {
        if (!filmStorage.getAllFilms().containsKey(filmId)) {
            throw new NotFoundException("фильм" + filmId);
        }
        filmStorage.getAllFilms().get(filmId).getLikes().add(userId);
    }

    // удаление лайка
    public void removeLike(Integer filmId, Integer userId) {
        if (!filmStorage.getAllFilms().containsKey(filmId)) {
            throw new NotFoundException("фильм" + filmId);
        }
        if (!filmStorage.getFilmById(filmId).getLikes().contains(userId)) {
            throw new NotFoundException("лайк пользователя " + userId);
        }
        filmStorage.getAllFilms().get(filmId).getLikes().remove(userId);
    }

    //рейтинг 10 фильмов
    public Collection<Film> getTopTenFilms(Integer count) {
        if (count > 0 && count < filmStorage.getAllFilms().size()) {
            return filmStorage.getAllFilms().values().stream()
                    .sorted((f1, f2) -> Integer.compare(f2.getLikes().size(), f1.getLikes().size()))
                    .limit(count).collect(Collectors.toList());
        }
        return filmStorage.getAllFilms().values().stream()
                .sorted((f1, f2) -> Integer.compare(f2.getLikes().size(), f1.getLikes().size()))
                .limit(filmStorage.getAllFilms().size()).collect(Collectors.toList());
    }
}

package ru.yandex.practicum.filmorate.storage.film;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Integer, Film> movie = new HashMap<>();
    @Getter
    private static final LocalDate EARLY_DATE = LocalDate.of(1985, 12, 28);
    private Integer id = 1;

    @Override
    public Film createFilm(Film film) throws ValidationException {
        if(validation(film)) {
            film.setId(id);
            movie.put(id, film);
            id++;
            log.debug("Добавлен новый фильм {}.", film);
        }
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (!movie.containsKey(film.getId())) {
            throw new ValidationException("фильм " + film.getId());
        }
        Film updateFilm = movie.get(film.getId());
        if (validation(film)) {
            updateFilm.setName(film.getName());
            updateFilm.setDescription(film.getDescription());
            updateFilm.setReleaseDate(film.getReleaseDate());
            updateFilm.setDuration(film.getDuration());
            movie.put(film.getId(), updateFilm);
            log.debug("Фильм обновлен {}.", updateFilm);
            return updateFilm;
        }
        return film;
    }

    @Override
    public Map<Integer, Film> getAllFilms() {
        log.info("Получены вся коллекция фильмов");
        return movie;
    }

    @Override
    public void deleteFilm(Film film) {
        log.info("Все фильмы удалены");
        movie.clear();
    }

    @Override
    public Film getFilmById(Integer id) {
        if (!movie.containsKey(id)) {
            throw new ValidationException("фильм " + movie.get(id));
        }
        log.info("Получен фильм с id {}", id);
        return movie.get(id);
    }

    public boolean validation(Film film) {
        if (film.getReleaseDate().isBefore(getEARLY_DATE())) {
            log.warn("Произошла ошибка  при создании фильма");
            throw new javax.validation.ValidationException("Тогда еще не было фильмов");
        } else {
            return false;
        }
    }
}


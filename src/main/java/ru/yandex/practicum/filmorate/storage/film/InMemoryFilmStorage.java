package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private final LocalDate movieDay = LocalDate.of(1895, 12, 28);
    private int id = 1;

    public Map<Integer, Film> getAllFilms() {
        return films;
    }

    public Film addFilm(Film film) {
        if (checkAddValidData(film)) {
            film.setId(id);
            films.put(id, film);
            id++;
            log.debug("Добавлен фильм {}.", film);
        }
        return film;
    }

    public Film updateFilm(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new NotFoundException("фильм " + film.getId());
        }
        Film updateFilm = films.get(film.getId());
        if (validator(film) && checkAddValidData(film)) {
            updateFilm.setName(film.getName());
            updateFilm.setDescription(film.getDescription());
            updateFilm.setReleaseDate(film.getReleaseDate());
            updateFilm.setDuration(film.getDuration());
            films.put(film.getId(), updateFilm);
            log.debug("Обновлен фильм {}.", updateFilm);
            return updateFilm;
        }
        return film;
    }

    public void removeFilm(Integer id) {
        films.remove(id);
        log.debug("Удален фильм {}.", id);
    }

    private boolean checkValidData(Film film) {
        if (film.getDescription().length() > 200) {
            log.error("Описание больше 200 символов.");
            throw new ValidationException("Описание не должно составлять больше 200 символов.");
        }
        if (film.getDescription().isBlank() || film.getDescription().isEmpty()) {
            log.error("Описание пустое или состоит из пробелов.");
            throw new ValidationException("Описание не должно быть пустым или состоять из пробелов.");
        }
        if (film.getReleaseDate().isBefore(movieDay)) {
            log.error("Дата выхода фильма в прокат не может быть раньше 28.12.1895.");
            throw new ValidationException("Дата выхода фильма в прокат не может быть раньше 28.12.1895.");
        }
        if (film.getDuration() <= 0) {
            log.error("Продолжительность фильма отрицательное число или равно нулю.");
            throw new ValidationException("Продолжительность фильма должна быть больше нуля.");
        }
        return true;
    }

    private boolean checkAddValidData(Film film) {
        if (checkValidData(film)) {
            if (film.getName().isBlank()) {
                log.error("Название пустое.");
                throw new ValidationException("Название не может быть пустым.");
            }
        }
        return true;
    }

    private boolean validator(Film film) {
        if (checkValidData(film)) {
            if (film.getId() == null) {
                log.error("Не введен id.");
                throw new ValidationException("Нужно задать id.");
            }
            if (!films.containsKey(film.getId())) {
                log.error("Неверный id.");
                throw new ValidationException("Фильма с id" + film.getId() + " нет.");
            }
        }
        return true;
    }
}


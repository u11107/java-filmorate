package ru.yandex.practicum.filmorate.controllers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class FilmController {
    private final Map<Integer, Film> movie = new HashMap<>();
    @Getter
    private static final LocalDate EARLY_DATE = LocalDate.of(1900, 1, 1);
    private Integer id;

    @GetMapping("/films")
    public Map getAllFilm() throws ValidationException {
        log.info("Количство фильмов {}", movie.size());
        return movie;
    }

    @PostMapping(value = "/films")
    public Film addFilm(@Valid @RequestBody Film film) throws ValidationException {
        validationFilm(film);
        film.setId(id++);
        movie.put(film.getId(), film);
        log.info("Добавлен новый фильм {}, id={}", film.getName(), film.getId());
        return film;
    }

    @PutMapping(value = "/films")
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        validationFilm(film);
        movie.put(film.getId(), film);
        log.info("Фильм перзеписан");
        return film;
    }

    public void validationFilm(Film film) {
        if (film.getReleaseDate().isBefore(getEARLY_DATE())) {
            log.warn("Произошла ошибка  при создании фильма");
            throw new ValidationException("Тогда еще не было фильмов");
        }
    }
}

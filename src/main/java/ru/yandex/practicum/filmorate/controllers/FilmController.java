package ru.yandex.practicum.filmorate.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;

@Slf4j
@RequestMapping("/films")
@RestController
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    private final FilmStorage filmStorage;

    @GetMapping
    public Collection<Film> allFilms() {
        return filmStorage.getAllFilms().values();
    }

    @PostMapping
    public Film add(@RequestBody Film film) {
        return filmStorage.createFilm(film);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return filmStorage.updateFilm(film);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public Collection<Film> getTopTenFilms(@RequestParam(defaultValue = "10") Integer count) {
        return filmService.getTopTenFilms(count);
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable Integer id) {
        return filmStorage.getFilmById(id);
    }
}
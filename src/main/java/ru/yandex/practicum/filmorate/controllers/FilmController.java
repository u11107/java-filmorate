package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.filmorate.exception.IncorrectParameterException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/films")
    public Collection<Film> getAllFilms() {
        log.debug("Текущее количество фильмов: {}", filmService.getAllFilms().size());
        return filmService.getAllFilms();
    }

    @PostMapping("/films")
    public Film addFilm(@Valid @RequestBody Film film) throws ValidationException {
        return filmService.addFilm(film);
    }

    @PutMapping("/films")
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        return filmService.updateFilm(film);
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable long id) {
        filmService.deleteFilm(id);
    }

    @GetMapping("/films/{id}")
    public Film getFilm(@PathVariable long id) { return filmService.getFilm(id); }

    @PutMapping("/films/{id}/like/{userId}")
    public void putLike(@PathVariable long id, @PathVariable int userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable long id, @PathVariable long userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/films/popular")
    public Collection<Film> getPopular(@RequestParam(defaultValue = "10") int count) {
        if (count <= 0) {
            throw new IncorrectParameterException("count");
        }
        return filmService.getPopular(count);
    }

    @GetMapping("/genres/{id}")
    public Collection<Genre> getGenre(@PathVariable(required = false) Integer id) {
        return filmService.getGenres(id);
    }

    @GetMapping("/mpa/{id}")
    public Collection<Rating> getMpaa(@PathVariable(required = false) Integer id) {
        return filmService.getMpaa(id);
    }
}
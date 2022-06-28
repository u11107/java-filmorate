package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    //GET /genres — для получения списка жанров.
    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    //GET /genres/{id} — для получение жанров по id.
    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Integer id) {
        return genreService.getGenreById(id);
    }
}

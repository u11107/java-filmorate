package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Film {

    private String name;
    private long id;
    @Size(min = 1, max = 200)
    private String description;
    private LocalDate releaseDate;
    private int duration;
    @NotNull
    private Rating mpa;
    private Set<Genre> genre;

}


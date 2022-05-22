package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Film {

    private int id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String name;
    @NotBlank
    private String description;
    private LocalDate releaseDate;
    @Min(value = 0, message = "Продолжительность фильма не может иметь отрицательное значение")
    private int duration;
    private Set<Integer> likes;

    public Film(int id, String name, String description, LocalDate releaseDate, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.likes = likes;
    }
}
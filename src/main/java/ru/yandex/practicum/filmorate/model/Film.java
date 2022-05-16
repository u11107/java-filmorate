package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Film {

    private int id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String name;
    @NotBlank
    private String description;
    private LocalDate releaseDate;
    @Min(value = 0, message = "Продолжительность фильма не может иметь отрицательное значение")
    private int duration;
}
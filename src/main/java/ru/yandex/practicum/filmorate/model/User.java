package ru.yandex.practicum.filmorate.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class User {

    private int id;
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String login;
    private LocalDate birthday;
}
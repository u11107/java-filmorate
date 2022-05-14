package ru.yandex.practicum.filmorate.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class User {

    private int id;
    @Email(message = "Введите email формата emeil@e")
    private String email;
    @NotNull
    private String name;
    @NotEmpty
    private String login;
    private LocalDate birthday;


}
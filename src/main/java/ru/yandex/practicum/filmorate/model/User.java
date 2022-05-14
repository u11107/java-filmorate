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
    @Email(message = "Введите email формата emeil@e")

    private String email;


    private String name;


    private String login;
    private LocalDate birthday;


}
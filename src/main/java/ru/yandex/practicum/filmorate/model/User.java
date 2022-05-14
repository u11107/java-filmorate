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
    @NotBlank
    private String email;
    @Size(max = 30, message = "Имя не может быть больше 30 символов")
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 30, message = "Имя не может быть больше 30 символов")
    private String login;
    private LocalDate birthday;


}
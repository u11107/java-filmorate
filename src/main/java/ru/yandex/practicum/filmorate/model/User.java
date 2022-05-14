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
    @Email
    @NotNull
    private String email;
    @Size(max = 30, message = "Имя не может быть больше 30 символов")
    @NotNull
    private String name;
    @NotNull
    @Size(max = 30, message = "Имя не может быть больше 30 символов")
    private String login;
    private LocalDate birthday;


}
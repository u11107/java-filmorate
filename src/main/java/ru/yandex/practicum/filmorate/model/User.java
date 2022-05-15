package ru.yandex.practicum.filmorate.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class User {

    private int id;
    @Email
    private String email;
    @Size(max = 30, message = "Имя может быть не больше тридцати символов")
    private String name;
    private String login;
    private LocalDate birthday;


}
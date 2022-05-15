package ru.yandex.practicum.filmorate.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class User {

    private int id;
    @Email
    private String email;
    private String name;
    private String login;
    private LocalDate birthday;


}
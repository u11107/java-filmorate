package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    private int id;
    @Email
    private String email;
    @Size(max = 30, message = "Имя может быть не больше тридцати символов")
    private String name;
    @Size(max = 30, message = "Имя может быть не больше тридцати символов")
    private String login;
    private Set<Integer> friends;

    public User(int id, String email, String name, String login, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.login = login;
        this.birthday = birthday;
        this.friends = friends;
    }
}
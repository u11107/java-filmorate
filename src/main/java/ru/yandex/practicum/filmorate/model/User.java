package ru.yandex.practicum.filmorate.model;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
@Data
public class User {

    private int id;
    @Email(message = "Введите email формата emeil@e")
    @NotEmpty(message = "Поле не может быть пустым")
    @NotBlank
    private String email;
    @NotNull
    @Size(max = 30, message = "Имя не может быть больше 30 символов")
    @Min(value = 0, message = "Имя не может начинается с отрицательного значения")
    @NotBlank
    private String name;
    @NotBlank
    @NotNull
    @Size(max = 30, message = "Имя не может быть больше 30 символов")
    @Min(value = 0, message = "Имя не может начинается с отрицательного значения")
    private String login;
    @Min(value = 0, message = "нельзя ввести отрицательную дату рождения")
    private LocalDateTime birthday;

    public User(int id, String email, String name, String login, LocalDateTime birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.login = login;
        this.birthday = birthday;
    }
}
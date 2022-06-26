package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Data
@Builder
public class User {

    @NonNull
    @NotBlank
    private String login;
    private String name;
    private long id;
    @Email
    private String email;
    private LocalDate birthday;
    public Map<String, Object> toMap() {
        Map<String, Object> dataVal = new HashMap<>();
        dataVal.put("name", name);
        dataVal.put("login", login);
        dataVal.put("email", email);
        dataVal.put("birthday", birthday);
        return dataVal;
    }
}
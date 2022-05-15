package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    int id = 1;

    @GetMapping("/users")
    public Map getAllFilm() throws ValidationException {
        log.info("Количство пользователей {}", users.size());
        return users;
    }

    @PostMapping(value = "/users")
    public User addUser(@Valid @RequestBody User user) throws ValidationException {
        log.error(String.valueOf(user));
        validate(user); // проверка даты рождения пользователя
        user.setId(id++);
        log.info("Пользователь {} успешно добавлен", user.getLogin());
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping(value = "/users")
    public User update(@Valid @RequestBody User user) throws ValidationException {
        log.error(String.valueOf((user)));
        validate(user);
        users.put(user.getId(), user);
        log.info("Добавлен пользователь");
        return user;
    }


//    public  void validationUser(User user) {
//        if (user.getBirthday().isBefore(LocalDate.now())) {
//            log.warn("Вы ввели не верную дату рождения");
//            throw new ValidationException("Вы ввели не верную дату рождения");
//        }
//        if (user.getName() == null || user.getName().isBlank()) {
//            user.setName(user.getLogin());
//        }
//    }

    public void validate(User user)  {
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            log.error("Логин {} содержит пробелы или пустой", user.getLogin());
            throw new ValidationException("Логин содержит пробелы или пустой");
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            log.error("Ошибка формата email '{}'", user.getEmail());
            throw new ValidationException("Ошибка формата email");
        }
        if (user.getBirthday().isBefore(LocalDate.now())) {
            log.error("Ошибка даты рождения '{}'", user.getBirthday());
            throw new ValidationException("Ошибка в поле дата рождения");
        }
    }
}

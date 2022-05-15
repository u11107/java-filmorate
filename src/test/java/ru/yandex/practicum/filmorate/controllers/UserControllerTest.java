package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void shouldValidationExceptionLoginIsBlank() {
        User user = new User(2,"test@test.ru", "", "name",
                LocalDate.of(1987, Month.MAY,3));
        assertThrows(ValidationException.class, () -> userController.validate(user));
    }

    @Test
    void shouldValidationExceptionLoginContainsSpace() {
        User user = new User(2,"test@test.ru", "login login", "name",
                LocalDate.of(1987, Month.MAY,3));
        assertThrows(ValidationException.class, () -> userController.validate(user));
    }

    @Test
    void shouldUseLoginIfNameIsBlank() {
        User user = new User(2,"test@test.ru", "login", "",
                LocalDate.of(1987, Month.MAY,3));
        userController.validate(user);
        assertEquals(user.getName(), user.getLogin(), "Не используется login в качестве name");
    }

    @Test
    void shouldValidationExceptionIfBirthDayIsFuture() {
        User user = new User(2,"test@test.ru", "login", "name",
                LocalDate.of(2023, Month.MAY,3));
        assertThrows(ValidationException.class, () -> userController.validate(user));
    }

    @Test
    void shouldValidationExceptionWrongEmailFormat() {
        User user = new User(2,"test.test.ru", "login", "name",
                LocalDate.of(2023, Month.MAY,3));
        User userBlankEmail = new User(3,"", "login", "name",
                LocalDate.of(2023, Month.MAY,3));
        assertThrows(ValidationException.class, () -> userController.validate(user));
        assertThrows(ValidationException.class, () -> userController.validate(userBlankEmail));
    }
}

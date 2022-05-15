package ru.yandex.practicum.filmorate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    UserController userController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createValidUserTest() throws Exception {
        User user = new User(2,"eeei@inbox.ru","name", "login", LocalDate.of(2022,10,12));
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);
        this.mockMvc.perform(post("/users").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createUserInValidEmail() throws Exception {
        User user = new User(2,"hdhdkfgfg","name", "login", LocalDate.of(2022,10,12));
        String json = objectMapper.writeValueAsString(user);
        this.mockMvc.perform(post("/users").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void  updateUserTest() throws Exception {
        User user = new User(2,"hdhdkfgfg","name", "login",
                LocalDate.of(2022,10,12));
        User user1 = new User(2,"eeei@inbox.ru","NAME", "login",
                LocalDate.of(2022,10,12));
        String json = objectMapper.writeValueAsString(user1);
        this.mockMvc.perform(put("/users").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

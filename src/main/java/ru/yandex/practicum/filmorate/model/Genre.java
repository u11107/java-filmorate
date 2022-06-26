package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Genre {

    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
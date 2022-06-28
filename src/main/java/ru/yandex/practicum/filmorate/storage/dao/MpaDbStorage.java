package ru.yandex.practicum.filmorate.storage.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class MpaDbStorage implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_MPA = "SELECT * FROM PUBLIC.MPA ORDER BY ID;";
    private static final String SQL_GET_BY_ID_MPA = "SELECT * FROM PUBLIC.MPA WHERE ID = ?;";

    @Autowired
    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mpa> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_MPA, (rs, rowNum) -> makeMpa(rs));
    }

    @Override
    public Optional<Mpa> findById(Integer id) {
        List<Mpa> mpaList = jdbcTemplate.query(SQL_GET_BY_ID_MPA, (rs, rowNum) -> makeMpa(rs), id);
        if (mpaList.isEmpty()) {
            log.info("MPA с идентификатором {} не найден.", id);
            return Optional.empty();
        } else {
            Mpa mpa = mpaList.get(0);
            log.info("MPA жанр: {} {}", mpa.getId(), mpa.getName());
            return Optional.of(mpa);
        }
    }

    private Mpa makeMpa(ResultSet rs) throws SQLException {
        return new Mpa(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
        );
    }
}

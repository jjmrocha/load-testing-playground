package net.uiqui.load.apps.control;

import net.uiqui.load.apps.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AppsStore {
    private static final String COUNT_BY_PK = "SELECT count(*) FROM application WHERE app_id = ?";
    private static final String FIND_BY_PK = "SELECT * FROM application WHERE app_id = ?";
    private static final String DELETE_BY_PK = "DELETE FROM application WHERE app_id = ?";
    private static final String UPDATE_BY_PK = "UPDATE application SET name = ? WHERE app_id = ?";
    private static final String INSERT_NEW = "INSERT INTO application (app_id, name) VALUES (?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Integer ONE = 1;
    private static final RowMapper<Application> APP_MAPPER = (resultSet, rowNum) -> Application.Builder.anApplication()
            .withAppId(resultSet.getInt("app_id"))
            .withName(resultSet.getString("name"))
            .build();

    public boolean exists(final int appId) {
        return ONE.equals(jdbcTemplate.queryForObject(COUNT_BY_PK, Integer.class, appId));
    }

    public Application findByAppId(final int appId) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_PK, APP_MAPPER, appId);
        } catch (final EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean deleteByAppId(final int appId) {
        return jdbcTemplate.update(DELETE_BY_PK, appId) == 1;
    }

    public void update(final Application application) {
        jdbcTemplate.update(UPDATE_BY_PK, application.getName(), application.getAppId());
    }

    public void create(final Application application) {
        jdbcTemplate.update(INSERT_NEW, application.getAppId(), application.getName());
    }
}

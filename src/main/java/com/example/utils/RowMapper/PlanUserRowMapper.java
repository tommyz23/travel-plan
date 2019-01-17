package com.example.utils.RowMapper;

import com.example.entity.PlanUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanUserRowMapper implements RowMapper<PlanUser> {
    @Override
    public PlanUser mapRow(ResultSet resultSet, int i) throws SQLException {
        PlanUser planUser = new PlanUser();
        planUser.setPlanId(resultSet.getInt("plan_id"));
        planUser.setRole(resultSet.getBoolean("role"));
        planUser.setUserId(resultSet.getInt("user_id"));
        return planUser;
    }
}

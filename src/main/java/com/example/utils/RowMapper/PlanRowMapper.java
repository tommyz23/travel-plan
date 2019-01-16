package com.example.utils.RowMapper;

import com.example.entity.Plan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanRowMapper implements RowMapper<Plan> {
    @Override
    public Plan mapRow(ResultSet resultSet, int i) throws SQLException {
        Plan plan = new Plan();
        plan.setId(resultSet.getInt("id"));
        plan.setName(resultSet.getString("name"));
        plan.setPeopleNumber(resultSet.getInt("people_number"));
        plan.setSite(resultSet.getString("site"));
        plan.setTime(resultSet.getDate("time"));
        return plan;
    }
}

package com.example.utils.RowMapper;

import com.example.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("ID"));
        user.setName(resultSet.getString("NAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
        user.setRealName(resultSet.getString("REAL_NAME"));
        user.setScore(resultSet.getInt("SCORE"));
        return user;
    }
}

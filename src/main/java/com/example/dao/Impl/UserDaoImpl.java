package com.example.dao.Impl;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.utils.RespCode;
import com.example.utils.RespEntity;
import com.example.utils.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RespEntity checkLogin(String phone_number, String password) {
        String sql = "SELECT * FROM USER WHERE PHONE_NUMBER=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), phone_number);
            if (user.getPassword().equals(password))
                return new RespEntity(RespCode.SUCCESS, user);
            else
                return new RespEntity(RespCode.WARN, "密码错误");
        }catch (DataAccessException e){
            System.out.println(e);
            return new RespEntity(RespCode.WARN);
        }
    }
}

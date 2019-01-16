package com.example.dao;

import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    RespEntity checkLogin(String phone_number, String password);
}

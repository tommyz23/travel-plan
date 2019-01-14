package com.example.dao;

import com.example.utils.RespEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    RespEntity checkLogin(String phone_number, String password);
}

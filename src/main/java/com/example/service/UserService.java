package com.example.service;

import com.example.utils.RespEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    RespEntity checkLogin(String phone_nuber, String password);
}

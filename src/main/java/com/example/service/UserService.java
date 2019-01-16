package com.example.service;

import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    RespEntity checkLogin(String phone_nuber, String password);
}

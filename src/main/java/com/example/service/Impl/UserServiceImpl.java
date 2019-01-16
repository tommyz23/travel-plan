package com.example.service.Impl;

import com.example.dao.UserDao;
import com.example.service.UserService;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public RespEntity checkLogin(String phone_nuber, String password) {
        return userDao.checkLogin(phone_nuber, password);
    }
}

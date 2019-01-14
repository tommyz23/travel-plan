package com.example.controller;


import com.example.service.UserService;
import com.example.utils.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/login")
    public RespEntity checkLogin(HttpServletRequest request){
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        return userService.checkLogin(phoneNumber, password);
    }
}

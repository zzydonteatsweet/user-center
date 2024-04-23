package com.zzy.usercenter.web.controller;

import com.zzy.usercenter.web.pojo.Request.LoginRequest;
import com.zzy.usercenter.web.pojo.Request.RegisterRequest;
import com.zzy.usercenter.web.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public String userRegister(@RequestBody RegisterRequest registerRequest) {
        return userService.userRegister(registerRequest);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody LoginRequest loginRequest) {
        return userService.userLogin(loginRequest);
    }
}

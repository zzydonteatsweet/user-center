package com.zzy.usercenter.web.service;

import com.zzy.usercenter.web.pojo.Request.LoginRequest;
import com.zzy.usercenter.web.pojo.Request.RegisterRequest;
import com.zzy.usercenter.web.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author T041018
* @description 针对表【user】的数据库操作Service
* @createDate 2024-04-22 23:17:24
*/
public interface UserService extends IService<User> {

    String userRegister(RegisterRequest registerRequest);
    String userLogin(LoginRequest loginRequest);
}

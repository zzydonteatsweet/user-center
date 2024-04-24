package com.zzy.usercenter.web.controller;

import com.zzy.usercenter.web.commons.BaseResponse;
import com.zzy.usercenter.web.commons.ErrorCode;
import com.zzy.usercenter.web.commons.ResultUtils;
import com.zzy.usercenter.web.pojo.Request.LoginRequest;
import com.zzy.usercenter.web.pojo.Request.RegisterRequest;
import com.zzy.usercenter.web.pojo.User;
import com.zzy.usercenter.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public BaseResponse<User> userLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) {
        String useraccount = loginRequest.getUseraccount();
        String password = loginRequest.getPassword();

        if(StringUtils.isAnyBlank(useraccount, password)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(useraccount, password, httpServletRequest);
        return ResultUtils.success(user);
    }
}

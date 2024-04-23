package com.zzy.usercenter.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.usercenter.web.pojo.Request.LoginRequest;
import com.zzy.usercenter.web.pojo.Request.RegisterRequest;
import com.zzy.usercenter.web.service.UserService;
import com.zzy.usercenter.web.pojo.User;
import com.zzy.usercenter.web.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Queue;

/**
 * @author T041018
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-04-22 23:17:24
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    protected static String SALT = "zzy";
    @Resource
    UserMapper userMapper;

    /*
        账号长度
        账号已经存在
        两次密码不同
        密码过于简单
     */
    @Override
    public String userRegister(RegisterRequest data) {
        String useraccount = data.getUseraccount();
        String username = data.getUsername();
        String password = data.getPassword();
        String confirmPassword = data.getConfirmPassword();
        String avatarUrl = data.getAvatarUrl();
        Integer gender = data.getGender();

        if (useraccount.length() < 8) return new String("The length of accoung is too short");
        if (!password.equals(confirmPassword)) return "两次密码不一致";
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("useraccount", useraccount);
        if (userMapper.exists(queryWrapper)) return "账号已存在";

        //  todo 密码过短暂时还没写
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        System.out.println("password is" + encryptPassword);
        User user = new User();
        user.setUsername(username);
        user.setUseraccount(useraccount);
        user.setAvatarurl(avatarUrl);
        user.setGender(gender);
        user.setPassword(encryptPassword);
        user.setUserstatus(0);
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setUserrole(1);

        userMapper.insert(user);
        return "注册成功";
    }

    @Override
    public String userLogin(LoginRequest loginRequest) {
        String useraccount = loginRequest.getUseraccount();
        String password = loginRequest.getPassword();

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("useraccount", useraccount);
        queryWrapper.eq("password", encryptPassword);

        User user = userMapper.selectOne(queryWrapper);
        if(user == null) {
            log.info("user login failed");
            return "密码或者账户错误";
        }
        return "登录成功";
    };


}





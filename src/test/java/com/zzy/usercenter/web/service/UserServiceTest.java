package com.zzy.usercenter.web.service;

import com.zzy.usercenter.web.pojo.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@MapperScan("com.zzy.usercenter.web.mapper")
class UserServiceTest {

    @Resource
    UserService userService;
    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("root");
        user.setUseraccount("root");
        user.setAvatarurl("");
        user.setGender(0);
        user.setPassword("1111");
        user.setUserstatus(0);
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setUserrole(0);

        userService.save(user);
    }

}
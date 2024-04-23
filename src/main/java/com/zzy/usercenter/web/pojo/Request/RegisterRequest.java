package com.zzy.usercenter.web.pojo.Request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {
    private String useraccount;
    private String username;
    private String password;
    private String confirmPassword;
    private String avatarUrl;
    private Integer gender;
}

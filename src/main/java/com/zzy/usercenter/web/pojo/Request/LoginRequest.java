package com.zzy.usercenter.web.pojo.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String useraccount;
    private String password;
}

package com.mredust.mredustusersystem.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: UserLoginRequest
 * @author: Mredust
 * @date: 2025/3/31 20:35
 */
@Data
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String account;
    private String password;
    private String checkPassword;
}

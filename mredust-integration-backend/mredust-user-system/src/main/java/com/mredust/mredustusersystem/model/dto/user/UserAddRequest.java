package com.mredust.mredustusersystem.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: UserAddRequest
 * @author: Mredust
 * @date: 2025/4/1 13:24
 */
@Data
public class UserAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 账号
     */
    private String account;
    
    /**
     * 密码
     */
    private String password;
    
    
    /**
     * 用户昵称
     */
    private String username;
    
}

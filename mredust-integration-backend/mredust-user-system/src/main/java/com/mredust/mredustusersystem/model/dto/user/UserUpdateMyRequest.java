package com.mredust.mredustusersystem.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: UserUpdateMyRequest
 * @author: Mredust
 * @date: 2025/4/1 13:41
 */
@Data
public class UserUpdateMyRequest implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String username;
    
    /**
     * 性别（0-女 1-男 2-未知）
     */
    private Integer sex;
    
    private static final long serialVersionUID = 1L;
}

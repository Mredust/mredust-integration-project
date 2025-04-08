package com.mredust.mredustusersystem.model.dto.user;

import com.mredust.mredustusersystem.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description: UserQueryRequest
 * @author: Mredust
 * @date: 2025/4/1 13:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
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
    
    /**
     * 性别（0-女 1-男 2-未知）
     */
    private Integer sex;
    
    
    /**
     * 用户角色（0-普通用户 1-管理员）
     */
    private Integer role;
    
}

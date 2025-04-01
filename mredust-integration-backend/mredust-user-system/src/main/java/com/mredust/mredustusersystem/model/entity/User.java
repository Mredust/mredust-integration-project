package com.mredust.mredustusersystem.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    
    /**
     * 用户账号
     */
    private String account;
    
    /**
     * 用户密码
     */
    private String password;
    
    /**
     * 用户昵称
     */
    private String username;
    
    /**
     * 用户性别（0-女 1-男 2-保密）
     */
    private Integer sex;
    
    /**
     * 用户角色（0-普通用户 1-管理员）
     */
    private Integer role;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 是否删除（0-否 1-是）
     */
    @TableLogic
    private Integer isDelete;
    
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

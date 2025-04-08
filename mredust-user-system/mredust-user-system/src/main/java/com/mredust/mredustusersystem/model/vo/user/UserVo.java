package com.mredust.mredustusersystem.model.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mredust.mredustusersystem.model.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: UserVo
 * @author: Mredust
 * @date: 2025/3/31 20:37
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户账号
     */
    private String account;
    
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
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 是否删除（0-否 1-是）
     */
    private Integer isDelete;
    
    public UserVo(User user) {
        if (user != null) {
            BeanUtils.copyProperties(user, this);
        }
    }
    
    @JsonIgnore
    public UserVo getUserVo() {
        return this;
    }
}

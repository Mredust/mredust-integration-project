package com.mredust.mredustusersystem.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mredust.mredustusersystem.model.dto.user.UserAddRequest;
import com.mredust.mredustusersystem.model.dto.user.UserQueryRequest;
import com.mredust.mredustusersystem.model.entity.User;
import com.mredust.mredustusersystem.model.vo.user.UserVo;

/**
 * @author Mredust
 * @description 针对表【user】的数据库操作Service
 * @createDate 2025-03-31 18:28:00
 */
public interface UserService extends IService<User> {
    long userRegister(String account, String password, String checkPassword);
    
    UserVo userLogin(String account, String password);
    
    /**
     * 新增用户
     *
     * @param userAddRequest 用户信息
     * @return 用户 id
     */
    long addUser(UserAddRequest userAddRequest);
    
    Page<User> getUserListByPage(UserQueryRequest userQueryRequest);
}

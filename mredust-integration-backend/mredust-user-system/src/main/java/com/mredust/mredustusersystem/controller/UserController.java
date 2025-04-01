package com.mredust.mredustusersystem.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mredust.mredustusersystem.common.BaseResponse;
import com.mredust.mredustusersystem.common.DeleteRequest;
import com.mredust.mredustusersystem.common.ResponseCode;
import com.mredust.mredustusersystem.common.Result;
import com.mredust.mredustusersystem.config.redis.RedisService;
import com.mredust.mredustusersystem.exception.BusinessException;
import com.mredust.mredustusersystem.model.dto.user.*;
import com.mredust.mredustusersystem.model.entity.User;
import com.mredust.mredustusersystem.model.vo.user.UserVo;
import com.mredust.mredustusersystem.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.mredust.mredustusersystem.constant.UserConstant.USER_LOGIN_KEY;

/**
 * @description: UserController
 * @author: Mredust
 * @date: 2025/3/31 20:29
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;
    
    /**
     * 用户注册
     *
     * @param userRequest 用户注册请求
     * @return 用户 id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRequest userRequest) {
        if (userRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        String account = userRequest.getAccount();
        String password = userRequest.getPassword();
        String checkPassword = userRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        long userId = userService.userRegister(account, password, checkPassword);
        return Result.success(userId);
    }
    
    /**
     * 用户登录
     *
     * @param userRequest 用户登录请求
     * @return 用户信息
     */
    @PostMapping("/login")
    public BaseResponse<UserVo> userLogin(@RequestBody UserRequest userRequest) {
        if (userRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        String account = userRequest.getAccount();
        String password = userRequest.getPassword();
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        UserVo userVo = userService.userLogin(account, password);
        return Result.success(userVo);
    }
    
    
    /**
     * 用户登出
     *
     * @return 是否登出成功
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(@RequestParam Long userId) {
        boolean flag = redisService.deleteCacheObject(USER_LOGIN_KEY + userId);
        return flag ? Result.success() : Result.fail();
    }
    
    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     */
    @GetMapping("/get/login-user")
    public BaseResponse<UserVo> getLoginUser(@RequestParam Long userId) {
        Object cacheObject = redisService.getCacheObject(USER_LOGIN_KEY + userId);
        UserVo userVo = JSONUtil.toBean(cacheObject.toString(), UserVo.class);
        return Result.success(userVo);
    }
    
    
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        if (userAddRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        String account = userAddRequest.getAccount();
        String password = userAddRequest.getPassword();
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        long addResult = userService.addUser(userAddRequest);
        return Result.success(addResult);
    }
    
    @DeleteMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Long id = deleteRequest.getId();
        User user = userService.getById(id);
        if (user == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        boolean removeResult = userService.removeById(id);
        return removeResult ? Result.success("删除成功") : Result.fail("删除失败");
    }
    
    @PutMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        String account = userUpdateRequest.getAccount();
        String password = userUpdateRequest.getPassword();
        String username = userUpdateRequest.getUsername();
        if (StringUtils.isAnyBlank(account, password, username)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean updateResult = userService.updateById(user);
        return updateResult ? Result.success("更新成功") : Result.fail("更新失败");
    }
    
    @PutMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        String username = userUpdateMyRequest.getUsername();
        if (StringUtils.isAnyBlank(username)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        boolean updateResult = userService.updateById(user);
        return updateResult ? Result.success("更新成功") : Result.fail("更新失败");
    }
    
    @GetMapping("/get")
    public BaseResponse<UserVo> getUser(@RequestParam Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        User user = userService.getById(id);
        if (user == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        UserVo userVo = new UserVo(user).getUserVo();
        return Result.success(userVo);
    }
    
    /**
     * 分页获取用户列表
     *
     * @param userQueryRequest 用户查询参数
     * @return 用户列表
     */
    @PostMapping("/list")
    public BaseResponse<Page<User>> getUserListByPage(@RequestBody UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        Page<User> userPage = userService.getUserListByPage(userQueryRequest);
        return Result.success(userPage);
    }
}

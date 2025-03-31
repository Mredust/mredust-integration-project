package com.mredust.mredustusersystem.controller;

import com.mredust.mredustusersystem.common.BaseResponse;
import com.mredust.mredustusersystem.common.ResponseCode;
import com.mredust.mredustusersystem.common.Result;
import com.mredust.mredustusersystem.exception.BusinessException;
import com.mredust.mredustusersystem.model.dto.user.UserRequest;
import com.mredust.mredustusersystem.model.vo.user.UserVo;
import com.mredust.mredustusersystem.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * @param userRequest
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
    
}

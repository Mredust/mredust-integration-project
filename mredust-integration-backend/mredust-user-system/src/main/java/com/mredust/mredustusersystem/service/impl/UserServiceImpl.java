package com.mredust.mredustusersystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.mredust.mredustusersystem.common.ResponseCode;
import com.mredust.mredustusersystem.exception.BusinessException;
import com.mredust.mredustusersystem.model.entity.User;
import com.mredust.mredustusersystem.model.vo.user.UserVo;
import com.mredust.mredustusersystem.service.UserService;
import com.mredust.mredustusersystem.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Pattern;

import static com.mredust.mredustusersystem.constant.UserConstant.*;

/**
 * @author Mredust
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-03-31 18:28:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public long userRegister(String account, String password, String checkPassword) {
        // 二次校验
        if (!Pattern.matches(ACCOUNT_REGEX, account)) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "不能包含特殊字符");
        }
        if (account.length() < USER_ACCOUNT_MIN_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "用户账号过短");
        }
        if (password.length() < USER_PASSWORD_MIN_LENGTH || checkPassword.length() < USER_PASSWORD_MIN_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "用户密码过短");
        }
        if (!password.equals(checkPassword)) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "两次密码不一致");
        }
        
        synchronized (account.intern()) {
            Long count = Db.lambdaQuery(User.class).eq(User::getAccount, account).count();
            if (count > 0) {
                throw new BusinessException(ResponseCode.PARAMS_ERROR, "账号已存在");
            }
            // 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
            User user = new User();
            user.setAccount(account);
            user.setPassword(encryptPassword);
            user.setUsername("用户" + account);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ResponseCode.SYSTEM_ERROR, "注册失败");
            }
            return user.getUserId();
        }
    }
    
    @Override
    public UserVo userLogin(String account, String password) {
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(ResponseCode.PARAMS_NULL);
        }
        if (account.length() < USER_ACCOUNT_MIN_LENGTH || password.length() < USER_PASSWORD_MIN_LENGTH || !Pattern.matches(ACCOUNT_REGEX, account)) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "用户账号或密码错误");
        }
        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        User user = Db.lambdaQuery(User.class)
                .eq(User::getAccount, account)
                .eq(User::getPassword, encryptPassword)
                .one();
        if (user == null) {
            throw new BusinessException(ResponseCode.PARAMS_NULL, "账户不存在或密码错误");
        }
        return new UserVo().getUserVo(user);
    }
}

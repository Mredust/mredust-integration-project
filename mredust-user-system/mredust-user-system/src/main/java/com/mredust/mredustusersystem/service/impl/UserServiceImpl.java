package com.mredust.mredustusersystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.mredust.mredustusersystem.common.ResponseCode;
import com.mredust.mredustusersystem.config.redis.RedisService;
import com.mredust.mredustusersystem.exception.BusinessException;
import com.mredust.mredustusersystem.mapper.UserMapper;
import com.mredust.mredustusersystem.model.dto.user.UserAddRequest;
import com.mredust.mredustusersystem.model.dto.user.UserQueryRequest;
import com.mredust.mredustusersystem.model.entity.User;
import com.mredust.mredustusersystem.model.vo.user.UserVo;
import com.mredust.mredustusersystem.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;
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
    @Resource
    private RedisService redisService;
    
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
            String substring = UUID.randomUUID().toString().substring(0, 6);
            user.setUsername("用户" + substring);
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
        UserVo userVo = new UserVo(user).getUserVo();
        String jsonStr = JSONUtil.toJsonStr(userVo);
        redisService.setCacheObject(USER_LOGIN_KEY + user.getUserId(), jsonStr);
        return userVo;
    }
    
    /**
     * 新增用户
     *
     * @param userAddRequest 用户信息
     * @return 用户 id
     */
    @Override
    public long addUser(UserAddRequest userAddRequest) {
        String account = userAddRequest.getAccount();
        String password = userAddRequest.getPassword();
        String username = userAddRequest.getUsername();
        if (!Pattern.matches(ACCOUNT_REGEX, account) || (StringUtils.isNotBlank(username) && !Pattern.matches(USERNAME_REGEX, username))) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "不能包含特殊字符");
        }
        if (account.length() < USER_ACCOUNT_MIN_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "用户账号过短");
        }
        if (password.length() < USER_PASSWORD_MIN_LENGTH) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "用户密码过短");
        }
        Long count = Db.lambdaQuery(User.class).eq(User::getAccount, account).count();
        if (count > 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "账号已存在");
        }
        User user = new User();
        BeanUtil.copyProperties(userAddRequest, user);
        user.setAvatarUrl(DEFAULT_AVATAR);
        boolean result = this.save(user);
        if (!result) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "添加用户失败");
        }
        return user.getUserId();
    }
    
    @Override
    public Page<User> getUserListByPage(UserQueryRequest userQueryRequest) {
        Long id = userQueryRequest.getId();
        Integer sex = userQueryRequest.getSex();
        Integer role = userQueryRequest.getRole();
        String account = userQueryRequest.getAccount();
        String username = userQueryRequest.getUsername();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        long pageNum = userQueryRequest.getPageNum();
        long pageSize = userQueryRequest.getPageSize();
        
        Page<User> userPage = new Page<>(pageNum, pageSize);
        return Db.lambdaQuery(User.class)
                .eq(id != null, User::getUserId, id)
                .eq(role != null, User::getRole, role)
                .eq(sex != null, User::getSex, sex)
                .like(StringUtils.isNotBlank(account), User::getAccount, account)
                .like(StringUtils.isNotBlank(username), User::getUsername, username)
                .last(StringUtils.isNotBlank(sortField), "order by " + sortField + " " + sortOrder)
                .page(userPage);
    }
}

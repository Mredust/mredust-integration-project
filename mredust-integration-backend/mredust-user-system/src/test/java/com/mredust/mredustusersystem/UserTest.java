package com.mredust.mredustusersystem;

import com.mredust.mredustusersystem.model.vo.user.UserVo;
import com.mredust.mredustusersystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @description: com.mredust.mredustusersystem.UserTest
 * @author: Mredust
 * @date: 2025/3/31 21:46
 */
@SpringBootTest
class UserTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    void testUserLogin() {
        String account = "admin";
        String password = "admin";
        UserVo userVo = userService.userLogin(account, password);
        System.out.println(userVo);
        
    }
    
    @Test
    void testUserRegister() {
        String substring = UUID.randomUUID().toString().substring(0, 6);
        String account = "user" + substring;
        String defaultPassword = "123456";
        long userId = userService.userRegister(account, defaultPassword, defaultPassword);
        System.out.println(userId);
    }
    
    public static void main(String[] args) {
        String s = DigestUtils.md5DigestAsHex("mredustadmin".getBytes());
        // acbd988c6cc18576a364047bd2fdcf70
    }
}

package com.kx;

import com.kx.pojo.User;
import com.kx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
    //测试新增用户
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("kongxin");
        user.setPassword("123456");
        user.setNickname("空心");
        user.setPhoto("upload/头像.jpg");
        user.setIntroduce("大三学生");
        user.setEmail("");
        userService.insertUser(user);
    }


    //测试密码加密
    @Test
    public void testPass(){
        String pass = "xin.0806";
        BCryptPasswordEncoder bcryptPasswordEncoder  = new BCryptPasswordEncoder();
//        String hashPass = bcryptPasswordEncoder.encode(pass);//加密
//        System.out.println("密码："+hashPass);
        boolean f = bcryptPasswordEncoder.matches("xin.0806","$2a$10$OZRPUuKyM1EJeE1oWksnf.JmXiwZPjrhc5V4OwwXJPIlMCEAaVhjK");//比较密码是否正确？
        System.out.println(" 比较结果"+f);
    }

    //测试查询用户
    @Test
    public void testLoadUser(){
        UserDetails user = userService.loadUserByUsername("kongxin");
        System.out.println(user);
    }
}

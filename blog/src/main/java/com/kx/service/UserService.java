/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.service;


import com.kx.mapper.UserMapper;
import com.kx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author chnewei
 */

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
           throw new UsernameNotFoundException("身份错误！"); //抛出异常
        }
        return user;
    }

    public User selectUserByUsername(String username){
        return userMapper.selectUserByUsername(username);
    }

    //查询用户，比对密码
    public String selectpwd(String username){
        User user = userMapper.selectUserByUsername(username);
        return user.getPassword();
    }

    public void insertUser(User user){
        //登录密码加密，数据库中加密存放
        encryptPassword(user);
        //设置默认角色，默认为普通用户
        user.setRole("visitor");
        //设置当前日期为用户建档日期
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String createTime = dateFormat.format(now);//时间格式化后放入字符串中
        user.setCreated(createTime);
        //新增用户
        userMapper.insertUser(user);
    }

    //加密
    private void encryptPassword(User user) {
        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);
    }

    //更改信息
    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    //更改密码
    public void updateUserPwd(User user){
        //登录密码加密，数据库中加密存放
        encryptPassword(user);
        userMapper.updateUserPwd(user);
    }
}
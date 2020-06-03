package com.kx.mapper;

import com.kx.pojo.User;

public interface UserMapper {
    //根据用户名查询用户
    public User selectUserByUsername(String username);

    //添加用户
    public void insertUser(User user);

    //更改信息
    public void updateUser(User user);

    //更改密码
    public void updateUserPwd(User user);

}

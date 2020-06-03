package com.kx.controller;

import com.kx.pojo.User;
import com.kx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getBlogger")
    public UserDetails getBlogger(){
        UserDetails user = userService.loadUserByUsername("kongsanjin");
        return user;
    }
    @PostMapping("/username/{username}")
    public boolean isUsername(@PathVariable String username){
        User user = userService.selectUserByUsername(username);
        if(user!=null){
            return false;
        }
        return true;
    }

}

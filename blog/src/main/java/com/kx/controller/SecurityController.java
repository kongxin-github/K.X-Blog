package com.kx.controller;


import com.kx.pojo.RespBean;
import com.kx.pojo.User;
import com.kx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SecurityController {
    @Autowired
    private UserService userService;

    @GetMapping("/username")
    public RespBean currentUserName() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return new RespBean("success", currentUser);
    }

    @GetMapping("/getuser")
    public User getuser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/register")
    public RespBean registerUser(@RequestBody User user){
        userService.insertUser(user);
        return new RespBean("success", "注册成功！");
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody User user, Authentication authentication){
        userService.updateUser(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),authentication.getAuthorities()));
        return "success";
    }

    @PostMapping("/isPwd")
    public boolean isPwd(@RequestBody User user){
        String pwd = userService.selectpwd(user.getUsername());
        boolean b = new BCryptPasswordEncoder().matches(user.getPassword(),pwd);
        return b;
    }
    @PutMapping("/setuser")
    public String updateUserPwd(@RequestBody User user){
        userService.updateUserPwd(user);
        return "success";
    }
    /**
     *
     * @return
     */
    @GetMapping("/islogin")
    public RespBean islogin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if ( username.equals("anonymousUser") ) {
            return new RespBean("logout", "anonymousUser");
        } else {
            return new RespBean("login", username);
        }
    }
 
    @GetMapping("/login_page")
    public RespBean loginPage() {
        return new RespBean("logout", "尚未登录，请登录!");
    }
    
    @GetMapping("/login_success")
    public RespBean loginSuccess() {
        return new RespBean("success", "登录成功!");
    }
    
    @GetMapping("/login_error")
    public RespBean loginError() {
        return new RespBean("error", "身份错误!");
    }
    
    @GetMapping("/logout_success")
    public RespBean logoutSuccess() {
        return new RespBean("success", "退出成功!");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.security;

import com.kx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //设置访问权限
                //.antMatchers("/home/**").permitAll()  //任何用户都可以访问，可以不登录
                //.antMatchers("/home/**").hasAnyRole("admin","user") //admin和user 都有权访问
                //.antMatchers("/home/**").hasRole("admin")  //只有admin有权访问
                //.antMatchers("/upload").permitAll()

                .antMatchers("/file").permitAll()   //任何用户都可以访问，可以不登录
                .antMatchers("/upload/**").permitAll()   //任何用户都可以访问，可以不登录
                .antMatchers("/username/**").permitAll()   //任何用户都可以访问，可以不登录
                .antMatchers("/register").permitAll()   //任何用户都可以访问，可以不登录
                .anyRequest().authenticated() //任何服务 登陆后才可以访问

                //.antMatchers("/cart/**").hasRole("user")    //设置只有user有权访问购物车的所有服务

                //尚未登陆提示  不要给login_page设置任何访问权限
                .and().formLogin().loginPage("/login_page")

                //security提供的登陆服务
                .loginProcessingUrl("/login")  //参数 /login?username=xx&password=xx
                .defaultSuccessUrl("/login_success").failureUrl("/login_error").permitAll()

                //允许退出登录
                .and().logout().logoutSuccessUrl("/logout_success").permitAll()
                //开启跨域 cors()
                .and().cors().configurationSource(corsConfigurationSource())
                .and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置用户User 根据用户名 找到用户User，比对密码 获取role
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //设置无需权限就可以访问的资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    //spring security 配置跨域访问资源
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");  //header，允许哪些header
        corsConfiguration.addAllowedMethod("*");  //允许的请求方法，PSOT、GET、PUT等
        //corsConfiguration.addExposedHeader("token"); //拓展header 浏览器放过redponse的token 不然跨域登录收不到token
        corsConfiguration.setAllowCredentials(true); //允许浏览器携带cookie
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**", corsConfiguration); //配置允许跨域访问的url
        return source;
    }


}
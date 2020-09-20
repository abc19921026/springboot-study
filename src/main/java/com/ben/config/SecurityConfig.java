package com.ben.config;

import com.ben.handler.AuthLimitHandler;
import com.ben.handler.LoginSuccessHandler;
import com.ben.model.UserDO;
import com.ben.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable();
        http.headers().frameOptions().disable();
        // 配置登录页面
        http.formLogin().loginPage("/login").permitAll();
        // 配置登录成功后的操作
        http.formLogin().successHandler(new LoginSuccessHandler());
        // 用户权限不足处理器
        http.exceptionHandling().accessDeniedHandler(new AuthLimitHandler());
        // 登出授权
        http.logout().permitAll();
        // 授权配置
        http.authorizeRequests()
            .antMatchers("/js/**","/css/**","/images/**").permitAll()
            .antMatchers("/lib/**","/sliderVerify.js","/fonts/**").permitAll()
            .antMatchers("/actuator/**").permitAll()
            .anyRequest().authenticated();
    }

    /**
     * 自定义认证数据源
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailService())
                .passwordEncoder(passwordEncoder());
    }
    @Bean
    public UserDetailServiceImpl userDetailService (){
        return new UserDetailServiceImpl() ;
    }
    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


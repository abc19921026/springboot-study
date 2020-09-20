package com.ben.service.impl;

import com.ben.mapper.UserMapper;
import com.ben.model.UserDO;
import com.ben.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean isExists(String userName, String password) {
        UserDO userDO = new UserDO();
        userDO.setUsername(userName);
        userDO.setPassword(password);
        List<UserDO> list = userMapper.selectList(userDO);
        if(CollectionUtils.isEmpty(list)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    @Async
    public void sendEmail() {
        //邮件设置1：一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知-明天来狂神这听课");
        message.setText("今晚7:30开会");

        message.setTo("1029635424@qq.com");
        message.setFrom("1029635424@qq.com");
        mailSender.send(message);
    }


}

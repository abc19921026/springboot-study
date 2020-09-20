package com.ben.controller;

import com.ben.model.UserDO;
import com.ben.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","/login"})
    public String toLogin(){
        return "login";
    }

//    @RequestMapping("/doLogin")
//    public String login(String userName, String password) {
//        Boolean flag = userService.isExists(userName, password);
//        if (flag) {
//            return "home";
//        } else {
//            return "error";
//        }
//    }
}

package com.example.first_floor.generator.controller;

import com.example.first_floor.generator.intercept.LoginCut;
import com.example.first_floor.generator.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author JinShengJie
 * @date 2023-07-25 9:41
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("")
// todo 除login接口之外的其它接口都需要内接@LoginCut注解
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;


    @GetMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password) {
       return userInfoService.login(username, password);
    }

    @LoginCut
    @GetMapping("/test")
    public void test(@RequestParam String username) {

    }

    @GetMapping("/loginOut")
    public void loginOut(@RequestParam String username) {
        userInfoService.loginOut(username);
    }
}

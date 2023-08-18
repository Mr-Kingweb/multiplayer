package com.example.first_floor.generator.service;

import com.example.first_floor.generator.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author JinShengJie
* @description 针对表【user_info】的数据库操作Service
* @createDate 2023-07-25 09:29:50
*/
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link Object}
     */
    Object login(String username,String password);

    /**
     * 登出
     *
     * @param username 用户名
     */
    void loginOut(String username);
}

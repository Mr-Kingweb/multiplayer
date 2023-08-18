package com.example.first_floor.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.first_floor.generator.domain.HttpStatus;
import com.example.first_floor.generator.domain.JsonResult;
import com.example.first_floor.generator.domain.UserInfo;
import com.example.first_floor.generator.service.UserInfoService;
import com.example.first_floor.generator.mapper.UserInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author JinShengJie
 * @description 针对表【user_info】的数据库操作Service实现
 * @createDate 2023-07-25 09:29:50
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {
    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public Object login(String username, String password) {
        UserInfo userInfo = userInfoMapper.login(username, password);
        if (userInfo != null) {
            redisTemplate.opsForSet().add(username, password);
            return new JsonResult(HttpStatus.OK);
        } else {
            return new JsonResult(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void loginOut(String username) {
        redisTemplate.opsForSet().pop(username);
    }
}





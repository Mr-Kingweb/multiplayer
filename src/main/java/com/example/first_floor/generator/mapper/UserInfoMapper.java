package com.example.first_floor.generator.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.first_floor.generator.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
/**
 * @author JinShengJie
 * @description 针对表【user_info】的数据库操作Mapper
 * @createDate 2023-07-25 09:29:50
 * @Entity com.example.first_floor.generator.domain.UserInfo
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link UserInfo}
     */
    UserInfo login(@Param("username") String username, @Param("password") String password);
}





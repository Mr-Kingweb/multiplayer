package com.example.first_floor.generator.intercept;

import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author JinShengJie
 * @date 2023-07-25 10:11
 */
@Aspect
@Component
public class LoginIntercepter {

    @Resource
    private RedisTemplate redisTemplate;

    @Around("@annotation(LoginCut)")
    public Object CheckLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // todo 通过java反射机制获取注解下的方法参数，和redis进行一个对比，符合条件则进行下面的内容，否则返回登录界面
        Object[] args = proceedingJoinPoint.getArgs();
        String username = args[0].toString();
        if(redisTemplate.hasKey(username)){
            System.out.println("用户账户信息正确");
            return proceedingJoinPoint.proceed();
        }
        return proceedingJoinPoint.proceed();
    }
}

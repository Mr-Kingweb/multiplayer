package com.example.first_floor.generator.intercept;

import java.lang.annotation.*;

/**
 * @author JinShengJie
 * @date 2023-07-25 10:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCut {
}

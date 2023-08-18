package com.example.first_floor.generator.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.example.first_floor.generator.domain.HttpStatus;
/**
 * @author JinShengJie
 * @date 2023-07-27 16:25
 */
// todo 构造函数 ，多个入参实现
@Data
public class JsonResult implements Serializable {
    private static final long serializableUid = 1l;
    private String msg;
    private Integer code;
    private Map<String,Object> body;
    public JsonResult(HttpStatus HttpStatus) {
        this.msg = HttpStatus.getMsg();
        this.code = HttpStatus.getCode();
    }

    public JsonResult(Integer status, String msg) {
        this.msg = msg;
        this.code = status;
    }

    public JsonResult(HttpStatus HttpStatus, Object data) {
        this.msg = HttpStatus.getMsg();
        this.code = HttpStatus.getCode();
        Map<String, Object> map = new HashMap();
        map.put("data", data);
        this.body = map;
    }
    public JsonResult(){

    }

    public JsonResult(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public JsonResult(String msg, Integer code, Object body) {
        this.msg = msg;
        this.code = code;
        Map<String,Object> map = new HashMap<>();
        map.put("data",body);
        this.body = map;
    }
}

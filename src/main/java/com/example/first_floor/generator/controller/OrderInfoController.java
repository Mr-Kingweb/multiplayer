package com.example.first_floor.generator.controller;

import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.example.first_floor.generator.domain.HttpStatus;
import com.example.first_floor.generator.domain.JsonResult;
import com.example.first_floor.generator.domain.OrderInfo;
import com.example.first_floor.generator.intercept.LoginCut;
import com.example.first_floor.generator.mapper.PackingInfoMapper;
import com.example.first_floor.generator.service.OrderInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author JinShengJie
 * @date 2023-07-25 9:40
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private PackingInfoMapper packingInfoMapper;
    @LoginCut
    @PostMapping("/getData")
    public JsonResult getAllData(@RequestBody String username) {
        return orderInfoService.getAllData(username);
    }

    @LoginCut
    @PostMapping("/exportExcel")
    public void exportExcel(@RequestBody String username, HttpServletResponse httpServletResponse) {
        orderInfoService.exportExcel(username, httpServletResponse);
    }


    @PostMapping("/insertData")
    public JsonResult insertData(@RequestBody Map<String, Object> insertData) {
        String username = insertData.get("username").toString();
        Gson gson = new Gson();
        // 将 object 转换为 JSON 字符串
        String jsonString = gson.toJson(insertData.get("orderInfo"));
        // 然后再将 JSON 字符串转换为 OrderInfo 类型的值
        OrderInfo orderInfo = gson.fromJson(jsonString, OrderInfo.class);

        return orderInfoService.insertData(orderInfo);
    }

    @LoginCut
    @GetMapping("/delete")
    public JsonResult deleteById(@RequestParam String username, @RequestParam Integer id) {
        return orderInfoService.deleteById(id);
    }

    // todo 最后数据库得加一个锁，控制多个人进行操作时如何分辨
    @PostMapping("/update")
    public JsonResult updateById(@RequestBody Map<String, Object> object) {
        Object ooo = object.get("orderInfo");
        List<Map<String, Object>> objectList = (List<Map<String, Object>>) ooo;
        List<OrderInfo> orderInfoList = new ArrayList<>();

        for (Map<String, Object> map : objectList) {
            OrderInfo orderInfo = new OrderInfo();
            System.out.println(map);
            Field[] fields = OrderInfo.class.getDeclaredFields();
            for (Field field : fields) {
                // 忽略私有静态字段
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                String fieldName = field.getName();

                try {
                    Object fieldValue = map.get(fieldName);

                    if (fieldName.equals("productionTime")) {
                        // 当字段为 productionTime 时，插入当前时间
                        fieldValue = LocalDateTime.parse((String) fieldValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
                    }

                    field.set(orderInfo, fieldValue);
                } catch (IllegalAccessException e) {
                    // 如果无法访问 obj 的这个字段，忽略错误
                }
            }

            orderInfoList.add(orderInfo);
        }

        for (OrderInfo orderInfo : orderInfoList) {
            if (orderInfo.getId() == null) {
                try {
                    orderInfoService.insertData1(orderInfo);
                }
                catch (Exception e){
                    return new JsonResult(HttpStatus.BAD_REQUEST,e);
                }
            }
            else{
                Integer i = orderInfoService.updateById1(orderInfo);
                if (i != 1) {
                    return new JsonResult(HttpStatus.BAD_REQUEST);
                }
            }
        }
        packingInfoMapper.updateDeviceCount();
        return new JsonResult(HttpStatus.OK);
    }

    @LoginCut
    @GetMapping("/getByOperatorId")
    public JsonResult selectAllByOperatorId(@RequestParam String username, @RequestParam Integer operatorId) {
        return orderInfoService.selectAllByOperatorId(operatorId);
    }
}

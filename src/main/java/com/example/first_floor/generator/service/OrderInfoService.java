package com.example.first_floor.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.first_floor.generator.domain.HttpStatus;
import com.example.first_floor.generator.domain.JsonResult;
import com.example.first_floor.generator.domain.OrderInfo;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author JinShengJie
 * @description 针对表【order_info】的数据库操作Service
 * @createDate 2023-07-25 09:20:08
 */
public interface OrderInfoService extends IService<OrderInfo> {
    /**
     * 得到所有数据
     *
     * @param username 用户名
     * @return {@link JsonResult}
     */
    JsonResult getAllData(String username);

    /**
     * 导出excel
     *
     * @param username            用户名
     * @param httpServletResponse http servlet响应
     */
    void exportExcel(String username, HttpServletResponse httpServletResponse);

    /**
     * 插入数据
     *
     * @param username  用户名
     * @param orderInfo 订单信息
     * @return {@link JsonResult}
     */
    JsonResult insertData(OrderInfo orderInfo);

    /**
     * 删除通过id
     *
     * @param id id
     * @return {@link JsonResult}
     */
    JsonResult deleteById(Integer id);


    /**
     * 更新通过id
     *
     * @param entity 实体
     * @return boolean
     */
    int updateById1(OrderInfo entity);

    /**
     * 根据操作员选择所有id
     *
     * @param operatorId 操作符id
     * @return {@link JsonResult}
     */
    JsonResult selectAllByOperatorId(Integer operatorId);

    /**
     * 插入data1
     *
     * @param orderInfo 订单信息
     * @return int
     */
    void insertData1(OrderInfo orderInfo);
}

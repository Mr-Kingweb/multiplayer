package com.example.first_floor.generator.mapper;

import com.example.first_floor.generator.domain.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.first_floor.generator.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
/**
* @author JinShengJie
* @description 针对表【order_info】的数据库操作Mapper
* @createDate 2023-07-25 09:20:08
* @Entity com.example.first_floor.generator.domain.OrderInfo
*/
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    /**
     * 选择所有订单时间
     *
     * @return {@link List}<{@link OrderInfo}>
     */
    List<OrderInfo> selectAllOrderByTime();

    /**
     * 插入所有
     *
     * @param orderInfo 订单信息
     * @return int
     */
    int insertAll(OrderInfo orderInfo);

    /**
     * 删除通过id
     *
     * @param id id
     * @return int
     */
    int deleteById(@Param("id") Integer id);


    /**
     * 更新标识
     *
     * @param orderInfo 订单信息
     * @return int
     */
    int updateId(OrderInfo orderInfo);

    /**
     * 根据操作员选择所有id
     *
     * @param operatorId 操作符id
     * @return {@link List}<{@link OrderInfo}>
     */
    List<OrderInfo> selectAllByOperatorId(@Param("operatorId") Integer operatorId);

    /**
     * 删除由操作员身份证
     *
     * @param operatorId 操作符id
     * @return int
     */
    int deleteByOperatorId(@Param("operatorId") Integer operatorId);
}





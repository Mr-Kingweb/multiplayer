package com.example.first_floor.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单信息
 *
 * @author JJ_un
 * @TableName order_info
 * @date 2023/08/07
 */
@TableName(value ="order_info")
@Data
public class OrderInfo implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String orderNumber;

    /**
     *
     */
    private String customerNumber;

    /**
     *
     */
    private String brand;

    /**
     *
     */
    private Integer quantity;

    /**
     *
     */
    private String specification;

    /**
     *
     */
    private String remark;

    /**
     *
     */
    private String tableNumber;

    /**
     *
     */
    private String boxOrWorkerNumber;

    /**
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime productionTime;

    /**
     *
     */
    private Integer operatorId;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

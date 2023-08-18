package com.example.first_floor.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 包装信息
 *
 * @author JJ_un
 * @TableName packing_info
 * @date 2023/08/09
 */
@TableName(value ="packing_info")
@Data
public class PackingInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer deviceCount;

    /**
     * 
     */
    private String operatorId;

    /**
     * 
     */
    private Date packingTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
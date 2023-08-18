package com.example.first_floor.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.first_floor.generator.domain.HttpStatus;
import com.example.first_floor.generator.domain.JsonResult;
import com.example.first_floor.generator.domain.OrderInfo;
import com.example.first_floor.generator.service.OrderInfoService;
import com.example.first_floor.generator.mapper.OrderInfoMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author JinShengJie
 * @description 针对表【order_info】的数据库操作Service实现
 * @createDate 2023-07-25 09:20:08
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
        implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public JsonResult getAllData(String username) {
        List<OrderInfo> orderInfoList = orderInfoMapper.selectAllOrderByTime();
        if (orderInfoList.isEmpty()) {
            return new JsonResult(HttpStatus.NO_CONTENT);
        }
        return new JsonResult(HttpStatus.OK, orderInfoList);
    }

    /**
     * 反射实现 空字段赋值判断
     */
    public void setNullFieldsToZero(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null && field.getType() == Integer.class) {
                    field.set(obj, 0);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void exportExcel(String username, HttpServletResponse response) {
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet("数据");
        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("订单编号");
        headerRow.createCell(1).setCellValue("客户号");
        headerRow.createCell(2).setCellValue("品名");
        headerRow.createCell(3).setCellValue("数量");
        headerRow.createCell(4).setCellValue("规格");
        headerRow.createCell(5).setCellValue("备注");
        headerRow.createCell(6).setCellValue("表号");
        headerRow.createCell(7).setCellValue("箱号/工号");
        headerRow.createCell(8).setCellValue("生产时间");
        List<OrderInfo> list = orderInfoMapper.selectAllOrderByTime();
        List<OrderInfo> newList = list.stream()
                .peek(this::setNullFieldsToZero)
                .collect(Collectors.toList());
        // 填充数据
        int rowIndex = 1;
        for (OrderInfo rowData : newList) {
            Row row = sheet.createRow(rowIndex++);
            LocalDateTime localDateTime = rowData.getProductionTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            // 时间
            String formattedDateTime = localDateTime.format(formatter);
            row.createCell(0).setCellValue(rowData.getOrderNumber());
            row.createCell(1).setCellValue(rowData.getCustomerNumber());
            row.createCell(2).setCellValue(rowData.getBrand());
            row.createCell(3).setCellValue(rowData.getQuantity());
            row.createCell(4).setCellValue(rowData.getSpecification());
            row.createCell(5).setCellValue(rowData.getRemark());
            row.createCell(6).setCellValue(rowData.getTableNumber());
            row.createCell(7).setCellValue(rowData.getBoxOrWorkerNumber());
            row.createCell(8).setCellValue(formattedDateTime);
        }
        String filename = "数据";
        String encodedFilename;
        encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);

        // 设置响应头信息
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodedFilename + ".xlsx");

        // 将工作簿写入响应流并关闭工作环境
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonResult insertData(OrderInfo orderInfo) {
        int success = orderInfoMapper.insertAll(orderInfo);
        if (success == 1) {
            return new JsonResult(HttpStatus.OK);
        }
        return new JsonResult(HttpStatus.BAD_REQUEST);
    }

    @Override
    public JsonResult deleteById(Integer id) {
        int success = orderInfoMapper.deleteById(id);
        if (success == 1) {
            return new JsonResult(HttpStatus.OK);
        }
        return new JsonResult(HttpStatus.BAD_REQUEST);
    }

    @Override
    public int updateById1(OrderInfo entity) {
        return orderInfoMapper.updateId(entity);
    }

    @Override
    public JsonResult selectAllByOperatorId(Integer operatorId) {
        List<OrderInfo> orderInfoList = orderInfoMapper.selectAllByOperatorId(operatorId);
        if (orderInfoList.isEmpty()) {
            return new JsonResult(HttpStatus.NO_CONTENT);
        }
        return new JsonResult(HttpStatus.OK, orderInfoList);
    }

    @Override
    public void insertData1(OrderInfo orderInfo) {
        orderInfoMapper.insertAll(orderInfo);
    }
}





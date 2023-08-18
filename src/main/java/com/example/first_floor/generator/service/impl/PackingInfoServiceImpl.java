package com.example.first_floor.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.first_floor.generator.domain.HttpStatus;
import com.example.first_floor.generator.domain.JsonResult;
import com.example.first_floor.generator.domain.PackingInfo;
import com.example.first_floor.generator.mapper.OrderInfoMapper;
import com.example.first_floor.generator.service.PackingInfoService;
import com.example.first_floor.generator.mapper.PackingInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author JJ_un
* @description 针对表【packing_info】的数据库操作Service实现
* @createDate 2023-08-07 12:25:41
*/
@Service
public class PackingInfoServiceImpl extends ServiceImpl<PackingInfoMapper, PackingInfo>
    implements PackingInfoService{

    @Autowired
    private PackingInfoMapper packingInfoMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Override
    public List<PackingInfo> getAll() {
        List<PackingInfo> packingInfos = packingInfoMapper.getAll();
        return packingInfos;
    }

    @Override
    public JsonResult insertAll(PackingInfo packingInfo) {
        packingInfoMapper.insertAll(packingInfo);
        return new JsonResult();
    }

    @Override
    public JsonResult deleteById(Integer id) {
        try {
            orderInfoMapper.deleteByOperatorId(id);
            packingInfoMapper.deleteById(id);
            return new JsonResult(HttpStatus.OK);
        }catch (Exception e){
            return new JsonResult(HttpStatus.BAD_REQUEST);
        }

    }
}





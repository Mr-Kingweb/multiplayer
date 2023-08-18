package com.example.first_floor.generator.service;

import com.example.first_floor.generator.domain.JsonResult;
import com.example.first_floor.generator.domain.PackingInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author JJ_un
* @description 针对表【packing_info】的数据库操作Service
* @createDate 2023-08-07 12:25:41
*/
public interface PackingInfoService extends IService<PackingInfo> {
    /**
     * 得到所有
     *
     * @return {@link List}<{@link PackingInfo}>
     */
    List<PackingInfo> getAll();

    /**
     * 插入所有
     *
     * @param packingInfo 包装信息
     * @return {@link JsonResult}
     */
    JsonResult insertAll(PackingInfo packingInfo);

    /**
     * 删除通过id
     *
     * @param id id
     * @return {@link JsonResult}
     */
    JsonResult deleteById(Integer id);
}

package com.example.first_floor.generator.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.first_floor.generator.domain.PackingInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author JJ_un
* @description 针对表【packing_info】的数据库操作Mapper
* @createDate 2023-08-07 12:25:41
* @Entity generator.domain.PackingInfo
*/
@Mapper
public interface PackingInfoMapper extends BaseMapper<PackingInfo> {
    /**
     * 得到所有数据
     *
     * @return {@link List}<{@link PackingInfo}>
     */
    List<PackingInfo> getAll();

    /**
     * 更新设备数
     *
     * @return int
     */
    int updateDeviceCount();

    /**
     * 插入所有
     *
     * @param packingInfo 包装信息
     * @return int
     */
    int insertAll(PackingInfo packingInfo);

    /**
     * 删除通过id
     *
     * @param id id
     * @return int
     */
    int deleteById(@Param("id") Integer id);
}





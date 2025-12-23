package com.pd.server.main.mapper;

import com.pd.server.main.dto.UserProjectEquipmentDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 移动端Mapper
 */
public interface MobileMapper {

    /**
     * 查询用户关联的所有项目和设备
     * @param usercode 用户账号
     * @return 用户项目设备列表
     */
    List<UserProjectEquipmentDto> getUserProjectEquipment(@Param("usercode") String usercode);
}


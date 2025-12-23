package com.pd.server.main.service;

import com.pd.server.main.dto.UserProjectEquipmentDto;
import com.pd.server.main.mapper.MobileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 移动端Service
 */
@Service
public class MobileService {

    @Resource
    private MobileMapper mobileMapper;

    /**
     * 获取用户关联的所有项目和设备
     * @param usercode 用户账号
     * @return 用户项目设备列表
     */
    public List<UserProjectEquipmentDto> getUserProjectEquipment(String usercode) {
        return mobileMapper.getUserProjectEquipment(usercode);
    }
}


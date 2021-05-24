package com.pd.server.main.service;

import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.domain.WaterData;
import com.pd.server.main.domain.WaterDataExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WaterDataDto;
import com.pd.server.main.mapper.WaterDataMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class WaterDataService {

    @Resource
    private WaterDataMapper waterDataMapper;

    /**
    * 列表查询
     * @return
     */
    public List<WaterData> list(WaterDataExample waterDataExample) {
        List<WaterData> waterDataList = waterDataMapper.selectByExample(waterDataExample);
        return waterDataList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterDataDto waterDataDto, LoginUserDto loginUserDto) {
        WaterData waterData = CopyUtil.copy(waterDataDto, WaterData.class);
        if (StringUtils.isEmpty(waterDataDto.getId())) {
            WaterDataExample waterDataExample = new WaterDataExample();
            WaterDataExample.Criteria ca = waterDataExample.createCriteria();
            ca.andCenterCodeEqualTo(waterDataDto.getCenterCode());
            ca.andCenterNameEqualTo(waterDataDto.getCenterName());
            List<WaterData> waterDataList = waterDataMapper.selectByExample(waterDataExample);
            if (!CollectionUtils.isEmpty(waterDataList)) {
                throw new BusinessException(BusinessExceptionCode.CENTER_CODE_EXIST);
            }
            //waterData.setDeptcode(loginUserDto.getDeptcode());
            waterData.setCreateBy(loginUserDto.getName());
            waterData.setCreateTime(new Date());
            this.insert(waterData);
        } else {
            waterData.setUpdateBy(loginUserDto.getName());
            waterData.setGxsj(new Date());
            this.update(waterData);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterData waterData) {
        waterData.setId(UuidUtil.getShortUuid());
        waterDataMapper.insert(waterData);
    }

    /**
    * 更新
    */
    private void update(WaterData waterData) {
        waterDataMapper.updateByPrimaryKey(waterData);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterDataMapper.deleteByPrimaryKey(id);
    }

}
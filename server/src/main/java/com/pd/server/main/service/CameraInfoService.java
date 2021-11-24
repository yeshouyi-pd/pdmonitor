package com.pd.server.main.service;

import com.pd.server.main.domain.CameraInfo;
import com.pd.server.main.domain.CameraInfoExample;
import com.pd.server.main.dto.CameraInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.CameraInfoMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class CameraInfoService {

    @Resource
    private CameraInfoMapper cameraInfoMapper;

    public CameraInfo findBySbsn(String sbsn){
        CameraInfoExample example = new CameraInfoExample();
        example.createCriteria().andSbsnEqualTo(sbsn);
        List<CameraInfo> cameraInfoList = cameraInfoMapper.selectByExample(example);
        if(cameraInfoList.size() > 0){
            return cameraInfoList.get(0);
        }
        return null;
    }

    /**
    * 列表查询
    */
    public List<CameraInfo> list(CameraInfoExample example) {
        List<CameraInfo> cameraInfoList = cameraInfoMapper.selectByExample(example);
        return cameraInfoList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(CameraInfoDto cameraInfoDto) {
        CameraInfo cameraInfo = CopyUtil.copy(cameraInfoDto, CameraInfo.class);
        if (StringUtils.isEmpty(cameraInfoDto.getId())) {
            this.insert(cameraInfo);
        } else {
            this.update(cameraInfo);
        }
    }

    /**
    * 新增
    */
    private void insert(CameraInfo cameraInfo) {
                Date now = new Date();
        cameraInfo.setId(UuidUtil.getShortUuid());
        cameraInfoMapper.insert(cameraInfo);
    }

    /**
    * 更新
    */
    private void update(CameraInfo cameraInfo) {
        cameraInfoMapper.updateByPrimaryKey(cameraInfo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        cameraInfoMapper.deleteByPrimaryKey(id);
    }

}
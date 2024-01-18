package com.pd.server.main.service;

import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class CameraInfoService {

    @Resource
    private CameraInfoMapper cameraInfoMapper;

    public List<CameraInfo> findByDeptcode(String deptcode){
        CameraInfoExample example = new CameraInfoExample();
        CameraInfoExample.Criteria ca = example.createCriteria();
        ca.andDeptcodeEqualTo(deptcode);
        ca.andSm1EqualTo("1");
        List<CameraInfo> cameraInfoList = cameraInfoMapper.selectByExample(example);
        return cameraInfoList;
    }

    public List<CameraInfo> findBySbbh(String sbsn){
        CameraInfoExample example = new CameraInfoExample();
        example.createCriteria().andSbsnEqualTo(sbsn);
        return cameraInfoMapper.selectByExample(example);
    }

    public CameraInfo findByID(String id){
        CameraInfoExample example = new CameraInfoExample();
        CameraInfo info = cameraInfoMapper.selectByPrimaryKey(id);
        return info;
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
        cameraInfo.setId(UuidUtil.getShortUuid());
//        CameraInfoExample example = new CameraInfoExample();
//        example.createCriteria().andSm2EqualTo(cameraInfo.getSm2());
//        List<CameraInfo> list = cameraInfoMapper.selectByExample(example);
//        if (!CollectionUtils.isEmpty(list)) {
//            throw new BusinessException(BusinessExceptionCode.DEPT_CODE_EXIST);
//        }
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
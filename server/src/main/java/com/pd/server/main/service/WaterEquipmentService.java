package com.pd.server.main.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PieChartDto;
import com.pd.server.main.dto.WaterEquipmentDto;
import com.pd.server.main.mapper.WaterEquiplogMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WaterEquipmentService {

    @Resource
    private WaterEquipmentMapper waterEquipmentMapper;
    @Resource
    private WaterEquiplogMapper waterEquiplogMapper;

    public void listByPage(WaterEquipmentDto waterEquipmentDto){
        PageHelper.startPage(waterEquipmentDto.getPage(),waterEquipmentDto.getSize());
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waterEquipmentDto.getSblb())){
            ca.andSblbEqualTo(waterEquipmentDto.getSblb());
        }
        if(!StringUtils.isEmpty(waterEquipmentDto.getSbsn())){
            ca.andSbsnEqualTo(waterEquipmentDto.getSbsn());
        }
        if(!StringUtils.isEmpty(waterEquipmentDto.getSbmc())){
            ca.andSbmcEqualTo(waterEquipmentDto.getSbmc());
        }
        List<WaterEquipment> list = waterEquipmentMapper.selectByExample(example);
        PageInfo<WaterEquipment> pageInfo = new PageInfo<>(list);
        waterEquipmentDto.setTotal(pageInfo.getTotal());
        List<WaterEquipmentDto> listDto = CopyUtil.copyList(list, WaterEquipmentDto.class);
        waterEquipmentDto.setList(listDto);
    }

    public List<String> findSbbh(WaterEquipmentExample example){
        return waterEquipmentMapper.selectSbbhByExample(example);
    }

    public WaterEquipment findById(String id){
        return waterEquipmentMapper.selectByPrimaryKey(id);
    }

    public List<String> findSbsnByDeptcode(String deptcode){
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        ca.andDeptcodeEqualTo(deptcode);
        List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
        List<String> sbsns = lists.stream().filter(Objects::nonNull).map(WaterEquipment::getSbsn).collect(Collectors.toList());
        return sbsns;
    }

    public List<String> findSbsnByDeptcodes(List<String> deptcodes){
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        ca.andDeptcodeIn(deptcodes);
        List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
        List<String> sbsns = lists.stream().filter(Objects::nonNull).map(WaterEquipment::getSbsn).collect(Collectors.toList());
        return sbsns;
    }

    /**
    * 列表查询
    */
    public List<WaterEquipment> list(WaterEquipmentExample waterEquipmentExample) {
        return waterEquipmentMapper.selectByExample(waterEquipmentExample);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterEquipmentDto waterEquipmentDto, LoginUserDto loginUserDto) {
        WaterEquipment waterEquipment = CopyUtil.copy(waterEquipmentDto, WaterEquipment.class);
        if (StringUtils.isEmpty(waterEquipmentDto.getId())) {
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = waterEquipmentExample.createCriteria();
            //ca.andIpEqualTo(waterEquipment.getIp());
            //ca.andPortEqualTo(waterEquipment.getPort());
            ca.andSbsnEqualTo(waterEquipment.getSbsn());
            //ca.andCenterCodeEqualTo(waterEquipment.getCenterCode());
            List<WaterEquipment> waterEquipmentList = waterEquipmentMapper.selectByExample(waterEquipmentExample);
            if (!CollectionUtils.isEmpty(waterEquipmentList)) {
                throw new BusinessException(BusinessExceptionCode.EQUIPMENT_EXIST);
            }else{
                //waterEquipment.setDeptcode(loginUserDto.getDeptcode());
                waterEquipment.setCreateBy(loginUserDto.getName());
                waterEquipment.setCreateTime(new Date());
            }
            this.insert(waterEquipment);
            WaterEquiplog equiplog = new WaterEquiplog();
            equiplog.setId(UuidUtil.getShortUuid());
            equiplog.setSbbh(waterEquipmentDto.getSbsn());
            equiplog.setCode("1");
            equiplog.setMsg("正常在线");
            equiplog.setCjsj(new Date());
            equiplog.setSm1(waterEquipmentDto.getSbcj());
            equiplog.setSm2(waterEquipmentDto.getSbmc());
            waterEquiplogMapper.insert(equiplog);
        } else {
            waterEquipment.setUpdateBy(loginUserDto.getName());
            waterEquipment.setGxsj(new Date());
            this.update(waterEquipment);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterEquipment waterEquipment) {
                Date now = new Date();
        waterEquipment.setId(UuidUtil.getShortUuid());
        waterEquipmentMapper.insert(waterEquipment);
    }

    /**
    * 更新
    */
    private void update(WaterEquipment waterEquipment) {
        waterEquipmentMapper.updateByPrimaryKey(waterEquipment);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterEquipmentMapper.deleteByPrimaryKey(id);
    }

    public List<PieChartDto> getPieChart(WaterEquipmentExample example) {
       return waterEquipmentMapper.getPieChart(example);
    }

    public void updateZt(WaterEquipment waterEquipment){
        waterEquipmentMapper.updateByPrimaryKeySelective(waterEquipment);
    }
}
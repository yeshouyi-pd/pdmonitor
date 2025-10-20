package com.pd.server.main.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.MqttClientSpace;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(WaterEquipmentService.class);

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
        //判断发声设备,发声设备的设备类别和主题唯一性
        if(!StringUtils.isEmpty(waterEquipmentDto.getSbsn()) && "0005".equals(waterEquipment.getSblb()) && !StringUtils.isEmpty(waterEquipmentDto.getJdfw())){
            WaterEquipmentExample example = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria topicCa = example.createCriteria();
//                topicCa.andSbsnEqualTo(waterEquipment.getSbsn());
            topicCa.andSblbEqualTo("0005");
            topicCa.andJdfwEqualTo(waterEquipment.getJdfw().trim());
            List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
            for(WaterEquipment item : lists){
                if(!item.getSbsn().equals(waterEquipmentDto.getSbsn())){
                    throw new BusinessException(BusinessExceptionCode.EQUIPMENT_TOPIC_EXIST);
                }
            }
        }
        if (StringUtils.isEmpty(waterEquipmentDto.getId())) {
            //判断设备编号的唯一性
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = waterEquipmentExample.createCriteria();
            ca.andSbsnEqualTo(waterEquipment.getSbsn());
            List<WaterEquipment> waterEquipmentList = waterEquipmentMapper.selectByExample(waterEquipmentExample);
            if (!CollectionUtils.isEmpty(waterEquipmentList)) {
                throw new BusinessException(BusinessExceptionCode.EQUIPMENT_EXIST);
            }
            waterEquipment.setCreateBy(loginUserDto.getName());
            waterEquipment.setCreateTime(new Date());
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
        try {
            // 获取MQTT客户端实例
            MqttClientSpace client = MqttClientSpace.getInstance();
            if(client == null){
                LOG.error("MQTT客户端未初始化，无法订阅消息。");
                return;
            }else{
                String[] topicArr = waterEquipmentDto.getJdfw().split(";");
                for(int i=0;i<topicArr.length;i++) {
                    String[] oneArr = topicArr[i].split(",");
                    if(oneArr.length < 2){
                        LOG.error("主题配置格式错误: {}", topicArr[i]);
                        continue;
                    }
                    client.subTopic(oneArr[1]);
                }
            }
        }catch (Exception e){
            LOG.error("处理新增修改发生设备消息订阅发生错误");
        }
    }

    /**
    * 新增
    */
    private void insert(WaterEquipment waterEquipment) {
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
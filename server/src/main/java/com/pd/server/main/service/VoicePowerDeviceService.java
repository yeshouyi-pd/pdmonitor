package com.pd.server.main.service;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.VoicePowerDevice;
import com.pd.server.main.domain.VoicePowerDeviceExample;
import com.pd.server.main.dto.VoicePowerDeviceDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.VoicePowerDeviceMapper;
import com.pd.server.main.service.shj.EquipmentFileShjService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
        import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoicePowerDeviceService {

    private static final Logger LOG = LoggerFactory.getLogger(VoicePowerDeviceService.class);

    @Resource
    private VoicePowerDeviceMapper voicePowerDeviceMapper;
    @Resource
    private RedisTemplate redisTemplate;

    /**
    * 列表查询
    */
    public List<VoicePowerDevice> selectByExample(VoicePowerDeviceExample example) {
        return voicePowerDeviceMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VoicePowerDeviceDto voicePowerDeviceDto) {
        VoicePowerDevice voicePowerDevice = CopyUtil.copy(voicePowerDeviceDto, VoicePowerDevice.class);
        if (StringUtils.isEmpty(voicePowerDeviceDto.getId())) {
            this.insert(voicePowerDevice);
        } else {
            this.update(voicePowerDevice);
        }
    }

    /**
    * 新增
    */
    private void insert(VoicePowerDevice voicePowerDevice) {
        voicePowerDeviceMapper.insert(voicePowerDevice);
    }

    /**
    * 更新
    */
    private void update(VoicePowerDevice voicePowerDevice) {
        voicePowerDeviceMapper.updateByPrimaryKey(voicePowerDevice);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        voicePowerDeviceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主题查询当天最后一条数据
     */
    public VoicePowerDevice selectTodayLastData(String topic){
        VoicePowerDeviceExample example = new VoicePowerDeviceExample();
        VoicePowerDeviceExample.Criteria ca = example.createCriteria();
        ca.andTopicNameEqualTo(topic);
        ca.andRqEqualTo(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
        List<VoicePowerDevice> list = voicePowerDeviceMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }else{
            list = list.stream()
                    .sorted(Comparator.comparing(VoicePowerDevice::getSendTime).reversed())
                    .collect(Collectors.toList());
            return list.get(0);
        }
    }

    /**
     * 保存发送指令数据
     */
    public VoicePowerDevice saveData(String topic){
        VoicePowerDevice voicePowerDevice = new VoicePowerDevice();
        voicePowerDevice.setId(UuidUtil.getShortUuid());
        voicePowerDevice.setSbbh(topicToSbbh(topic));
        voicePowerDevice.setTopicName(topic);
        voicePowerDevice.setRq(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
        voicePowerDevice.setSendTime(new Date());
        voicePowerDevice.setDeptcode(sbbhToDeptcode(voicePowerDevice.getSbbh()));
        voicePowerDeviceMapper.insert(voicePowerDevice);
        return voicePowerDevice;
    }

    public String sbbhToDeptcode(String sbbh){
        try {
            Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
            return map.get(sbbh);
        }catch (Exception e){
            LOG.error("根据驱离设备设备编号 {} 查询部门错误", sbbh);
        }
        return "";
    }

    public String topicToSbbh(String topic){
        try {
            Map<String,String> topicSbbhMap = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.TOPICSBBH);
            if (topicSbbhMap != null) {
                for(String key : topicSbbhMap.keySet()){
                    if(key.contains(topic)){
                        return topicSbbhMap.get(key);
                    }
                }
            } else {
                LOG.error("未找到主题 {} 对应的设备编号", topic);
            }
        }catch (Exception e){
            LOG.error("根据主题 {} 查询驱离设备设备编号错误", topic);
        }
        return "";
    }

    /**
     * 根据id修改播放状态
     */
    public void updateIsPlay(String topicName){
        try {
            if(!StringUtils.isEmpty(redisTemplate.opsForValue().get(topicName + "QLWJ"))){
                String entityJson = (String) redisTemplate.opsForValue().get(topicName + "QLWJ");
                VoicePowerDevice voicePowerDevice = JSONObject.parseObject(entityJson, VoicePowerDevice.class);
                voicePowerDevice.setIsPlay(1);
                voicePowerDeviceMapper.updateByPrimaryKeySelective(voicePowerDevice);
            }
        }catch (Exception e){
            LOG.error("根据主题 {} 修改播放状态错误", topicName);
        }
    }

    /**
     * 根据id修改停止时间
     */
    public void updateStopTime(String topicName){
        try {
            if(!StringUtils.isEmpty(redisTemplate.opsForValue().get(topicName + "QLWJ"))){
                String entityJson = (String) redisTemplate.opsForValue().get(topicName + "QLWJ");
                VoicePowerDevice voicePowerDevice = JSONObject.parseObject(entityJson, VoicePowerDevice.class);
                voicePowerDevice.setStopTime(new Date());
                voicePowerDeviceMapper.updateByPrimaryKeySelective(voicePowerDevice);
            }
        }catch (Exception e){
            LOG.error("根据主题 {} 修改停止时间错误", topicName);
        }
    }

    public Boolean findBySbsnAndSentTime(String deviceId, Date startSendTime) {
        VoicePowerDeviceExample example = new VoicePowerDeviceExample();
        VoicePowerDeviceExample.Criteria ca = example.createCriteria();
        ca.andSbbhEqualTo(deviceId);
        ca.andSendTimeGreaterThanOrEqualTo(startSendTime);
        ca.andIsPlayEqualTo(1);
        long l = voicePowerDeviceMapper.countByExample(example);
        if (l > 0){
            return true;
        }else{
            return false;
        }


    }

    public Boolean findBySbsnAndSentTimeend(String deviceId, Date startSendTime) {
        VoicePowerDeviceExample example = new VoicePowerDeviceExample();
        VoicePowerDeviceExample.Criteria ca = example.createCriteria();
        ca.andSbbhEqualTo(deviceId);
        ca.andStopTimeGreaterThanOrEqualTo(startSendTime);
        ca.andIsPlayEqualTo(1);
        long l = voicePowerDeviceMapper.countByExample(example);
        if (l > 0){
            return true;
        }else{
            return false;
        }


    }
}
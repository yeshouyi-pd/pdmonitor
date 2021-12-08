package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.mapper.EquipmentFileMapper;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.service.AttrService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentFileShjService extends AbstractScanRequest{

    public  static RedisTemplate  redisTstaticemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
    }
    /**
     * 设备文件
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(JSONObject jsonParam) throws Exception {
        String sbbh = jsonParam.getString("sbbh");
        String tplj = jsonParam.getString("tplj");
        String cjsj = jsonParam.getString("cjsj");
        if(StringUtils.isEmpty(sbbh)||StringUtils.isEmpty(tplj)||StringUtils.isEmpty(cjsj)){
            data = "参数错误";
            return data;
        }
        //Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
        EquipmentFileMapper equipmentFileMapper = SpringUtil.getBean(EquipmentFileMapper.class);
        EquipmentFileExample exampleFile = new EquipmentFileExample();
        EquipmentFileExample.Criteria caFile = exampleFile.createCriteria();
        caFile.andTpljEqualTo(tplj);
        caFile.andSbbhEqualTo(sbbh);
        List<EquipmentFile> comment = equipmentFileMapper.selectByExample(exampleFile);
        if(comment==null || comment.isEmpty()){
            EquipmentFile entity = new EquipmentFile();
            entity.setId(UuidUtil.getShortUuid());
            entity.setSbbh(sbbh);
            entity.setTplj(tplj);
            entity.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            entity.setNf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy"));
            entity.setYf(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM"));
            entity.setRq(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
            entity.setXs(DateUtil.getFormatDate(entity.getCjsj(),"HH"));
            entity.setFz(DateUtil.getFormatDate(entity.getCjsj(),"mm"));
            entity.setLy("1");//实时数据
            WaterEquipmentMapper waterEquipmentMapper = SpringUtil.getBean(WaterEquipmentMapper.class);
            WaterEquipmentExample example = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = example.createCriteria();
            ca.andSbsnEqualTo(sbbh);
            List<WaterEquipment> lists = waterEquipmentMapper.selectByExample(example);
            if(!StringUtils.isEmpty(lists)&&lists.size()>0&&!StringUtils.isEmpty(lists.get(0).getDeptcode())){
                entity.setDeptcode(lists.get(0).getDeptcode());
            }
            entity.setCreateTime(new Date());
            AttrService attrService = SpringUtil.getBean(AttrService.class);
            String predationsbsn = attrService.findByAttrKey("predationsbsn");
            if(predationsbsn.contains(sbbh)&&!tplj.contains("predation")){
                //RedisTemplate redisTemplate = SpringUtil.getBean(RedisTemplate.class);
                //南方海洋-判断是否是雾报(前后三分钟都没有报警的数据是雾报数据，雾报数据不保存)
                EquipmentFile beforeEntity = new EquipmentFile();
                System.out.println(redisTstaticemplate.opsForValue().get(sbbh));
                if(!StringUtils.isEmpty(redisTstaticemplate.opsForValue().get(sbbh))){
                    String entityJson = (String) redisTstaticemplate.opsForValue().get(sbbh);
                    beforeEntity = JSONObject.parseObject(entityJson,EquipmentFile.class);
                    if(!StringUtils.isEmpty(beforeEntity.getCjsj())&&isOverThreeMinute(DateUtil.getFormatDate(beforeEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"),cjsj)){
                        equipmentFileMapper.insert(beforeEntity);
                        redisTstaticemplate.opsForValue().set(sbbh, JSONObject.toJSONString(entity));
                    }else{
                        EquipmentFile lastFile = equipmentFileMapper.selectLastOneBySbbh(sbbh);
                        if(!StringUtils.isEmpty(beforeEntity.getCjsj())&&!StringUtils.isEmpty(lastFile.getCjsj())&&isOverThreeMinute(DateUtil.getFormatDate(lastFile.getCjsj(),"yyyy-MM-dd HH:mm:ss"),DateUtil.getFormatDate(beforeEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"))){
                            equipmentFileMapper.insert(beforeEntity);
                        }
                        redisTstaticemplate.opsForValue().set(sbbh, JSONObject.toJSONString(entity));
                    }
                }else{
                    redisTstaticemplate.opsForValue().set(sbbh, JSONObject.toJSONString(entity));
                }
                data="保存成功";
            }else {
                equipmentFileMapper.insert(entity);
                data="保存成功";
            }
            return data;
        }else {
            data="该图片已保存过";
            return data;
        }
    }

    public static Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        AttrService attrService = SpringUtil.getBean(AttrService.class);
        String predationInterval = attrService.findByAttrKey("predationInterval");
        if(minute<=Integer.parseInt(predationInterval)){
            return true;
        }
        return false;
    }
}

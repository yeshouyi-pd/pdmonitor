package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.PontoonGps;
import com.pd.server.main.domain.PontoonGpsExample;
import com.pd.server.main.dto.PontoonGpsDto;
import com.pd.server.main.service.PontoonGpsService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class PontoonGPSShjService extends AbstractScanRequest {
    private static final Logger LOG = LoggerFactory.getLogger(PontoonGPSShjService.class);
    /**
     * 趸船gps
     */
    public String request(JSONObject jsonParam) throws Exception {
        try {
            String sbbh = jsonParam.getString("sbbh");
            String gps = jsonParam.getString("gps");
            String cjsj = jsonParam.getString("cjsj");
            if (StringUtils.isEmpty(sbbh) || StringUtils.isEmpty(gps) || StringUtils.isEmpty(cjsj)) {
                data = "参数错误";
                return data;
            }
            if(gps.matches(".*[a-zA-Z].*") || gps.equals("0,0") || gps.startsWith("0.")){
                data = "GPS数据错误，不保存";
                return data;
            }
            PontoonGpsService service = SpringUtil.getBean(PontoonGpsService.class);
            PontoonGpsExample example = new PontoonGpsExample();
            PontoonGpsExample.Criteria ca = example.createCriteria();
            ca.andCjsjEqualTo(cjsj);
            ca.andSbbhEqualTo(sbbh);
            List<PontoonGps> list = service.selectByExample(example);
            if(list.size()>=1){
                data = "数据重复";
                return data;
            }
            PontoonGpsDto dto = new PontoonGpsDto();
            dto.setSbbh(jsonParam.getString("sbbh"));
            dto.setGps(jsonParam.getString("gps"));
            dto.setCjsj(DateUtil.toDate(cjsj,"yyyy-MM-dd HH:mm:ss"));
            dto.setCreateTime(new Date());
            dto.setRq(DateUtil.getFormatDate(dto.getCjsj(),"yyyy-MM-dd"));
            service.save(dto);
            data = "保存成功";
        }catch (Exception e){
            LOG.error("PontoonGPSShjService:"+e.getMessage());
            data = "系统异常";
        }
        return data;
    }
}

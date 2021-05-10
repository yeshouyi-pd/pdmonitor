package com.pd.system.controller.conf;

import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.WaterQualityAvg;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.WaterQualityAvgDto;
import com.pd.server.main.mapper.WaterQualityAvgMapper;
import com.pd.server.main.mapper.WaterQualityResultMapper;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class WateDataAvgController implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(WateDataAvgController.applicationContext == null) {
            WateDataAvgController.applicationContext = applicationContext;
        }
    }

    public static void main(String args[]){
        waterDataAvgSchedule();
    }

    @Scheduled(cron = "0 0 1 * * ? ")
    public static void waterDataAvgSchedule(){
        WaterQualityResultExample example = new WaterQualityResultExample();
        WaterQualityResultExample.Criteria ca = example.createCriteria();
        ca.andCreateTimeEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        WaterQualityResultMapper mapper = SpringUtil.getBean(WaterQualityResultMapper.class);
        List<WaterQualityAvgDto> list = mapper.selectAvgByExample(example);
        WaterQualityAvgMapper avgMapper = SpringUtil.getBean(WaterQualityAvgMapper.class);
        for(WaterQualityAvgDto dto : list){
            WaterQualityAvg entity = new WaterQualityAvg();
            entity.setId(UuidUtil.getShortUuid());
            entity.setCreateTime(DateUtil.toDate(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"),"yyyy-MM-dd"));
            entity.setDatacenter(dto.getDatacenter());
            entity.setIp(dto.getIp());
            entity.setJcxm(dto.getJcxm());
            if(dto.getCount()!=0){
                entity.setDataResult(Double.toString(div(dto.getSumDataResult(),dto.getCount(),7)));
            }else {
                entity.setDataResult("0.0");
            }
            avgMapper.insert(entity);
        }
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1            被除数
     * @param v2            除数
     * @param scale         表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(Double v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

package com.pd.monitor.quartz;

import com.pd.server.main.domain.WaterQualityAvg;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.WaterQualityAvgDto;
import com.pd.server.main.mapper.WaterQualityAvgMapper;
import com.pd.server.main.mapper.WaterQualityResultMapper;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class WaterDataAvgQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(WaterDataAvgQuartz.class);

    @Resource
    private WaterQualityResultMapper mapper;
    @Resource
    private WaterQualityAvgMapper avgMapper;

    /* 每天凌晨3点开始计算水深，流速等平均值 */
    @Scheduled(cron = "0 0 3 * * ? ")
    public void waterDataAvgSchedule(){
        WaterQualityResultExample example = new WaterQualityResultExample();
        WaterQualityResultExample.Criteria ca = example.createCriteria();
        ca.andCreateTimeEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<WaterQualityAvgDto> list = mapper.selectAvgByExample(example);
        for(WaterQualityAvgDto dto : list){
            WaterQualityAvg entity = new WaterQualityAvg();
            entity.setId(UuidUtil.getShortUuid());
            entity.setCreateTime(DateUtil.toDate(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"),"yyyy-MM-dd"));
            entity.setDatacenter(StringUtils.isEmpty(dto.getDatacenter())?"":dto.getDatacenter());
            entity.setIp(StringUtils.isEmpty(dto.getIp())?"":dto.getIp());
            entity.setJcxm(StringUtils.isEmpty(dto.getJcxm())?"":dto.getJcxm());
            entity.setSm1(StringUtils.isEmpty(dto.getSm1())?"":dto.getSm1());
            if(dto.getCount()!=0){
                entity.setDataResult(Double.toString(div(dto.getSumDataResult(),dto.getCount(),7)));
            }else {
                entity.setDataResult("0.0");
            }
            avgMapper.insert(entity);
        }
        list.clear();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1            被除数
     * @param v2            除数
     * @param scale         表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public double div(Double v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

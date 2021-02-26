package com.pd.server.main.service;

import com.pd.server.main.domain.VehParkLotStatistics;
import com.pd.server.main.domain.VehParkLotStatisticsExample;
import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.dto.VehParkLotStatisticsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.mapper.VehParkLotStatisticsMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehParkLotStatisticsService {

@Resource
private VehParkLotStatisticsMapper vehParkLotStatisticsMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VehParkLotStatisticsExample vehParkLotStatisticsExample = new VehParkLotStatisticsExample();
        List<VehParkLotStatistics> vehParkLotStatisticsList = vehParkLotStatisticsMapper.selectByExample(vehParkLotStatisticsExample);
        PageInfo<VehParkLotStatistics> pageInfo = new PageInfo<>(vehParkLotStatisticsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VehParkLotStatisticsDto> vehParkLotStatisticsDtoList = CopyUtil.copyList(vehParkLotStatisticsList, VehParkLotStatisticsDto.class);
        pageDto.setList(vehParkLotStatisticsDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehParkLotStatisticsDto vehParkLotStatisticsDto) {
        VehParkLotStatistics vehParkLotStatistics = CopyUtil.copy(vehParkLotStatisticsDto, VehParkLotStatistics.class);
        if (StringUtils.isEmpty(vehParkLotStatisticsDto.getId())) {
            this.insert(vehParkLotStatistics);
        } else {
            this.update(vehParkLotStatistics);
        }
    }

    /**
    * 新增
    */
    private void insert(VehParkLotStatistics vehParkLotStatistics) {
        vehParkLotStatistics.setId(UuidUtil.getShortUuid());
        vehParkLotStatisticsMapper.insert(vehParkLotStatistics);
    }

    /**
    * 更新
    */
    private void update(VehParkLotStatistics vehParkLotStatistics) {
        vehParkLotStatisticsMapper.updateByPrimaryKey(vehParkLotStatistics);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehParkLotStatisticsMapper.deleteByPrimaryKey(id);
    }



    /**
     * 按条件统计
     */
    public Map<String ,Object> getStatistics(VehParkLotStatisticsDto dto){
        Map<String ,Object>  map = new HashMap<String ,Object>();
        VehParkLotStatisticsDto allvo = new  VehParkLotStatisticsDto();//总计
        Integer sumddchpall = 0;
        Integer sumddcnpall = 0;
        Integer sumddcwpall = 0;
        Integer summtcypall = 0;
        Integer summtcwpall = 0;
        Integer sumslcall = 0;
        Integer sumqcall = 0;
        Integer sumnycall = 0;
        Integer sumqtall = 0;
        List<VehParkLotStatisticsDto> list = vehParkLotStatisticsMapper.getStatistics(dto);
        if(!CollectionUtils.isEmpty(list)){
            for(VehParkLotStatisticsDto vo :list){
                sumddchpall = sumddchpall+ vo.getSumddchp();
                sumddcnpall =sumddcnpall + vo.getSumddcnp();
                sumddcwpall =sumddcwpall + vo.getSumddcwp();
                summtcypall =summtcypall + vo.getSummtcyp();
                summtcwpall =summtcwpall + vo.getSummtcwp();
                sumslcall   = sumslcall + vo.getSumslc();
                sumqcall    = sumqcall + vo.getSumqc();
                sumnycall   = sumnycall + vo.getSumnyc();
                sumqtall    =sumqtall + vo.getSumqt();
            }
        }
        allvo.setSumddchp(sumddchpall);
        allvo.setSumddcnp(sumddcnpall);
        allvo.setSumddcwp(sumddcwpall);
        allvo.setSummtcyp(summtcypall);
        allvo.setSummtcwp(summtcwpall);
        allvo.setSumslc(sumslcall);
        allvo.setSumqc(sumqcall);
        allvo.setSumnyc(sumnycall);
        allvo.setSumqt(sumqtall);
        map.put("mx",list);
        map.put("zj",allvo);
        return map;
    }


}

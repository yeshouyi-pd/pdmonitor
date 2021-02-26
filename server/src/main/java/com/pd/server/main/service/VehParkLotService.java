package com.pd.server.main.service;

import com.pd.server.main.domain.VehParkLot;
import com.pd.server.main.domain.VehParkLotExample;
import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.dto.WxYyStatisticsDto;
import com.pd.server.main.dto.basewx.my.VehParkLotGuoupDto;
import com.pd.server.main.mapper.VehParkLotMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class VehParkLotService {

@Resource
private VehParkLotMapper vehParkLotMapper;

    /**
    * 列表查询
    */
    public List<VehParkLot> list(VehParkLotExample vehParkLotExample) {
        List<VehParkLot> vehParkLotList = vehParkLotMapper.selectByExample(vehParkLotExample);
        return vehParkLotList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehParkLotDto vehParkLotDto) {
        VehParkLot vehParkLot = CopyUtil.copy(vehParkLotDto, VehParkLot.class);
        if (StringUtils.isEmpty(vehParkLotDto.getId())) {
            this.insert(vehParkLot);
        } else {
            this.update(vehParkLot);
        }
    }

    /**
    * 新增
    */
    private void insert(VehParkLot vehParkLot) {
        Date now = new Date();
        vehParkLot.setId(UuidUtil.getShortUuid());
        vehParkLotMapper.insert(vehParkLot);
    }

    /**
    * 更新
    */
    private void update(VehParkLot vehParkLot) {
        vehParkLotMapper.updateByPrimaryKeySelective(vehParkLot);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        vehParkLotMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取今天的统计
     */
    public Map<String ,Object> getStatisticsByToday(VehParkLotDto vehParkLotDto){
        Map<String ,Object>  map = new HashMap<String ,Object>();
        List<VehParkLotGuoupDto> lists = new ArrayList<VehParkLotGuoupDto>();
        if("1".equals(vehParkLotDto.getZt())){
            lists = vehParkLotMapper.getStatisticsByRcToday(vehParkLotDto);
        }
        if("2".equals(vehParkLotDto.getZt())){
            lists = vehParkLotMapper.getStatisticsByCcToday(vehParkLotDto);
        }
        VehParkLotDto allvo = new  VehParkLotDto();//总计
        Integer sumddchpall = 0;
        Integer sumddcnpall = 0;
        Integer sumddcwpall = 0;
        Integer summtcypall = 0;
        Integer summtcwpall = 0;
        Integer sumslcall = 0;
        Integer sumqcall = 0;
        Integer sumnycall = 0;
        Integer sumqtall = 0;
        Map<String, VehParkLotDto> sysOrgCodeMap = new HashMap<String, VehParkLotDto>();
        if(!CollectionUtils.isEmpty(lists)){
            /**
             * 根据 cllb zt syscode 分组
             */
            for(VehParkLotGuoupDto vo : lists){
                Integer sumddchp = 0;
                Integer sumddcnp = 0;
                Integer sumddcwp = 0;
                Integer summtcyp = 0;
                Integer summtcwp = 0;
                Integer sumslc = 0;
                Integer sumqc = 0;
                Integer sumnyc = 0;
                Integer sumqt = 0;
                VehParkLotDto thisvo = new  VehParkLotDto();
                if(sysOrgCodeMap.containsKey(vo.getSyscode())){
                    thisvo = sysOrgCodeMap.get(vo.getSyscode());
                }
                thisvo.setSysOrgCode(vo.getSyscode());
                if("1401".equals(vo.getCllb())&&"2".equals(vo.getCpys())){
                    sumddchp = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    sumddchpall =sumddchpall+ sumddchp;
                    thisvo.setSumddchp(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSumddchp())){
                        thisvo.setSumddchp(0);
                    }
                }
                if("1401".equals(vo.getCllb())&&"1".equals(vo.getCpys())){
                    sumddcnp = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    sumddcnpall = sumddcnpall+sumddcnp;
                    thisvo.setSumddcnp(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSumddcnp())){
                        thisvo.setSumddcnp(0);
                    }
                }
                if("1401".equals(vo.getCllb())&&"8".equals(vo.getCpys())){
                    sumddcwp = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    sumddcwpall = sumddcwpall+sumddcwp;
                    thisvo.setSumddcwp(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSumddcwp())){
                        thisvo.setSumddcwp(0);
                    }
                }
                if("1403".equals(vo.getCllb())&&"7".equals(vo.getCpys())){
                    summtcyp = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    summtcypall = summtcypall+summtcyp;
                    thisvo.setSummtcyp(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSummtcyp())){
                        thisvo.setSummtcyp(0);
                    }
                }
                if("1403".equals(vo.getCllb())&&"8".equals(vo.getCpys())){
                    summtcwp = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    summtcwpall = summtcwpall+summtcwp;
                    thisvo.setSummtcwp(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSummtcwp())){
                        thisvo.setSummtcwp(0);
                    }
                }
                if("1404".equals(vo.getCllb())){
                    sumslc = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    sumslcall = sumslcall +sumslc;
                    thisvo.setSumslc(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSumslc())){
                        thisvo.setSumslc(0);
                    }
                }
                if("1402".equals(vo.getCllb())){
                    sumqc = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    sumqcall = sumqcall+sumqc;
                    thisvo.setSumqc(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSumqc())){
                        thisvo.setSumqc(0);
                    }
                }
                if("1405".equals(vo.getCllb())){
                    sumnyc = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();
                    sumnycall =sumnycall+ sumnyc;
                    thisvo.setSumnyc(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSumnyc())){
                        thisvo.setSumnyc(0);
                    }
                }
                if("1406".equals(vo.getCllb())){
                    sumqt = StringUtils.isEmpty(vo.getCount())?0:vo.getCount();;
                    sumqtall =sumqtall+ sumqt;
                    thisvo.setSumqt(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(thisvo.getSumqt())){
                        thisvo.setSumqt(0);
                    }
                }
                //list.add(thisvo);
                sysOrgCodeMap.put(vo.getSyscode(),thisvo);
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
        Collection<VehParkLotDto> myCollection = sysOrgCodeMap.values();
        List<VehParkLotDto> list  = new ArrayList<VehParkLotDto>(myCollection);
        map.put("mx",list);
        map.put("zj",allvo);
        return map;
    }

    /**
     * 获取今天或今年的出场量
     */
    public List<VehParkLotGuoupDto> getStatisticsByDay(VehParkLotDto vehParkLotDto){
        if("1".equals(vehParkLotDto.getZt())){
            return vehParkLotMapper.getStatisticsByRcDay(vehParkLotDto);
        }else{
            return vehParkLotMapper.getStatisticsByCcDay(vehParkLotDto);
        }
    }
}

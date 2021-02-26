package com.pd.server.main.service;

import com.pd.server.main.domain.WxYy;
import com.pd.server.main.domain.WxYyExample;
import com.pd.server.main.domain.WxYyStatisticsExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.dto.WxYyDto;
import com.pd.server.main.dto.WxYyStatisticsDto;
import com.pd.server.main.dto.basewx.my.WxyyGuoupDto;
import com.pd.server.main.mapper.WxYyMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class WxYyService {

@Resource
private WxYyMapper wxYyMapper;

    /**
    * 列表查询
    */
    public List<WxYy> list(WxYyExample wxYyExample) {
        List<WxYy> wxYyList = wxYyMapper.selectByExample(wxYyExample);
        return wxYyList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WxYyDto wxYyDto) {
        WxYy wxYy = CopyUtil.copy(wxYyDto, WxYy.class);
        if (StringUtils.isEmpty(wxYyDto.getId())) {
            this.insert(wxYy);
        } else {
            this.update(wxYy);
        }
    }

    /**
    * 新增
    */
    private void insert(WxYy wxYy) {
        wxYy.setId(UuidUtil.getShortUuid());
        wxYyMapper.insert(wxYy);
    }

    /**
    * 更新
    */
    private void update(WxYy wxYy) {
    wxYyMapper.updateByPrimaryKeySelective(wxYy);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    wxYyMapper.deleteByPrimaryKey(id);
    }


    /**
     * 统计所有的预约数和到场数（不需要根据业务分类和业务类型进行分组统计）
     */
    public Map<String, WxYyDto> getStatisticsByTodayAndDeptcode(WxYyExample wxYyExample){
        List<WxyyGuoupDto> list = wxYyMapper.getStatisticsByTodayAndDeptcode(wxYyExample);
        Map<String, WxYyDto> deptMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)) {
            for (WxyyGuoupDto vo : list) {
                WxYyDto temp = new WxYyDto();
                if (deptMap.containsKey(vo.getDeptcode())) {
                    temp = deptMap.get(vo.getDeptcode());
                }
                temp.setDeptcode(vo.getDeptcode());
                //计算到场数
                if(vo.getZt().equals("4")){
                    if(StringUtils.isEmpty(temp.getAllDcs())){
                        temp.setAllDcs(0);
                    }else{
                        temp.setAllDcs(temp.getAllDcs()+1);
                    }
                }else{
                    temp.setAllDcs(0);
                }
                //计算预约数
                if(StringUtils.isEmpty(temp.getAllYysl())){
                    temp.setAllYysl(!StringUtils.isEmpty(vo.getCount())?vo.getCount():0);
                }else{
                    temp.setAllYysl(temp.getAllYysl() + vo.getCount());
                }
                deptMap.put(vo.getDeptcode(),temp);
            }
        }
        return deptMap;
    }

    /**
     * 获取今天的预约数
     */
    public Map<String ,Object> getYyslByDay(WxYyExample example){
        Map<String ,Object> map = new HashMap<String ,Object>();
        List<WxyyGuoupDto> list = wxYyMapper.getStatisticsByDay(example);
        Integer sumjdcyysl = 0;
        Integer sumjszyysl = 0;
        Integer sumwfyysl = 0;
        if(!CollectionUtils.isEmpty(list)){
            for(WxyyGuoupDto vo :list) {
                //计算预约数
                if("2".equals(vo.getYwfl())){
                    sumjdcyysl = sumjdcyysl+ vo.getCount();
                }
                if("3".equals(vo.getYwfl())){
                    sumjszyysl = sumjszyysl+ vo.getCount();
                }
                if("5".equals(vo.getYwfl())){
                    sumwfyysl = sumwfyysl+ vo.getCount();
                }
            }
        }
        map.put("sumjdcyysl",sumjdcyysl);
        map.put("sumjszyysl",sumjszyysl);
        map.put("sumwfyysl",sumwfyysl);
        return map;
    }

    /**
     * 统计所有的预约数和到场数（根据业务分类和业务类型进行分组统计）
     */
    public Map<String ,Object> getStatisticsByToday(WxYyExample example){
        Map<String ,Object>  map = new HashMap<String ,Object>();
        WxYyDto allvo = new  WxYyDto();//总计
        List<WxyyGuoupDto> list = wxYyMapper.getStatisticsByDay(example);
        Integer sumjdcxkcxczc = 0;
        Integer sumjdcescgh = 0;
        Integer sumjdcxkczr = 0;
        Integer sumjdcbhljdchp = 0;
        Integer sumjdcbhlxsz = 0;
        Integer sumjdcdyyw = 0;
        Integer sumjdcdzxzcdj = 0;
        Integer sumjdcdzxzycyw = 0;
        Integer sumjdcqtyw = 0;
        Integer sumjszwdzr = 0;
        Integer sumjszqmhz = 0;
        Integer sumjszbhlyw = 0;
        Integer sumjszjjhz = 0;
        Integer sumjszjwhz = 0;
        Integer sumjszmfyw = 0;
        Integer sumjszsyyw = 0;
        Integer sumjszqtyw = 0;
        Integer sumwfjdc = 0;
        Integer sumwfddc = 0;
        Integer sumjdcdcs = 0;
        Integer sumjszdcs = 0;
        Integer sumwfdcs = 0;
        Map<String, WxYyDto> deptMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)){
            /**
             * 根据 yyfl,yylx分组
             */
            for(WxyyGuoupDto vo :list){
                WxYyDto temp = new WxYyDto();
                if(deptMap.containsKey(vo.getDeptcode())){
                    temp = deptMap.get(vo.getDeptcode());
                }
                //计算到场数
                if("2".equals(vo.getYwfl())&&vo.getZt().equals("4")){
                    if(StringUtils.isEmpty(temp.getSumjdcdcs())){
                        temp.setSumjdcdcs(0);
                    }else{
                        temp.setSumjdcdcs(temp.getSumjdcdcs()+1);
                    }
                    sumjdcdcs = sumjdcdcs+ temp.getSumjdcdcs();
                }else{
                    temp.setSumjdcdcs(0);
                }
                if("3".equals(vo.getYwfl())&&vo.getZt().equals("4")){
                    if(StringUtils.isEmpty(temp.getSumjszdcs())){
                        temp.setSumjszdcs(0);
                    }else{
                        temp.setSumjszdcs(temp.getSumjszdcs()+1);
                    }
                    sumjszdcs = sumjszdcs+ temp.getSumjszdcs();
                }else{
                    temp.setSumjszdcs(0);
                }
                if("5".equals(vo.getYwfl())&&vo.getZt().equals("4")){
                    if(StringUtils.isEmpty(temp.getSumwfdcs())){
                        temp.setSumwfdcs(0);
                    }else{
                        temp.setSumwfdcs(temp.getSumwfdcs()+1);
                    }
                    sumwfdcs = sumwfdcs+ temp.getSumwfdcs();
                }else{
                    temp.setSumwfdcs(0);
                }
                //计算预约数
                if("2".equals(vo.getYwfl())&&"1".equals(vo.getYwlx())){
                    sumjdcxkcxczc = sumjdcxkcxczc + vo.getCount();
                    temp.setSumjdcxkcxczc(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcxkcxczc())){
                        temp.setSumjdcxkcxczc(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"2".equals(vo.getYwlx())){
                    sumjdcescgh = sumjdcescgh + vo.getCount();
                    temp.setSumjdcescgh(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcescgh())){
                        temp.setSumjdcescgh(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"3".equals(vo.getYwlx())){
                    sumjdcxkczr = sumjdcxkczr + vo.getCount();
                    temp.setSumjdcxkczr(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcxkczr())){
                        temp.setSumjdcxkczr(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"4".equals(vo.getYwlx())){
                    sumjdcbhljdchp = sumjdcbhljdchp + vo.getCount();
                    temp.setSumjdcbhljdchp(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcbhljdchp())){
                        temp.setSumjdcbhljdchp(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"5".equals(vo.getYwlx())){
                    sumjdcbhlxsz = sumjdcbhlxsz + vo.getCount();
                    temp.setSumjdcbhlxsz(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcbhlxsz())){
                        temp.setSumjdcbhlxsz(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"6".equals(vo.getYwlx())){
                    sumjdcdyyw = sumjdcdyyw + vo.getCount();
                    temp.setSumjdcdyyw(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcdyyw())){
                        temp.setSumjdcdyyw(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"7".equals(vo.getYwlx())){
                    sumjdcdzxzcdj = sumjdcdzxzcdj + vo.getCount();
                    temp.setSumjdcdzxzcdj(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcdzxzcdj())){
                        temp.setSumjdcdzxzcdj(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"8".equals(vo.getYwlx())){
                    sumjdcdzxzycyw = sumjdcdzxzycyw + vo.getCount();
                    temp.setSumjdcdzxzycyw(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcdzxzycyw())){
                        temp.setSumjdcdzxzycyw(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"99".equals(vo.getYwlx())){
                    sumjdcqtyw = sumjdcqtyw + vo.getCount();
                    temp.setSumjdcqtyw(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcqtyw())){
                        temp.setSumjdcqtyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"1".equals(vo.getYwlx())){
                    sumjszwdzr = sumjszwdzr + vo.getCount();
                    temp.setSumjszwdzr(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszwdzr())){
                        temp.setSumjszwdzr(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"2".equals(vo.getYwlx())){
                    sumjszqmhz = sumjszqmhz + vo.getCount();
                    temp.setSumjszqmhz(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszqmhz())){
                        temp.setSumjszqmhz(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"3".equals(vo.getYwlx())){
                    sumjszbhlyw = sumjszbhlyw + vo.getCount();
                    temp.setSumjszbhlyw(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszbhlyw())){
                        temp.setSumjszbhlyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"4".equals(vo.getYwlx())){
                    sumjszjjhz = sumjszjjhz + vo.getCount();
                    temp.setSumjszjjhz(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszjjhz())){
                        temp.setSumjszjjhz(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"5".equals(vo.getYwlx())){
                    sumjszjwhz = sumjszjwhz + vo.getCount();
                    temp.setSumjszjwhz(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszjwhz())){
                        temp.setSumjszjwhz(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"6".equals(vo.getYwlx())){
                    sumjszmfyw = sumjszmfyw + vo.getCount();
                    temp.setSumjszmfyw(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszmfyw())){
                        temp.setSumjszmfyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"7".equals(vo.getYwlx())){
                    sumjszsyyw = sumjszsyyw + vo.getCount();
                    temp.setSumjszsyyw(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszsyyw())){
                        temp.setSumjszsyyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"99".equals(vo.getYwlx())){
                    sumjszqtyw = sumjszqtyw + vo.getCount();
                    temp.setSumjszqtyw(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszqtyw())){
                        temp.setSumjszqtyw(0);
                    }
                }
                if("5".equals(vo.getYwfl())&&"A".equals(vo.getYwlx())){
                    sumwfjdc = sumwfjdc + vo.getCount();
                    temp.setSumwfjdc(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumwfjdc())){
                        temp.setSumwfjdc(0);
                    }
                }
                if("5".equals(vo.getYwfl())&&"B".equals(vo.getYwlx())){
                    sumwfddc = sumwfddc + vo.getCount();
                    temp.setSumwfddc(vo.getCount());
                }else{
                    if(StringUtils.isEmpty(temp.getSumwfddc())){
                        temp.setSumwfddc(0);
                    }
                }
                deptMap.put(vo.getDeptcode(),temp);
            }
        }
        allvo.setSumjdcxkcxczc(sumjdcxkcxczc);
        allvo.setSumjdcescgh(sumjdcescgh);
        allvo.setSumjdcxkczr(sumjdcxkczr);
        allvo.setSumjdcbhljdchp(sumjdcbhljdchp);
        allvo.setSumjdcbhlxsz(sumjdcbhlxsz);
        allvo.setSumjdcdyyw(sumjdcdyyw);
        allvo.setSumjdcdzxzcdj(sumjdcdzxzcdj);
        allvo.setSumjdcdzxzycyw(sumjdcdzxzycyw);
        allvo.setSumjdcqtyw(sumjdcqtyw);
        allvo.setSumjszwdzr(sumjszwdzr);
        allvo.setSumjszqmhz(sumjszqmhz);
        allvo.setSumjszbhlyw(sumjszbhlyw);
        allvo.setSumjszjjhz(sumjszjjhz);
        allvo.setSumjszjwhz(sumjszjwhz);
        allvo.setSumjszmfyw(sumjszmfyw);
        allvo.setSumjszsyyw(sumjszsyyw);
        allvo.setSumjszqtyw(sumjszqtyw);
        allvo.setSumwfjdc(sumwfjdc);
        allvo.setSumwfddc(sumwfddc);
        allvo.setSumjdcdcs(sumjdcdcs);
        allvo.setSumjszdcs(sumjszdcs);
        allvo.setSumwfdcs(sumwfdcs);
        Collection<WxYyDto> myCollection = deptMap.values();
        List<WxYyDto> mxList = new ArrayList<WxYyDto>(myCollection);
        map.put("mx", mxList);
        map.put("zj",allvo);
        return map;
    }

    /**
     * 获取预约数量根据预约时间分组
     */
    public List<WxyyGuoupDto> getYyslByExample(WxYyExample example){
        return wxYyMapper.getYyslByExample(example);
    }

    /**
     * 获取前10的预约数量
     */
    public List<WxyyGuoupDto> getYyslByBeforeTen(WxYyExample example){
        return wxYyMapper.getYyslByBeforeTen(example);
    }
}

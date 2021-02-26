package com.pd.server.main.service;

import com.pd.server.main.domain.WxYyStatistics;
import com.pd.server.main.domain.WxYyStatisticsExample;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.WxYyStatisticsDto;
import com.pd.server.main.mapper.WxYyStatisticsMapper;
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
public class WxYyStatisticsService {

    @Resource
    private WxYyStatisticsMapper wxYyStatisticsMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WxYyStatisticsExample wxYyStatisticsExample = new WxYyStatisticsExample();
        List<WxYyStatistics> wxYyStatisticsList = wxYyStatisticsMapper.selectByExample(wxYyStatisticsExample);
        PageInfo<WxYyStatistics> pageInfo = new PageInfo<>(wxYyStatisticsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WxYyStatisticsDto> wxYyStatisticsDtoList = CopyUtil.copyList(wxYyStatisticsList, WxYyStatisticsDto.class);
        pageDto.setList(wxYyStatisticsDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WxYyStatisticsDto wxYyStatisticsDto) {
        WxYyStatistics wxYyStatistics = CopyUtil.copy(wxYyStatisticsDto, WxYyStatistics.class);
        if (StringUtils.isEmpty(wxYyStatisticsDto.getId())) {
            this.insert(wxYyStatistics);
        } else {
            this.update(wxYyStatistics);
        }
    }

    /**
    * 新增
    */
    private void insert(WxYyStatistics wxYyStatistics) {
        wxYyStatistics.setId(UuidUtil.getShortUuid());
        wxYyStatisticsMapper.insert(wxYyStatistics);
    }

    /**
    * 更新
    */
    private void update(WxYyStatistics wxYyStatistics) {
    wxYyStatisticsMapper.updateByPrimaryKey(wxYyStatistics);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    wxYyStatisticsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 统计所有的预约数和到场数（不需要根据业务分类和业务类型进行分组统计）
     */
    public List<WxYyStatisticsDto> getStatisticsByDeptcode(WxYyStatisticsExample example){
        Map<String ,Object>  map = new HashMap<String ,Object>();
        List<WxYyStatisticsDto> list = wxYyStatisticsMapper.getStatisticsByDeptCode(example);
        Map<String, WxYyStatisticsDto> deptMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)) {
            for (WxYyStatisticsDto vo : list) {
                WxYyStatisticsDto temp = new WxYyStatisticsDto();
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
                    temp.setAllYysl(!StringUtils.isEmpty(vo.getSumYysl())?vo.getSumYysl():0);
                }else{
                    temp.setAllYysl(temp.getAllYysl() + vo.getSumYysl());
                }
                deptMap.put(vo.getDeptcode(),temp);
            }
        }
        Collection<WxYyStatisticsDto> myCollection = deptMap.values();
        List<WxYyStatisticsDto> mxList = new ArrayList<WxYyStatisticsDto>(myCollection);
        return mxList;
    }

    /**
     * 统计所有的预约数和到场数（不需要根据业务分类和业务类型进行分组统计）
     */
    public Map<String, WxYyStatisticsDto> getStatisticsByDeptcode1(WxYyStatisticsExample example){
        Map<String ,Object>  map = new HashMap<String ,Object>();
        List<WxYyStatisticsDto> list = wxYyStatisticsMapper.getStatisticsByDeptCode(example);
        Map<String, WxYyStatisticsDto> deptMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)) {
            for (WxYyStatisticsDto vo : list) {
                WxYyStatisticsDto temp = new WxYyStatisticsDto();
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
                }else {
                    temp.setAllDcs(0);
                }
                //计算预约数
                if(StringUtils.isEmpty(temp.getAllYysl())){
                    temp.setAllYysl(!StringUtils.isEmpty(vo.getSumYysl())?vo.getSumYysl():0);
                }else{
                    temp.setAllYysl(temp.getAllYysl() + vo.getSumYysl());
                }
                deptMap.put(vo.getDeptcode(),temp);
            }
        }
        return deptMap;
    }

    /**
     * 统计所有的预约数和到场数（根据业务分类和业务类型进行分组统计）
     */
    public Map<String ,Object> getStatistics(WxYyStatisticsExample example){
        Map<String ,Object>  map = new HashMap<String ,Object>();
        WxYyStatisticsDto allvo = new  WxYyStatisticsDto();//总计
        List<WxYyStatisticsDto> list = wxYyStatisticsMapper.getStatistics(example);
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
        Map<String, WxYyStatisticsDto> deptMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)){
            /**
             * 根据 yyfl,yylx分组
             */
            for(WxYyStatisticsDto vo :list){
                WxYyStatisticsDto temp = new WxYyStatisticsDto();
                if(deptMap.containsKey(vo.getDeptcode())){
                    temp = deptMap.get(vo.getDeptcode());
                }
                temp.setDeptcode(vo.getDeptcode());
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
                    sumjdcxkcxczc = sumjdcxkcxczc + vo.getSumYysl();
                    temp.setSumjdcxkcxczc(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcxkcxczc())){
                        temp.setSumjdcxkcxczc(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"2".equals(vo.getYwlx())){
                    sumjdcescgh = sumjdcescgh + vo.getSumYysl();
                    temp.setSumjdcescgh(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcescgh())){
                        temp.setSumjdcescgh(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"3".equals(vo.getYwlx())){
                    sumjdcxkczr = sumjdcxkczr + vo.getSumYysl();
                    temp.setSumjdcxkczr(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcxkczr())){
                        temp.setSumjdcxkczr(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"4".equals(vo.getYwlx())){
                    sumjdcbhljdchp = sumjdcbhljdchp + vo.getSumYysl();
                    temp.setSumjdcbhljdchp(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcbhljdchp())){
                        temp.setSumjdcbhljdchp(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"5".equals(vo.getYwlx())){
                    sumjdcbhlxsz = sumjdcbhlxsz + vo.getSumYysl();
                    temp.setSumjdcbhlxsz(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcbhlxsz())){
                        temp.setSumjdcbhlxsz(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"6".equals(vo.getYwlx())){
                    sumjdcdyyw = sumjdcdyyw + vo.getSumYysl();
                    temp.setSumjdcdyyw(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcdyyw())){
                        temp.setSumjdcdyyw(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"7".equals(vo.getYwlx())){
                    sumjdcdzxzcdj = sumjdcdzxzcdj + vo.getSumYysl();
                    temp.setSumjdcdzxzcdj(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcdzxzcdj())){
                        temp.setSumjdcdzxzcdj(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"8".equals(vo.getYwlx())){
                    sumjdcdzxzycyw = sumjdcdzxzycyw + vo.getSumYysl();
                    temp.setSumjdcdzxzycyw(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcdzxzycyw())){
                        temp.setSumjdcdzxzycyw(0);
                    }
                }
                if("2".equals(vo.getYwfl())&&"99".equals(vo.getYwlx())){
                    sumjdcqtyw = sumjdcqtyw + vo.getSumYysl();
                    temp.setSumjdcqtyw(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjdcqtyw())){
                        temp.setSumjdcqtyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"1".equals(vo.getYwlx())){
                    sumjszwdzr = sumjszwdzr + vo.getSumYysl();
                    temp.setSumjszwdzr(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszwdzr())){
                        temp.setSumjszwdzr(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"2".equals(vo.getYwlx())){
                    sumjszqmhz = sumjszqmhz + vo.getSumYysl();
                    temp.setSumjszqmhz(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszqmhz())){
                        temp.setSumjszqmhz(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"3".equals(vo.getYwlx())){
                    sumjszbhlyw = sumjszbhlyw + vo.getSumYysl();
                    temp.setSumjszbhlyw(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszbhlyw())){
                        temp.setSumjszbhlyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"4".equals(vo.getYwlx())){
                    sumjszjjhz = sumjszjjhz + vo.getSumYysl();
                    temp.setSumjszjjhz(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszjjhz())){
                        temp.setSumjszjjhz(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"5".equals(vo.getYwlx())){
                    sumjszjwhz = sumjszjwhz + vo.getSumYysl();
                    temp.setSumjszjwhz(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszjwhz())){
                        temp.setSumjszjwhz(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"6".equals(vo.getYwlx())){
                    sumjszmfyw = sumjszmfyw + vo.getSumYysl();
                    temp.setSumjszmfyw(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszmfyw())){
                        temp.setSumjszmfyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"7".equals(vo.getYwlx())){
                    sumjszsyyw = sumjszsyyw + vo.getSumYysl();
                    temp.setSumjszsyyw(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszsyyw())){
                        temp.setSumjszsyyw(0);
                    }
                }
                if("3".equals(vo.getYwfl())&&"99".equals(vo.getYwlx())){
                    sumjszqtyw = sumjszqtyw + vo.getSumYysl();
                    temp.setSumjszqtyw(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumjszqtyw())){
                        temp.setSumjszqtyw(0);
                    }
                }
                if("5".equals(vo.getYwfl())&&"A".equals(vo.getYwlx())){
                    sumwfjdc = sumwfjdc + vo.getSumYysl();
                    temp.setSumwfjdc(vo.getSumYysl());
                }else{
                    if(StringUtils.isEmpty(temp.getSumwfjdc())){
                        temp.setSumwfjdc(0);
                    }
                }
                if("5".equals(vo.getYwfl())&&"B".equals(vo.getYwlx())){
                    sumwfddc = sumwfddc + vo.getSumYysl();
                    temp.setSumwfddc(vo.getSumYysl());
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
        Collection<WxYyStatisticsDto> myCollection = deptMap.values();
        List<WxYyStatisticsDto> mxList = new ArrayList<WxYyStatisticsDto>(myCollection);
        map.put("mx",mxList);
        map.put("zj",allvo);
        return map;
    }

}

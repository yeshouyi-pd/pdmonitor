package com.pd.server.main.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.pd.server.main.domain.AppMonitorExp;
import com.pd.server.main.domain.AppMonitorExpExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.mapper.AppMonitorExpMapper;
import com.pd.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AppMonitorExpService {

    @Resource
    private AppMonitorExpMapper appMonitorExpMapper;

    public List<AppMonitorExpDto> getShowGps(EquipmentFileTyDto equipmentFileTyDto) throws ParseException {
        if(StringUtils.isBlank(equipmentFileTyDto.getSbbh())){
            return null;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            AppMonitorExpExample appMonitorExpExample = new AppMonitorExpExample();
            AppMonitorExpExample.Criteria criteria = appMonitorExpExample.createCriteria();
            criteria.andSbbhEqualTo(equipmentFileTyDto.getSbbh());
            criteria.andEventsEqualTo("S");
            if(StringUtils.isNotBlank(equipmentFileTyDto.getStime())){
                criteria.andCjsjGreaterThanOrEqualTo( sdf.parse(equipmentFileTyDto.getStime() + " 00:00:00"));
            }
            if (StringUtils.isNotBlank(equipmentFileTyDto.getEtime())){
                criteria.andCjsjLessThanOrEqualTo( sdf.parse(equipmentFileTyDto.getEtime() + " 23:59:59"));
            }
            List<AppMonitorExp> appMonitorExps = appMonitorExpMapper.selectByExample(appMonitorExpExample);
            return CopyUtil.copyList(appMonitorExps, AppMonitorExpDto.class);
        }

    }


    public List<AppMonitorExpDto> listNoGps(){
        AppMonitorExpExample appMonitorExpExample = new AppMonitorExpExample();
        AppMonitorExpExample.Criteria criteria = appMonitorExpExample.createCriteria();
        criteria.andDeclatEqualTo("0");//未获取到
        criteria.andEventsEqualTo("S");//发现江豚
        List<AppMonitorExp> appMonitorExpList = appMonitorExpMapper.selectByExample(appMonitorExpExample);
        return CopyUtil.copyList(appMonitorExpList, AppMonitorExpDto.class);
    }

    /**
    * 列表查询
    */
    public PageDto list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorExpExample appMonitorExpExample = new AppMonitorExpExample();
        AppMonitorExpExample.Criteria criteria = appMonitorExpExample.createCriteria();
        // 将分页对象映射为查询DTO以承接前端传参
        AppMonitorExpDto query = CopyUtil.copy(pageDto, AppMonitorExpDto.class);
        if (query != null) {
            if (StringUtils.isNotBlank(query.getDate1())) {
                criteria.andDate1EqualTo(query.getDate1());
            }
            if (StringUtils.isNotBlank(query.getGczxm())) {
                criteria.andGczxmEqualTo(query.getGczxm());
            }
        }
        // 排序：按日期、时间倒序
        appMonitorExpExample.setOrderByClause("cjsj asc");
        List<AppMonitorExp> appMonitorExpList = appMonitorExpMapper.selectByExample(appMonitorExpExample);
        PageInfo<AppMonitorExp> pageInfo = new PageInfo<>(appMonitorExpList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorExpDto> appMonitorExpDtoList = CopyUtil.copyList(appMonitorExpList, AppMonitorExpDto.class);
        pageDto.setList(appMonitorExpDtoList);
        return pageDto;
    }

    public List<AppMonitorExpDto> listByDay(AppMonitorExpDto appMonitorExpDto) {

        AppMonitorExpExample appMonitorExpExample = new AppMonitorExpExample();
        AppMonitorExpExample.Criteria criteria = appMonitorExpExample.createCriteria();

        if (appMonitorExpDto != null) {
            if (StringUtils.isNotBlank(appMonitorExpDto.getDate1())) {
                criteria.andDate1EqualTo(appMonitorExpDto.getDate1().replaceAll("-", ""));
            }
            if (StringUtils.isNotBlank(appMonitorExpDto.getGczxm())) {
                criteria.andGczxmEqualTo(appMonitorExpDto.getGczxm());
            }
        }
        // 排序：按日期、时间倒序
        appMonitorExpExample.setOrderByClause("cjsj asc");
        List<AppMonitorExp> appMonitorExpList = appMonitorExpMapper.selectByExample(appMonitorExpExample);
        List<AppMonitorExpDto> appMonitorExpDtoList = CopyUtil.copyList(appMonitorExpList, AppMonitorExpDto.class);
        return appMonitorExpDtoList;
    }


    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(AppMonitorExpDto appMonitorExpDto) {
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);
    }

    /**
    * 新增
    */
    private void insert(AppMonitorExp appMonitorExp) {
        appMonitorExpMapper.insert(appMonitorExp);
    }

    /**
    * 更新
    */
    public void update(AppMonitorExp appMonitorExp) {
        appMonitorExpMapper.updateByPrimaryKey(appMonitorExp);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorExpMapper.deleteByPrimaryKey(id);
    }

    public void deleteByMid(String mid,String ev) {
        AppMonitorExpExample appMonitorExpExample = new AppMonitorExpExample();
        appMonitorExpExample.createCriteria().andMidEqualTo(mid).andEventsEqualTo(ev);
        appMonitorExpMapper.deleteByExample(appMonitorExpExample);
    }


    /**
     * 主表 转换 start 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToStart(AppMonitorInfoDto appMonitorInfo,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
                appMonitorInfo.getKsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getKsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getKsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("start");
        appMonitorExpDto.setData1(codeMap.get("A").get(appMonitorInfo.getKcclx()));//考察船类型
        appMonitorExpDto.setData2(appMonitorInfo.getGczzwm());//观察者中文姓名
        appMonitorExpDto.setData3(appMonitorInfo.getGcqy());//观察区域
        appMonitorExpDto.setData4(appMonitorInfo.getGcdw());//观察单位
        appMonitorExpDto.setDeptcode(appMonitorInfo.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorInfo.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorInfo.getGczzwm());
        appMonitorExpDto.setTypes("1"); //表类型
        appMonitorExpDto.setMid(appMonitorInfo.getId());
        appMonitorExpDto.setCjsj(appMonitorInfo.getKsgcsj());
        appMonitorExpDto.setBid(appMonitorInfo.getId());
        appMonitorExpDto.setSbbh(appMonitorInfo.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorInfo.getId(),"start");
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 B 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToB(AppMonitorInfoDto appMonitorInfo,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
                appMonitorInfo.getKsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getKsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getKsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("B");
        appMonitorExpDto.setDeptcode(appMonitorInfo.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorInfo.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorInfo.getGczzwm());
        appMonitorExpDto.setTypes("1");//表类型
        appMonitorExpDto.setMid(appMonitorInfo.getId());
        appMonitorExpDto.setCjsj(new Date((appMonitorInfo.getKsgcsj().getTime() + 1000)));
        appMonitorExpDto.setBid(appMonitorInfo.getId());
        appMonitorExpDto.setSbbh(appMonitorInfo.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorInfo.getId(),"B");
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 E 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToE(AppMonitorInfoDto appMonitorInfo,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getJsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getJsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorInfo.getJsgps()) &&
                appMonitorInfo.getJsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getJsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getJsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("E");
        appMonitorExpDto.setDeptcode(appMonitorInfo.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorInfo.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorInfo.getGczzwm());
        appMonitorExpDto.setTypes("1");
        appMonitorExpDto.setMid(appMonitorInfo.getId());
        appMonitorExpDto.setCjsj(appMonitorInfo.getJsgcsj());
        appMonitorExpDto.setBid(appMonitorInfo.getId());
        appMonitorExpDto.setSbbh(appMonitorInfo.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorInfo.getId(),"E");
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 P 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToP(AppMonitorInfoDto appMonitorInfo,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
                appMonitorInfo.getKsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getKsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getKsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("P");
        appMonitorExpDto.setData1(appMonitorInfo.getPzfgcz());//左方观察者
        appMonitorExpDto.setData2(appMonitorInfo.getPjlz()); //记录者
        appMonitorExpDto.setData3(appMonitorInfo.getPyfgcz());//右方观察者
        appMonitorExpDto.setData4(appMonitorInfo.getPdlgcz());//独立观察者
        appMonitorExpDto.setData5(codeMap.get("B").get(appMonitorInfo.getPdlgczsyff()));//独立观察者所有方式
        appMonitorExpDto.setData6(appMonitorInfo.getPfjgcz());//附加观察者
        appMonitorExpDto.setDeptcode(appMonitorInfo.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorInfo.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorInfo.getGczzwm());
        appMonitorExpDto.setTypes("1");
        appMonitorExpDto.setMid(appMonitorInfo.getId());
        appMonitorExpDto.setCjsj(new Date((appMonitorInfo.getKsgcsj().getTime() + 2000)));
        appMonitorExpDto.setBid(appMonitorInfo.getId());
        appMonitorExpDto.setSbbh(appMonitorInfo.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorInfo.getId(),"P");
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 W 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToW(AppMonitorInfoDto appMonitorInfo,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
                appMonitorInfo.getKsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getKsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getKsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("W");
        appMonitorExpDto.setData1(codeMap.get("C").get(appMonitorInfo.getWztgczk()) );//总体观测状况
        appMonitorExpDto.setData2(codeMap.get("E").get( appMonitorInfo.getWxgzk()));//眩光状况
        appMonitorExpDto.setData3(codeMap.get("D").get(appMonitorInfo.getWxgszfw()));//眩光所在范围
        appMonitorExpDto.setDeptcode(appMonitorInfo.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorInfo.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorInfo.getGczzwm());
        appMonitorExpDto.setTypes("1");
        appMonitorExpDto.setMid(appMonitorInfo.getId());
        appMonitorExpDto.setCjsj(new Date((appMonitorInfo.getKsgcsj().getTime() + 3000)));
        appMonitorExpDto.setBid(appMonitorInfo.getId());
        appMonitorExpDto.setSbbh(appMonitorInfo.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorInfo.getId(),"W");
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 N 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToN(AppMonitorInfoDto appMonitorInfo,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
                appMonitorInfo.getKsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getKsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getKsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("N");
        appMonitorExpDto.setData1(appMonitorInfo.getNjajl());//距岸距离(米)
        appMonitorExpDto.setData2(codeMap.get("F").get(appMonitorInfo.getNjafx()));//近岸方向
        appMonitorExpDto.setData3(appMonitorInfo.getNss());//水深
        appMonitorExpDto.setData4(codeMap.get("G").get(appMonitorInfo.getNfx()));//方向
        appMonitorExpDto.setData5(appMonitorInfo.getNcs());//船速
        appMonitorExpDto.setDeptcode(appMonitorInfo.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorInfo.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorInfo.getGczzwm());
        appMonitorExpDto.setTypes("1");
        appMonitorExpDto.setMid(appMonitorInfo.getId());
        appMonitorExpDto.setCjsj(new Date((appMonitorInfo.getKsgcsj().getTime() + 4000)));
        appMonitorExpDto.setBid(appMonitorInfo.getId());
        appMonitorExpDto.setSbbh(appMonitorInfo.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorInfo.getId(),"N");
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 P 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToP(AppMonitorManualEntryeDto appMonitorManualEntryeDto,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
                appMonitorManualEntryeDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorManualEntryeDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorManualEntryeDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("P");
        appMonitorExpDto.setData1(appMonitorManualEntryeDto.getPzfgcz());//左方观察者
        appMonitorExpDto.setData2(appMonitorManualEntryeDto.getPjlz()); //记录者
        appMonitorExpDto.setData3(appMonitorManualEntryeDto.getPyfgcz());//右方观察者
        appMonitorExpDto.setData4(appMonitorManualEntryeDto.getPdlgcz());//独立观察者
        appMonitorExpDto.setData5(codeMap.get("B").get(appMonitorManualEntryeDto.getPdlgczsyff()));//独立观察者所有方式
        appMonitorExpDto.setData6(appMonitorManualEntryeDto.getPfjgcz());//附加观察者
        appMonitorExpDto.setDeptcode(appMonitorManualEntryeDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorManualEntryeDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorManualEntryeDto.getGczzwm());
        appMonitorExpDto.setTypes("2");
        appMonitorExpDto.setMid(appMonitorManualEntryeDto.getId());
        appMonitorExpDto.setCjsj(appMonitorManualEntryeDto.getKsgcsj());
        appMonitorExpDto.setBid(appMonitorManualEntryeDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorManualEntryeDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorManualEntryeDto.getId(),"P");
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 W 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToW(AppMonitorManualEntryeDto  appMonitorManualEntryeDto,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
                appMonitorManualEntryeDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorManualEntryeDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorManualEntryeDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("W");
        appMonitorExpDto.setData1(codeMap.get("C").get(appMonitorManualEntryeDto.getWztgczk()) );//总体观测状况
        appMonitorExpDto.setData2(codeMap.get("E").get(appMonitorManualEntryeDto.getWxgzk()));//眩光状况
        appMonitorExpDto.setData3(codeMap.get("D").get(appMonitorManualEntryeDto.getWxgszfw()));//眩光所在范围
        appMonitorExpDto.setDeptcode(appMonitorManualEntryeDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorManualEntryeDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorManualEntryeDto.getGczzwm());
        appMonitorExpDto.setTypes("2");
        appMonitorExpDto.setMid(appMonitorManualEntryeDto.getId());
        appMonitorExpDto.setCjsj(appMonitorManualEntryeDto.getKsgcsj());
        appMonitorExpDto.setBid(appMonitorManualEntryeDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorManualEntryeDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorManualEntryeDto.getId(),"W");
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 N 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToN(AppMonitorManualEntryeDto  appMonitorManualEntryeDto,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
                appMonitorManualEntryeDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorManualEntryeDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorManualEntryeDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("N");
        appMonitorExpDto.setData1(appMonitorManualEntryeDto.getNjajl());//距岸距离(米)
        appMonitorExpDto.setData2(codeMap.get("F").get(appMonitorManualEntryeDto.getNjafx()) );//近岸方向
        appMonitorExpDto.setData3(appMonitorManualEntryeDto.getNss());//水深
        appMonitorExpDto.setData4(codeMap.get("G").get(appMonitorManualEntryeDto.getNfx()) );//方向
        appMonitorExpDto.setData5(appMonitorManualEntryeDto.getNcs());//船速
        appMonitorExpDto.setDeptcode(appMonitorManualEntryeDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorManualEntryeDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorManualEntryeDto.getGczzwm());
        appMonitorExpDto.setTypes("2");
        appMonitorExpDto.setMid(appMonitorManualEntryeDto.getId());
        appMonitorExpDto.setCjsj(appMonitorManualEntryeDto.getKsgcsj());
        appMonitorExpDto.setBid(appMonitorManualEntryeDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorManualEntryeDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorManualEntryeDto.getId(),"N");
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 V 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToV(AppMonitorManualEntryeDto  appMonitorManualEntryeDto,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
                appMonitorManualEntryeDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorManualEntryeDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorManualEntryeDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("V");
        appMonitorExpDto.setData1(appMonitorManualEntryeDto.getVtbcsl());//停泊船数量
        appMonitorExpDto.setData2(appMonitorManualEntryeDto.getVydcsl());//移动船数量
        appMonitorExpDto.setData3(appMonitorManualEntryeDto.getVjlsc());//记录市场
        appMonitorExpDto.setDeptcode(appMonitorManualEntryeDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorManualEntryeDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorManualEntryeDto.getGczzwm());
        appMonitorExpDto.setTypes("2");
        appMonitorExpDto.setMid(appMonitorManualEntryeDto.getId());
        appMonitorExpDto.setCjsj(appMonitorManualEntryeDto.getKsgcsj());
        appMonitorExpDto.setBid(appMonitorManualEntryeDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorManualEntryeDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorManualEntryeDto.getId(),"V");
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 C 信息 Types 是表类型
     * @param appMonitorManualEntryeDto
     * @param codeMap
     */
    public void monitorManualToC(AppMonitorManualEntryeDto appMonitorManualEntryeDto, Map<String, Map<String, String>> codeMap) {
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
                appMonitorManualEntryeDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorManualEntryeDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorManualEntryeDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("C");
        appMonitorExpDto.setData1(appMonitorManualEntryeDto.getCzsbh());//注释编码
        appMonitorExpDto.setDeptcode(appMonitorManualEntryeDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorManualEntryeDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorManualEntryeDto.getGczzwm());
        appMonitorExpDto.setTypes ("2");
        appMonitorExpDto.setMid(appMonitorManualEntryeDto.getId());
        appMonitorExpDto.setCjsj(appMonitorManualEntryeDto.getKsgcsj());
        appMonitorExpDto.setBid(appMonitorManualEntryeDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorManualEntryeDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorManualEntryeDto.getId(),"C");
        this.insert(appMonitorExp);


    }


    /**
     * 发现江豚 转换 S 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    public void monitorDiscoveryToS(AppMonitorDiscoveryDto appMonitorDiscoveryDto,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorDiscoveryDto.getFxsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorDiscoveryDto.getFxsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorDiscoveryDto.getGps()) &&
                appMonitorDiscoveryDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorDiscoveryDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorDiscoveryDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("S");
        appMonitorExpDto.setData1(appMonitorDiscoveryDto.getSjl());//距离
        appMonitorExpDto.setData2(appMonitorDiscoveryDto.getSfw());//方位
        appMonitorExpDto.setData3(appMonitorDiscoveryDto.getSgcz());//观察者
        appMonitorExpDto.setData4(codeMap.get("L").get(appMonitorDiscoveryDto.getSff()) );//观察方法
        appMonitorExpDto.setData5(codeMap.get("H").get(appMonitorDiscoveryDto.getSwzm()) );//物种名
        appMonitorExpDto.setData6(appMonitorDiscoveryDto.getSqtzy());//最优
        appMonitorExpDto.setData7(appMonitorDiscoveryDto.getSqtzx());//最小
        appMonitorExpDto.setData8(appMonitorDiscoveryDto.getSqtzd());//最多
        appMonitorExpDto.setDeptcode(appMonitorDiscoveryDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorDiscoveryDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorDiscoveryDto.getGczzwm());
        appMonitorExpDto.setTypes("3");
        appMonitorExpDto.setMid(appMonitorDiscoveryDto.getId());
        appMonitorExpDto.setCjsj(appMonitorDiscoveryDto.getFxsj());
        appMonitorExpDto.setBid(appMonitorDiscoveryDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorDiscoveryDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorDiscoveryDto.getId(),"S");
        this.insert(appMonitorExp);

    }

    /**
     * 发现江豚 转换 H 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    public void monitorDiscoveryToH(AppMonitorDiscoveryDto appMonitorDiscoveryDto,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorDiscoveryDto.getFxsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorDiscoveryDto.getFxsj(), "HHmm"));
        if(org.apache.commons.lang.StringUtils.isNotBlank(appMonitorDiscoveryDto.getGps()) &&
                appMonitorDiscoveryDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorDiscoveryDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorDiscoveryDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("H");
        appMonitorExpDto.setData1(appMonitorDiscoveryDto.getHzjajl());//动物距最近岸的距离
        appMonitorExpDto.setData2(appMonitorDiscoveryDto.getHmztds());//母子豚对数
        if(StringUtils.isNotBlank(appMonitorDiscoveryDto.getHhjlxdm())){
            String[] hhjlxdms = appMonitorDiscoveryDto.getHhjlxdm().split(",");
            int len = hhjlxdms.length;
            StringBuilder hhjlxdm = new StringBuilder();
            for (int i = 0; i < len; i++){
                hhjlxdm.append(codeMap.get("J").get(hhjlxdms[i])).append(";");
            }
            appMonitorExpDto.setData3(hhjlxdm.toString());

        }else{
            appMonitorExpDto.setData3("");//环境类型
        }

        appMonitorExpDto.setData4(codeMap.get("I").get(appMonitorDiscoveryDto.getHxwms()) );//行为描述
        appMonitorExpDto.setDeptcode(appMonitorDiscoveryDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorDiscoveryDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorDiscoveryDto.getGczzwm());
        appMonitorExpDto.setTypes("3");
        appMonitorExpDto.setMid(appMonitorDiscoveryDto.getId());
        appMonitorExpDto.setCjsj (new Date((appMonitorDiscoveryDto.getFxsj().getTime() + 1000)));
        appMonitorExpDto.setBid(appMonitorDiscoveryDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorDiscoveryDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorDiscoveryDto.getId(),"H");
        this.insert(appMonitorExp);

    }


    /**
     * 发现江豚 转换 C 信息 --弃用
     * @param appMonitorDiscoveryDto
     * @return
     */
    public void monitorDiscoveryToC(AppMonitorDiscoveryDto appMonitorDiscoveryDto,Map<String, Map<String, String>> codeMap){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorDiscoveryDto.getFxsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorDiscoveryDto.getFxsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorDiscoveryDto.getGps()) &&
                appMonitorDiscoveryDto.getGps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorDiscoveryDto.getGps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorDiscoveryDto.getGps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("C");
        appMonitorExpDto.setData1(appMonitorDiscoveryDto.getCzsbh());//注释编码
        appMonitorExpDto.setDeptcode(appMonitorDiscoveryDto.getDeptcode());
        appMonitorExpDto.setGczxm(appMonitorDiscoveryDto.getGczxm());
        appMonitorExpDto.setGczzwm(appMonitorDiscoveryDto.getGczzwm());
        appMonitorExpDto.setTypes("3");
        appMonitorExpDto.setMid(appMonitorDiscoveryDto.getId());
        appMonitorExpDto.setCjsj(appMonitorDiscoveryDto.getFxsj());
        appMonitorExpDto.setBid(appMonitorDiscoveryDto.getMid());
        appMonitorExpDto.setSbbh(appMonitorDiscoveryDto.getSbbh());
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.deleteByMid(appMonitorDiscoveryDto.getId(),"C");
        this.insert(appMonitorExp);

    }



}
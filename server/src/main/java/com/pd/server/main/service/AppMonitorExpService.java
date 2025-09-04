package com.pd.server.main.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.pd.server.main.domain.AppMonitorExp;
import com.pd.server.main.domain.AppMonitorExpExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.mapper.AppMonitorExpMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppMonitorExpService {

    @Resource
    private AppMonitorExpMapper appMonitorExpMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AppMonitorExpExample appMonitorExpExample = new AppMonitorExpExample();
        List<AppMonitorExp> appMonitorExpList = appMonitorExpMapper.selectByExample(appMonitorExpExample);
        PageInfo<AppMonitorExp> pageInfo = new PageInfo<>(appMonitorExpList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AppMonitorExpDto> appMonitorExpDtoList = CopyUtil.copyList(appMonitorExpList, AppMonitorExpDto.class);
        pageDto.setList(appMonitorExpDtoList);
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
    private void update(AppMonitorExp appMonitorExp) {
        appMonitorExpMapper.updateByPrimaryKey(appMonitorExp);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        appMonitorExpMapper.deleteByPrimaryKey(id);
    }


    /**
     * 主表 转换 start 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToStart(AppMonitorInfoDto appMonitorInfo){
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
        appMonitorExpDto.setData1(appMonitorInfo.getKcclx());//考察船类型
        appMonitorExpDto.setData2(appMonitorInfo.getGczzwm());//观察者中文姓名
        appMonitorExpDto.setData3(appMonitorInfo.getGcqy());//观察区域
        appMonitorExpDto.setData4(appMonitorInfo.getGcdw());//观察单位
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 B 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToB(AppMonitorInfoDto appMonitorInfo){
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
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 E 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToE(AppMonitorInfoDto appMonitorInfo){
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
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 P 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToP(AppMonitorInfoDto appMonitorInfo){
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
        appMonitorExpDto.setData5(appMonitorInfo.getPdlgczsyff());//独立观察者所有方式
        appMonitorExpDto.setData6(appMonitorInfo.getPfjgcz());//附加观察者
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 W 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToW(AppMonitorInfoDto appMonitorInfo){
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
        appMonitorExpDto.setData1(appMonitorInfo.getWztgczk());//总体观测状况
        appMonitorExpDto.setData2(appMonitorInfo.getWxgzk());//眩光状况
        appMonitorExpDto.setData3(appMonitorInfo.getWxgszfw());//眩光所在范围

        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 主表 转换 N 信息
     * @param appMonitorInfo
     * @return
     */
    public void monitorInfoToN(AppMonitorInfoDto appMonitorInfo){
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
        appMonitorExpDto.setData2(appMonitorInfo.getNjafx());//近岸方向
        appMonitorExpDto.setData3(appMonitorInfo.getNss());//水深
        appMonitorExpDto.setData4(appMonitorInfo.getNfx());//方向
        appMonitorExpDto.setData5(appMonitorInfo.getNcs());//船速
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 P 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToP(AppMonitorManualEntryeDto appMonitorManualEntryeDto){
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
        appMonitorExpDto.setData5(appMonitorManualEntryeDto.getPdlgczsyff());//独立观察者所有方式
        appMonitorExpDto.setData6(appMonitorManualEntryeDto.getPfjgcz());//附加观察者
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 W 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToW(AppMonitorManualEntryeDto  appMonitorManualEntryeDto){
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
        appMonitorExpDto.setData1(appMonitorManualEntryeDto.getWztgczk());//总体观测状况
        appMonitorExpDto.setData2(appMonitorManualEntryeDto.getWxgzk());//眩光状况
        appMonitorExpDto.setData3(appMonitorManualEntryeDto.getWxgszfw());//眩光所在范围
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 N 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToN(AppMonitorManualEntryeDto  appMonitorManualEntryeDto){
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
        appMonitorExpDto.setData2(appMonitorManualEntryeDto.getNjafx());//近岸方向
        appMonitorExpDto.setData3(appMonitorManualEntryeDto.getNss());//水深
        appMonitorExpDto.setData4(appMonitorManualEntryeDto.getNfx());//方向
        appMonitorExpDto.setData5(appMonitorManualEntryeDto.getNcs());//船速
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 观察表 转换 V 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    public void monitorManualToV(AppMonitorManualEntryeDto  appMonitorManualEntryeDto){
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
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }


    /**
     * 发现江豚 转换 S 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    public void monitorDiscoveryToS(AppMonitorDiscoveryDto appMonitorDiscoveryDto){
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
        appMonitorExpDto.setData4(appMonitorDiscoveryDto.getSff());//观察方法
        appMonitorExpDto.setData5(appMonitorDiscoveryDto.getSwzm());//物种名
        appMonitorExpDto.setData6(appMonitorDiscoveryDto.getSqtzy());//最优
        appMonitorExpDto.setData7(appMonitorDiscoveryDto.getSqtzx());//最小
        appMonitorExpDto.setData8(appMonitorDiscoveryDto.getSqtzd());//最多
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }

    /**
     * 发现江豚 转换 H 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    public void monitorDiscoveryToH(AppMonitorDiscoveryDto appMonitorDiscoveryDto){
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
        appMonitorExpDto.setData3(appMonitorDiscoveryDto.getHhjlxdm());//环境类型
        appMonitorExpDto.setData4(appMonitorDiscoveryDto.getHxwms());//行为描述
        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }


    /**
     * 发现江豚 转换 C 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    public void monitorDiscoveryToC(AppMonitorDiscoveryDto appMonitorDiscoveryDto){
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

        AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
        this.insert(appMonitorExp);

    }


}
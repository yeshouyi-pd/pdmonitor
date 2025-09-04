package com.pd.system.controller.app;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.system.controller.conf.HttpResult;
import com.pd.system.controller.conf.RedisConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/ob")
public class AppObserverController {

    @Resource
    private AppVersionService appVersionService;

    @Resource
    private AppCodeSetService appCodeSetService;

    @Resource
    private AppCodeTypeService appCodeTypeService;

    @Resource
    private AppMonitorInfoService appMonitorInfoService;

    @Resource
    private AppMonitorDiscoveryService appMonitorDiscoveryService;

    @Resource
    private AppMonitorManualEntryeService appMonitorManualEntryeService;


    @Resource
    private UserService userService;

    @Resource
    private DeptService deptService;


    /**
     * 获取版本信息
     * @return
     */
    @GetMapping("/getVersion")
    public HttpResult getVersion() {
        List<AppVersion> versions = appVersionService.selectByExample();
        if (!versions.isEmpty()) {
            AppVersion version = versions.get(0);
            return HttpResult.ok(version);
        }
        return HttpResult.error("查询失败");
    }

    /**
     * 查询app Code set 配置
     * @return
     */
    @GetMapping("/getAppCodeSet")
    public HttpResult getAppCodeSet() {
        List<AppCodeSet> list = appCodeSetService.selectByExample();
        List<AppCodeSetDto> appCodeSetDtoList = CopyUtil.copyList(list, AppCodeSetDto.class);
        try {
            return HttpResult.ok(appCodeSetDtoList);
        }catch (Exception e){
            return HttpResult.error("查询失败");
        }

    }

    /**
     * 查询app Code type 配置
     * @return
     */
    @GetMapping("/getAppCodeType")
    public HttpResult getAppCodeType() {
        List<AppCodeType> list = appCodeTypeService.selectByExample();
        List<AppCodeTypeDto> AppCodeTypeDtoList = CopyUtil.copyList(list, AppCodeTypeDto.class);
        try {
            return HttpResult.ok(AppCodeTypeDtoList);
        }catch (Exception e){
            return HttpResult.error("查询失败");
        }

    }


    /**
     * 上传开始和结束信息
     * @param appMonitorInfoDto
     * @return
     */

    @PostMapping("/uploadMonitorInfo")
    public HttpResult uploadMonitorInfo(@RequestBody AppMonitorInfoDto appMonitorInfoDto) {
        if(StringUtils.isBlank(appMonitorInfoDto.getId()) || StringUtils.isBlank(appMonitorInfoDto.getDeptcode()) || StringUtils.isBlank(appMonitorInfoDto.getGczxm())){
            return HttpResult.error("上传参数异常");
        }
        try {
            appMonitorInfoService.save(appMonitorInfoDto);
            return HttpResult.ok();
        }catch (Exception e){
            return HttpResult.error("上传失败");
        }
    }

    /**
     * 根据用户部门和用户信息下载观察过程信息  所有数据
     * @param appMonitorInfoDto
     * @return
     */
    @PostMapping("/downloadMonitorInfo")
    public HttpResult downloadMonitorInfo(@RequestBody AppMonitorInfoDto appMonitorInfoDto) {
        if( StringUtils.isBlank(appMonitorInfoDto.getDeptcode())){
            return HttpResult.error("参数异常");
        }
        try {
            List<AppMonitorInfo> list = appMonitorInfoService.selectByExample(appMonitorInfoDto);
            List<AppMonitorInfoDto> respList = CopyUtil.copyList(list, AppMonitorInfoDto.class);
            return HttpResult.ok(respList);
        }catch (Exception e){
            return HttpResult.error("下载失败");
        }
    }







    /**
     * 上传10分钟 N 信息
     * 上传 30 分钟P  V  W  N
     * 人工观察信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    @PostMapping("/uploadManualEntrye")
    public HttpResult uploadManualEntrye(@RequestBody AppMonitorManualEntryeDto appMonitorManualEntryeDto) {
        if(StringUtils.isBlank(appMonitorManualEntryeDto.getMid()) || StringUtils.isBlank(appMonitorManualEntryeDto.getId())
        || StringUtils.isBlank(appMonitorManualEntryeDto.getDeptcode()) || StringUtils.isBlank(appMonitorManualEntryeDto.getGczxm())
        ){
            return HttpResult.error("上传参数异常");
        }
        try {
            appMonitorManualEntryeService.save(appMonitorManualEntryeDto);
            return HttpResult.ok();
        }catch (Exception e){
            return HttpResult.error("上传失败");
        }
    }


    /**
     * 下载人10分钟 N 信息
     * 下载  30 分钟P  V  W  N   所有数据
     * @param appMonitorManualEntryeDto
     * @return
     */
    @PostMapping("/downloadManualEntrye")
    public HttpResult downloadManualEntrye(@RequestBody AppMonitorManualEntryeDto appMonitorManualEntryeDto) {
        if( StringUtils.isBlank(appMonitorManualEntryeDto.getDeptcode())){
            return HttpResult.error("参数异常");
        }
        try {
            List<AppMonitorManualEntrye> list = appMonitorManualEntryeService.selectByExample(appMonitorManualEntryeDto);
            List<AppMonitorManualEntryeDto> respList = CopyUtil.copyList(list, AppMonitorManualEntryeDto.class);
            return HttpResult.ok(respList);
        }catch (Exception e){
            return HttpResult.error("下载失败");
        }
    }



    /**
     * 获取发现江豚信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    @PostMapping("/uploadMonitorDiscovery")
    public HttpResult uploadMonitorDiscovery(@RequestBody AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        if( StringUtils.isBlank(appMonitorDiscoveryDto.getDeptcode()) || StringUtils.isBlank(appMonitorDiscoveryDto.getGczxm())
        ||StringUtils.isBlank(appMonitorDiscoveryDto.getDeptcode()) || StringUtils.isBlank(appMonitorDiscoveryDto.getGczxm())){
            return HttpResult.error("上传参数异常");
        }
        try {
            appMonitorDiscoveryService.save(appMonitorDiscoveryDto);
            return HttpResult.ok();
        }catch (Exception e){
            return HttpResult.error("上传失败");
        }
    }


    /**
     * 下载所有发信江豚信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    @PostMapping("/downloadMonitorDiscovery")
    public HttpResult downloadMonitorDiscovery(@RequestBody AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        if( StringUtils.isBlank(appMonitorDiscoveryDto.getDeptcode())){
            return HttpResult.error("参数异常");
        }
        try {
            List<AppMonitorDiscovery> list = appMonitorDiscoveryService.selectByExample(appMonitorDiscoveryDto);
            List<AppMonitorDiscoveryDto> respList = CopyUtil.copyList(list, AppMonitorDiscoveryDto.class);
            return HttpResult.ok(respList);
        }catch (Exception e){
            return HttpResult.error("下载失败");
        }
    }


    /**
     * 巡查信息分页查询
     * @param appMonitorInfoDto
     * @return
     */
    @PostMapping("/findPageMonitorInfo")
    public HttpResult findPageMonitorInfo(@RequestBody AppMonitorInfoDto appMonitorInfoDto) {
        if( StringUtils.isBlank(appMonitorInfoDto.getDeptcode())){
            return HttpResult.error("参数异常");
        }
        try {
            PageDto pageDto = appMonitorInfoService.list(appMonitorInfoDto);
            return HttpResult.ok(pageDto);
        }catch (Exception e){
            return HttpResult.error("下载失败");
        }
    }


    /**
     * 当前巡查下所有的发现江豚信息
     * @param mid
     * @return
     */
    @GetMapping("/findDiscoveryByMid/{mid}")
    public HttpResult findDiscoveryByMid(@PathVariable(value = "mid") String mid) {
        if( StringUtils.isBlank(mid)){
            return HttpResult.error("参数异常");
        }
        try {
            List<AppMonitorDiscovery> list = appMonitorDiscoveryService.selectByMid(mid);
            List<AppMonitorDiscoveryDto> respList = CopyUtil.copyList(list, AppMonitorDiscoveryDto.class);
            return HttpResult.ok(respList);
        }catch(Exception e){
            return HttpResult.error("下载失败");
        }
    }

    /**
     * 当前巡查下所有的人工观察信息
     * @param mid
     * @return
     */
    @GetMapping("/findManualEntryeByMid/{mid}")
    public HttpResult findManualEntryeByMid(@PathVariable(value = "mid") String mid) {
        if( StringUtils.isBlank(mid)){
            return HttpResult.error("参数异常");
        }
        try {
            List<AppMonitorManualEntrye> list = appMonitorManualEntryeService.selectByMid(mid);
            List<AppMonitorManualEntryeDto> respList = CopyUtil.copyList(list, AppMonitorManualEntryeDto.class);
            return HttpResult.ok(respList);
        }catch(Exception e){
            return HttpResult.error("下载失败");
        }
    }


    /**
     * 发现江豚信息分页查询 关联迅游信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    @PostMapping("/findPageDiscovery")
    public HttpResult findPageDiscovery(@RequestBody AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        if( StringUtils.isBlank(appMonitorDiscoveryDto.getDeptcode())){
            return HttpResult.error("参数异常");
        }
        try {
            PageDto pageDto = appMonitorDiscoveryService.list(appMonitorDiscoveryDto);
            return HttpResult.ok(pageDto);
        }catch (Exception e){
            return HttpResult.error("下载失败");
        }
    }


    /**
     * 获取当前巡查信息
     * @param id
     * @return
     */
    @GetMapping("/findInfoById/{id}")
    public HttpResult findInfoById(@PathVariable(value = "id") String id) {
        if( StringUtils.isBlank(id)){
            return HttpResult.error("参数异常");
        }
        try {
            AppMonitorInfo appMonitorInfo =  appMonitorInfoService.findById(id);
            AppMonitorInfoDto dao =  CopyUtil.copy(appMonitorInfo ,AppMonitorInfoDto.class);
            return HttpResult.ok(dao);
        }catch(Exception e){
            return HttpResult.error("下载失败");
        }
    }


    @PostMapping("/appLgoin")
    public HttpResult appLgoin(@RequestBody UserDto userDto) {
        if( StringUtils.isBlank(userDto.getLoginName())|| StringUtils.isBlank(userDto.getPassword()) ){
            return HttpResult.error("参数异常");
        }
        userDto.setPassword(userDto.getPassword()+"!@#QWERT");
        userDto.setPassword( DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()).getBytes())  );
        User user =  userService.appLgoin(userDto);
        if(null == user){
            return HttpResult.error("账号或密码错误");
        }
        Dept dept = deptService.queryByDeptcode(user.getDeptcode());
        if(null != dept){
            user.setJyw(dept.getDeptname());
        }
        user.setPassword( null);
        return HttpResult.ok(user);
     }


    /**
     * 主表 转换 start 信息
     * @param appMonitorInfo
     * @return
     */
    private static AppMonitorExpDto monitorInfoToStart(AppMonitorInfoDto appMonitorInfo){
         AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
         appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
         appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
         appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
         if(StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
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

         return appMonitorExpDto;

     }

    /**
     * 主表 转换 B 信息
     * @param appMonitorInfo
     * @return
     */
    private static AppMonitorExpDto monitorInfoToB(AppMonitorInfoDto appMonitorInfo){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
                appMonitorInfo.getKsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getKsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getKsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("B");
        return appMonitorExpDto;

    }

    /**
     * 主表 转换 E 信息
     * @param appMonitorInfo
     * @return
     */
    private static AppMonitorExpDto monitorInfoToE(AppMonitorInfoDto appMonitorInfo){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getJsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getJsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorInfo.getJsgps()) &&
                appMonitorInfo.getJsgps().split(",").length ==2){
            appMonitorExpDto.setDeclat(appMonitorInfo.getJsgps().split(",")[0]);
            appMonitorExpDto.setDeclong(appMonitorInfo.getJsgps().split(",")[1]);
        }else{
            appMonitorExpDto.setDeclat("0");
            appMonitorExpDto.setDeclong("0");
        }
        appMonitorExpDto.setEvents("E");
        return appMonitorExpDto;

    }

    /**
     * 主表 转换 P 信息
     * @param appMonitorInfo
     * @return
     */
    private static AppMonitorExpDto monitorInfoToP(AppMonitorInfoDto appMonitorInfo){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
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
        return appMonitorExpDto;

    }

    /**
     * 主表 转换 W 信息
     * @param appMonitorInfo
     * @return
     */
    private static AppMonitorExpDto monitorInfoToW(AppMonitorInfoDto appMonitorInfo){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
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

        return appMonitorExpDto;

    }

    /**
     * 主表 转换 N 信息
     * @param appMonitorInfo
     * @return
     */
    private static AppMonitorExpDto monitorInfoToN(AppMonitorInfoDto appMonitorInfo){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorInfo.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorInfo.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorInfo.getKsgps()) &&
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
        return appMonitorExpDto;

    }

    /**
     * 观察表 转换 P 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    private static AppMonitorExpDto monitorManualToP(AppMonitorManualEntryeDto  appMonitorManualEntryeDto){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
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
        return appMonitorExpDto;

    }

    /**
     * 观察表 转换 W 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    private static AppMonitorExpDto monitorManualToW(AppMonitorManualEntryeDto  appMonitorManualEntryeDto){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
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
        return appMonitorExpDto;

    }

    /**
     * 观察表 转换 N 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    private static AppMonitorExpDto monitorManualToN(AppMonitorManualEntryeDto  appMonitorManualEntryeDto){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
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
        return appMonitorExpDto;

    }

    /**
     * 观察表 转换 V 信息
     * @param appMonitorManualEntryeDto
     * @return
     */
    private static AppMonitorExpDto monitorManualToV(AppMonitorManualEntryeDto  appMonitorManualEntryeDto){
        AppMonitorExpDto appMonitorExpDto = new AppMonitorExpDto();
        appMonitorExpDto.setId(IdUtil.getSnowflakeNextIdStr());
        appMonitorExpDto.setDate1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "yyyyMMdd"));
        appMonitorExpDto.setTime1(DateUtil.format(appMonitorManualEntryeDto.getKsgcsj(), "HHmm"));
        if(StringUtils.isNotBlank(appMonitorManualEntryeDto.getGps()) &&
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
        return appMonitorExpDto;

    }


    /**
     * 发现江豚 转换 S 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    private static AppMonitorExpDto monitorDiscoveryToS(AppMonitorDiscoveryDto appMonitorDiscoveryDto){
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
        appMonitorExpDto.setEvents("S");
        appMonitorExpDto.setData1(appMonitorDiscoveryDto.getSjl());//距离
        appMonitorExpDto.setData2(appMonitorDiscoveryDto.getSfw());//方位
        appMonitorExpDto.setData3(appMonitorDiscoveryDto.getSgcz());//观察者
        appMonitorExpDto.setData4(appMonitorDiscoveryDto.getSff());//观察方法
        appMonitorExpDto.setData5(appMonitorDiscoveryDto.getSwzm());//物种名
        appMonitorExpDto.setData6(appMonitorDiscoveryDto.getSqtzy());//最优
        appMonitorExpDto.setData7(appMonitorDiscoveryDto.getSqtzx());//最小
        appMonitorExpDto.setData8(appMonitorDiscoveryDto.getSqtzd());//最多
        return appMonitorExpDto;

    }

    /**
     * 发现江豚 转换 H 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    private static AppMonitorExpDto monitorDiscoveryToH(AppMonitorDiscoveryDto appMonitorDiscoveryDto){
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
        appMonitorExpDto.setEvents("H");
        appMonitorExpDto.setData1(appMonitorDiscoveryDto.getHzjajl());//动物距最近岸的距离
        appMonitorExpDto.setData2(appMonitorDiscoveryDto.getHmztds());//母子豚对数
        appMonitorExpDto.setData3(appMonitorDiscoveryDto.getHxwms());//行为描述
        int len = appMonitorDiscoveryDto.getHhjlxdm().split(",").length;
        if(StringUtils.isNotBlank(appMonitorDiscoveryDto.getHhjlxdm())  && len>0 ){
            //最多区5个
            appMonitorExpDto.setData4(appMonitorDiscoveryDto.getHhjlxdm().split(",")[0]);
            if(len >1){
                appMonitorExpDto.setData5(appMonitorDiscoveryDto.getHhjlxdm().split(",")[1]);
            }
            if(len >2){
                appMonitorExpDto.setData6(appMonitorDiscoveryDto.getHhjlxdm().split(",")[2]);
            }
            if(len >3){
                appMonitorExpDto.setData7(appMonitorDiscoveryDto.getHhjlxdm().split(",")[3]);
            }
            if(len >4){
                appMonitorExpDto.setData8(appMonitorDiscoveryDto.getHhjlxdm().split(",")[4]);
            }

        }
        return appMonitorExpDto;

    }


    /**
     * 发现江豚 转换 C 信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    private static AppMonitorExpDto monitorDiscoveryToC(AppMonitorDiscoveryDto appMonitorDiscoveryDto){
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

        return appMonitorExpDto;

    }


















}

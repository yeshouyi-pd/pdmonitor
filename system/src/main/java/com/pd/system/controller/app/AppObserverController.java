package com.pd.system.controller.app;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.system.controller.conf.HttpResult;
import com.pd.system.controller.conf.RedisConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/ob")
public class AppObserverController {


    private static final Logger LOG = LoggerFactory.getLogger(AppObserverController.class);
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

    @Resource
    private AppMonitorExpService appMonitorExpService;


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
            return HttpResult.error("查询失败："+e.getMessage());
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
            return HttpResult.error("查询失败："+e.getMessage());
        }

    }


    /**
     * 上传开始和结束信息
     * @param appMonitorInfoDto
     * @return
     */

    @PostMapping("/uploadMonitorInfo")
    @Transactional
    public HttpResult uploadMonitorInfo(@RequestBody AppMonitorInfoDto appMonitorInfoDto) {
        if(StringUtils.isBlank(appMonitorInfoDto.getId()) || StringUtils.isBlank(appMonitorInfoDto.getDeptcode()) || StringUtils.isBlank(appMonitorInfoDto.getGczxm())){
            return HttpResult.error("上传参数异常");
        }

        try {
            Map<String, Map<String, String>> codeMap = (Map<String, Map<String, String>>) RedisConfig.redisTstaticemplate.opsForValue().get(RedisCode.APPCODESET);

            appMonitorExpService.monitorInfoToStart(appMonitorInfoDto,codeMap);
            appMonitorExpService.monitorInfoToB(appMonitorInfoDto ,codeMap);
            appMonitorExpService.monitorInfoToP(appMonitorInfoDto,codeMap);
            appMonitorExpService.monitorInfoToW(appMonitorInfoDto,codeMap);
            //appMonitorExpService.monitorInfoToN(appMonitorInfoDto,codeMap);
            appMonitorExpService.monitorInfoToE(appMonitorInfoDto,codeMap);
            appMonitorInfoService.save(appMonitorInfoDto);
            return HttpResult.ok();
        }catch (Exception e){
            LOG.info("uploadMonitorInfo上传失败：{}", e.getMessage());
            LOG.info("uploadMonitorInfo上传失败：{}", JSON.toJSONString(appMonitorInfoDto));
            return HttpResult.error("上传失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
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
    @Transactional
    public HttpResult uploadManualEntrye(@RequestBody AppMonitorManualEntryeDto appMonitorManualEntryeDto) {
        if(StringUtils.isBlank(appMonitorManualEntryeDto.getMid()) || StringUtils.isBlank(appMonitorManualEntryeDto.getId())
        || StringUtils.isBlank(appMonitorManualEntryeDto.getDeptcode()) || StringUtils.isBlank(appMonitorManualEntryeDto.getGczxm())||
                StringUtils.isBlank(appMonitorManualEntryeDto.getTypes())){
            return HttpResult.error("上传参数异常");
        }
        appMonitorManualEntryeDto.setGczzwm("-");

        try {
            Map<String, Map<String, String>> codeMap = (Map<String, Map<String, String>>) RedisConfig.redisTstaticemplate.opsForValue().get(RedisCode.APPCODESET);


            if("2".equals(appMonitorManualEntryeDto.getTypes())){
                appMonitorExpService.monitorManualToP(appMonitorManualEntryeDto,codeMap);
                appMonitorExpService.monitorManualToN(appMonitorManualEntryeDto,codeMap);
                appMonitorExpService.monitorManualToW(appMonitorManualEntryeDto,codeMap);
            }else if("1".equals(appMonitorManualEntryeDto.getTypes())){
                appMonitorExpService.monitorManualToN(appMonitorManualEntryeDto,codeMap);
            }else if("3".equals(appMonitorManualEntryeDto.getTypes())){
                appMonitorExpService.monitorManualToC(appMonitorManualEntryeDto,codeMap);
            }else if("4".equals(appMonitorManualEntryeDto.getTypes())){
                appMonitorExpService.monitorManualToV(appMonitorManualEntryeDto,codeMap);
            }
            appMonitorManualEntryeService.save(appMonitorManualEntryeDto);
            return HttpResult.ok();
        }catch (Exception e){
            LOG.info("uploadManualEntrye上传失败：{}", e.getMessage());
            LOG.info("uploadManualEntrye上传失败：{}", JSON.toJSONString(appMonitorManualEntryeDto));
            return HttpResult.error("上传失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
        }
    }



    /**
     * 获取发现江豚信息
     * @param appMonitorDiscoveryDto
     * @return
     */
    @PostMapping("/uploadMonitorDiscovery")
    @Transactional
    public HttpResult uploadMonitorDiscovery(@RequestBody AppMonitorDiscoveryDto appMonitorDiscoveryDto) {
        if( StringUtils.isBlank(appMonitorDiscoveryDto.getMid()) || StringUtils.isBlank(appMonitorDiscoveryDto.getId())
        ||StringUtils.isBlank(appMonitorDiscoveryDto.getDeptcode()) || StringUtils.isBlank(appMonitorDiscoveryDto.getGczxm())){
            return HttpResult.error("上传参数异常");
        }
        appMonitorDiscoveryDto.setGczzwm("-");
        try {
            Map<String, Map<String, String>> codeMap = (Map<String, Map<String, String>>) RedisConfig.redisTstaticemplate.opsForValue().get(RedisCode.APPCODESET);
            appMonitorExpService.monitorDiscoveryToS(appMonitorDiscoveryDto,codeMap);
            appMonitorExpService.monitorDiscoveryToH(appMonitorDiscoveryDto,codeMap);
            appMonitorDiscoveryService.save(appMonitorDiscoveryDto);
            return HttpResult.ok();
        }catch (Exception e){
            LOG.info("uploadMonitorDiscovery上传失败：{}", e.getMessage());
            LOG.info("uploadMonitorDiscovery传失败：{}", JSON.toJSONString(appMonitorDiscoveryDto));
            return HttpResult.error("上传失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
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
            return HttpResult.error("下载失败："+e.getMessage());
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







}

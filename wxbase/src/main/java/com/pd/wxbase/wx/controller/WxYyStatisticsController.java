package com.pd.wxbase.wx.controller;

import com.pd.server.main.domain.WxYyExample;
import com.pd.server.main.domain.WxYyStatisticsExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.WxyyYyslGuoupDto;
import com.pd.server.main.service.WxYyService;
import com.pd.server.main.service.WxYyStatisticsService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.DateUtils;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/wxYyStatistics")
public class WxYyStatisticsController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WxYyStatisticsController.class);
    public static final String BUSINESS_NAME = "预约统计";

    @Resource
    private WxYyStatisticsService wxYyStatisticsService;
    @Resource
    private WxYyService wxYyService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        wxYyStatisticsService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WxYyStatisticsDto wxYyStatisticsDto) {
        // 保存校验
        ValidatorUtil.length(wxYyStatisticsDto.getDeptcode(), "部门编号", 1, 36);
        ValidatorUtil.length(wxYyStatisticsDto.getYwfl(), "业务分类", 1, 10);
        ValidatorUtil.length(wxYyStatisticsDto.getYwlx(), "业务类型", 1, 10);
        ValidatorUtil.length(wxYyStatisticsDto.getZt(), "状态", 1, 2);
        ValidatorUtil.length(wxYyStatisticsDto.getNf(), "年份", 1, 10);
        ValidatorUtil.length(wxYyStatisticsDto.getYf(), "月份", 1, 10);
        ValidatorUtil.length(wxYyStatisticsDto.getRq(), "日期", 1, 100);
        ValidatorUtil.length(wxYyStatisticsDto.getBzzd1(), "备注字段1", 1, 100);
        ValidatorUtil.length(wxYyStatisticsDto.getBzzd2(), "备注字段2", 1, 100);
        ValidatorUtil.length(wxYyStatisticsDto.getBzzd3(), "备注字段3", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        wxYyStatisticsService.save(wxYyStatisticsDto);
        responseDto.setContent(wxYyStatisticsDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        wxYyStatisticsService.delete(id);
        return responseDto;
    }

    /**
     * 车驾管业务和违法处理业务预约查询统计
     */
    @PostMapping("/getStatisticsContent")
    public  ResponseDto getStatisticsContent(@RequestBody WxYyStatisticsDto wxYyStatisticsDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());//获取部门属性
        //获得今天的日期
        String dateStr = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        if(dateStr.equals(wxYyStatisticsDto.getRq())){//统计今天
            WxYyExample example = new WxYyExample();
            WxYyExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(list)){
                ca.andDeptcodeIn(list);
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getZt())){
                ca.andZtEqualTo(wxYyStatisticsDto.getZt());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getYwfl())){
                ca.andYwflEqualTo(wxYyStatisticsDto.getYwfl());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getRq())){
                ca.andYysjEqualTo(wxYyStatisticsDto.getRq());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getYwflList())){
                ca.andYwflIn(wxYyStatisticsDto.getYwflList());
            }
            Map<String,Object> map = wxYyService.getStatisticsByToday(example);
            responseDto.setContent(map);
        }else{
            WxYyStatisticsExample example = new WxYyStatisticsExample();
            WxYyStatisticsExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(list)){
                ca.andDeptcodeIn(list);
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getNf())){
                ca.andNfEqualTo(wxYyStatisticsDto.getNf());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getYf())){
                ca.andYfEqualTo(wxYyStatisticsDto.getYf());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getRq())){
                ca.andRqEqualTo(wxYyStatisticsDto.getRq());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getZt())){
                ca.andZtEqualTo(wxYyStatisticsDto.getZt());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getYwfl())){
                ca.andYwflEqualTo(wxYyStatisticsDto.getYwfl());
            }
            if(!StringUtils.isEmpty(wxYyStatisticsDto.getYwflList())){
                ca.andYwflIn(wxYyStatisticsDto.getYwflList());
            }
            Map<String,Object> map = wxYyStatisticsService.getStatistics(example);
            responseDto.setContent(map);
        }
        return responseDto;
    }

    /**
     * 业务预约数量查询统计
     */
    @PostMapping("/getYyslCountStatistics")
    public ResponseDto getYyslCountStatistics(@RequestBody WxYyStatisticsDto wxYyStatisticsDto) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());//获取部门属性
        //1.根据年份进行统计
        //1-1.选择的年份是今年
        //查询WxYyStatisticsDto和WxYyDto(这个表主要是查询当天的数据)
        //1-2.选择的年份不是今年
        //查询WxYyStatisticsDto
        //2.根据月份进行统计
        //2-1.选择的月份包含当前月
        //查询WxYyStatisticsDto和WxYyDto(这个表主要是查询当天的数据)
        //2-2.选择的年份不包含当前月
        //查询WxYyStatisticsDto
        if(!StringUtils.isEmpty(wxYyStatisticsDto.getChooseType())&&"1".equals(wxYyStatisticsDto.getChooseType())){
            Map<String, Object> resultMap = getStatisticsByNf(list,wxYyStatisticsDto);
            responseDto.setContent(resultMap);
        }
        if(!StringUtils.isEmpty(wxYyStatisticsDto.getChooseType())&&"2".equals(wxYyStatisticsDto.getChooseType())){
            Map<String, Object> resultMap = getStatisticsByYf(list,wxYyStatisticsDto);
            responseDto.setContent(resultMap);
        }
        return responseDto;
    }

    public Map<String, Object> getStatisticsByNf(List<String> list,WxYyStatisticsDto wxYyStatisticsDto){
        String dateStr = DateUtil.getFormatDate(new Date(),"yyyy-MM");
        //获取当前的年份
        String curNfStr = dateStr.split("-")[0];
        //选择年份的预约数和到场数
        WxYyStatisticsExample wxYyStatisticsExample = new WxYyStatisticsExample();
        WxYyStatisticsExample.Criteria wxYyStatisticsCa = wxYyStatisticsExample.createCriteria();
        if(!StringUtils.isEmpty(list)){
            wxYyStatisticsCa.andDeptcodeIn(list);
        }
        wxYyStatisticsCa.andNfEqualTo(wxYyStatisticsDto.getNf());
        List<WxYyStatisticsDto> curNfList = wxYyStatisticsService.getStatisticsByDeptcode(wxYyStatisticsExample);
        //上一年的预约数和到场数
        WxYyStatisticsExample beforeExample = new WxYyStatisticsExample();
        WxYyStatisticsExample.Criteria beforeCa = beforeExample.createCriteria();
        if(!StringUtils.isEmpty(list)){
            beforeCa.andDeptcodeIn(list);
        }
        Integer beforeNf = Integer.parseInt(wxYyStatisticsDto.getNf())-1;
        beforeCa.andNfEqualTo(beforeNf.toString());
        Map<String,WxYyStatisticsDto> beforeNfMap = wxYyStatisticsService.getStatisticsByDeptcode1(beforeExample);
        List<WxYyStatisticsDto> allCurNfList = new ArrayList<>();
        if(curNfStr.equals(wxYyStatisticsDto.getNf())){
            //当天的预约数和到场数
            WxYyExample wxYyExample = new WxYyExample();
            WxYyExample.Criteria wxYyCa = wxYyExample.createCriteria();
            wxYyCa.andYysjEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
            Map<String, WxYyDto> curDayMap = wxYyService.getStatisticsByTodayAndDeptcode(wxYyExample);
            List<String> commonDeptCodeList = new ArrayList<>();
            //当deptcode相同时，将当天的预约数和到场数加到年份中
            for(WxYyStatisticsDto entity : curNfList){
                if(curDayMap.containsKey(entity.getDeptcode())){
                    entity.setAllYysl(curDayMap.get(entity.getDeptcode()).getAllYysl()+entity.getAllYysl());
                    entity.setAllDcs(curDayMap.get(entity.getDeptcode()).getAllDcs()+entity.getAllDcs());
                }
                allCurNfList.add(entity);
                commonDeptCodeList.add(entity.getDeptcode());
            }
            //年份中未包含的deptcode的到场数和预约数
            for(String key : curDayMap.keySet()){
                if(!commonDeptCodeList.contains(key)){
                    WxYyStatisticsDto entity = new WxYyStatisticsDto();
                    WxYyDto wxYyDto = curDayMap.get(key);
                    entity.setDeptcode(wxYyDto.getDeptcode());
                    entity.setAllYysl(wxYyDto.getAllYysl());
                    entity.setAllDcs(wxYyDto.getAllDcs());
                    allCurNfList.add(entity);
                }
            }
        }else{
            allCurNfList = curNfList;
        }
        List<WxyyYyslGuoupDto> resultList = new ArrayList<>();
        List<String> commonDCList = new ArrayList<>();
        //当deptcode相同时，将当年和上一年的到场数和预约数及到场同比率和预约同比率写入到WxyyYyslGuoupDto中
        for(WxYyStatisticsDto entity:allCurNfList){
            WxyyYyslGuoupDto obj = new WxyyYyslGuoupDto();
            obj.setDeptcode(entity.getDeptcode());
            if(beforeNfMap.containsKey(entity.getDeptcode())){
                obj.setCurNfYysl(entity.getAllYysl());
                obj.setBeforeNfYysl(beforeNfMap.get(entity.getDeptcode()).getAllYysl());
                obj.setCurNfDcsl(entity.getAllDcs());
                obj.setBeforeNfDcsl(beforeNfMap.get(entity.getDeptcode()).getAllDcs());
                if(!StringUtils.isEmpty(obj.getBeforeNfYysl())&&obj.getBeforeNfYysl()!=0){
                    obj.setTbzzYysl(div(obj.getCurNfYysl()-obj.getBeforeNfYysl(),obj.getBeforeNfYysl())*100+"%");
                }else{
                    obj.setTbzzYysl("0");
                }
                if(!StringUtils.isEmpty(obj.getBeforeNfDcsl())&&obj.getBeforeNfDcsl()!=0){
                    obj.setTbzzDcsl(div(obj.getCurNfDcsl()-obj.getBeforeNfDcsl(),obj.getBeforeNfDcsl())*100+"%");
                }else{
                    obj.setTbzzDcsl("0");
                }
            }else {
                obj.setCurNfYysl(entity.getAllYysl());
                obj.setBeforeNfYysl(0);
                obj.setCurNfDcsl(entity.getAllDcs());
                obj.setBeforeNfDcsl(0);
                obj.setTbzzYysl("0");
                obj.setTbzzDcsl("0");
            }
            resultList.add(obj);
            commonDCList.add(entity.getDeptcode());
        }
        //不相同的deptcode
        for(String key:beforeNfMap.keySet()){
            if(!commonDCList.contains(key)){
                WxyyYyslGuoupDto obj = new WxyyYyslGuoupDto();
                obj.setDeptcode(key);
                obj.setCurNfYysl(0);
                obj.setBeforeNfYysl(beforeNfMap.get(key).getAllYysl());
                obj.setCurNfDcsl(0);
                obj.setBeforeNfDcsl(beforeNfMap.get(key).getAllDcs());
                if(!StringUtils.isEmpty(obj.getBeforeNfYysl())&&obj.getBeforeNfYysl()!=0){
                    obj.setTbzzYysl(div(obj.getCurNfYysl()-obj.getBeforeNfYysl(),obj.getBeforeNfYysl())*100+"%");
                }else{
                    obj.setTbzzYysl("0");
                }
                if(!StringUtils.isEmpty(obj.getBeforeNfDcsl())&&obj.getBeforeNfDcsl()!=0){
                    obj.setTbzzDcsl(div(obj.getCurNfDcsl()-obj.getBeforeNfDcsl(),obj.getBeforeNfDcsl())*100+"%");
                }else{
                    obj.setTbzzDcsl("0");
                }
                resultList.add(obj);
            }
        }
        //总计
        WxyyYyslGuoupDto allObj = new WxyyYyslGuoupDto();
        Optional<Integer> curNfYysl = resultList.stream().map(WxyyYyslGuoupDto::getCurNfYysl).reduce(Integer::sum);
        Optional<Integer> curNfDcsl = resultList.stream().map(WxyyYyslGuoupDto::getCurNfDcsl).reduce(Integer::sum);
        Optional<Integer> beforeNfYysl = resultList.stream().map(WxyyYyslGuoupDto::getBeforeNfYysl).reduce(Integer::sum);
        Optional<Integer> beforeNfDcsl = resultList.stream().map(WxyyYyslGuoupDto::getBeforeNfDcsl).reduce(Integer::sum);
        allObj.setCurNfYysl(curNfYysl.isPresent()?curNfYysl.get():0);
        allObj.setCurNfDcsl(curNfDcsl.isPresent()?curNfDcsl.get():0);
        allObj.setBeforeNfYysl(beforeNfYysl.isPresent()?beforeNfYysl.get():0);
        allObj.setBeforeNfDcsl(beforeNfDcsl.isPresent()?beforeNfDcsl.get():0);
        if(!StringUtils.isEmpty(allObj.getBeforeNfYysl())&&allObj.getBeforeNfYysl()!=0){
            allObj.setTbzzYysl(div(allObj.getCurNfYysl()-allObj.getBeforeNfYysl(),allObj.getBeforeNfYysl())*100+"%");
        }else{
            allObj.setTbzzYysl("0");
        }
        if(!StringUtils.isEmpty(allObj.getBeforeNfDcsl())&&allObj.getBeforeNfDcsl()!=0){
            allObj.setTbzzDcsl(div(allObj.getCurNfDcsl()-allObj.getBeforeNfDcsl(),allObj.getBeforeNfDcsl())*100+"%");
        }else{
            allObj.setTbzzDcsl("0");
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("mx",resultList);
        resultMap.put("zj",allObj);
        return resultMap;
    }

    public Map<String, Object> getStatisticsByYf(List<String> list,WxYyStatisticsDto wxYyStatisticsDto){
        String dateStr = DateUtil.getFormatDate(new Date(),"yyyy-MM");
        //获取当前的月份
        String curYfStr = dateStr.split("-")[1];
        //选择月份的预约数和到场数
        WxYyStatisticsExample wxYyStatisticsExample = new WxYyStatisticsExample();
        WxYyStatisticsExample.Criteria wxYyStatisticsCa = wxYyStatisticsExample.createCriteria();
        if(!StringUtils.isEmpty(list)){
            wxYyStatisticsCa.andDeptcodeIn(list);
        }
        wxYyStatisticsCa.andNfEqualTo(wxYyStatisticsDto.getNf());
        wxYyStatisticsCa.andYfEqualTo(wxYyStatisticsDto.getYf());
        List<WxYyStatisticsDto> curYfList = wxYyStatisticsService.getStatisticsByDeptcode(wxYyStatisticsExample);
        //上一月的预约数和到场数
        WxYyStatisticsExample beforeExample = new WxYyStatisticsExample();
        WxYyStatisticsExample.Criteria beforeCa = beforeExample.createCriteria();
        if(!StringUtils.isEmpty(list)){
            beforeCa.andDeptcodeIn(list);
        }
        String beforeNf = wxYyStatisticsDto.getNf();
        String beforeYf = Integer.parseInt(wxYyStatisticsDto.getYf())-1+"";
        if("0".equals(beforeYf)){
            beforeNf = Integer.parseInt(beforeNf)-1+"";
            beforeYf = "12";
        }
        beforeCa.andNfEqualTo(beforeNf);
        beforeCa.andYfEqualTo(beforeYf);
        Map<String,WxYyStatisticsDto> beforeYfMap = wxYyStatisticsService.getStatisticsByDeptcode1(beforeExample);
        List<WxYyStatisticsDto> allCurYfList = new ArrayList<>();
        if(curYfStr.equals(wxYyStatisticsDto.getYf())){
            //当天的预约数和到场数
            WxYyExample wxYyExample = new WxYyExample();
            WxYyExample.Criteria wxYyCa = wxYyExample.createCriteria();
            wxYyCa.andYysjEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
            Map<String, WxYyDto> curDayMap = wxYyService.getStatisticsByTodayAndDeptcode(wxYyExample);
            List<String> commonDeptCodeList = new ArrayList<>();
            //当deptcode相同时，将当天的预约数和到场数加到年份中
            for(WxYyStatisticsDto entity : curYfList){
                if(curDayMap.containsKey(entity.getDeptcode())){
                    entity.setAllYysl(curDayMap.get(entity.getDeptcode()).getAllYysl()+entity.getAllYysl());
                    entity.setAllDcs(curDayMap.get(entity.getDeptcode()).getAllDcs()+entity.getAllDcs());
                }
                allCurYfList.add(entity);
                commonDeptCodeList.add(entity.getDeptcode());
            }
            //年份中未包含的deptcode的到场数和预约数
            for(String key : curDayMap.keySet()){
                if(!commonDeptCodeList.contains(key)){
                    WxYyStatisticsDto entity = new WxYyStatisticsDto();
                    WxYyDto wxYyDto = curDayMap.get(key);
                    entity.setDeptcode(wxYyDto.getDeptcode());
                    entity.setAllYysl(wxYyDto.getAllYysl());
                    entity.setAllDcs(wxYyDto.getAllDcs());
                    allCurYfList.add(entity);
                }
            }
        }else{
            allCurYfList = curYfList;
        }
        List<WxyyYyslGuoupDto> resultList = new ArrayList<>();
        List<String> commonDCList = new ArrayList<>();
        //当deptcode相同时，将当年和上一年的到场数和预约数及到场同比率和预约同比率写入到WxyyYyslGuoupDto中
        for(WxYyStatisticsDto entity:allCurYfList){
            WxyyYyslGuoupDto obj = new WxyyYyslGuoupDto();
            obj.setDeptcode(entity.getDeptcode());
            if(beforeYfMap.containsKey(entity.getDeptcode())){
                obj.setCurYfYysl(entity.getAllYysl());
                obj.setBeforeYfYysl(beforeYfMap.get(entity.getDeptcode()).getAllYysl());
                obj.setCurYfDcsl(entity.getAllDcs());
                obj.setBeforeYfDcsl(beforeYfMap.get(entity.getDeptcode()).getAllDcs());
                if(!StringUtils.isEmpty(obj.getBeforeYfYysl())&&obj.getBeforeYfYysl()!=0){
                    obj.setHbzzYysl(div(obj.getCurYfYysl()-obj.getBeforeYfYysl(),obj.getBeforeYfYysl())*100+"%");
                }else{
                    obj.setHbzzYysl("0");
                }
                if(!StringUtils.isEmpty(obj.getBeforeYfDcsl())&&obj.getBeforeYfDcsl()!=0){
                    obj.setHbzzDcsl(div(obj.getCurYfDcsl()-obj.getBeforeYfDcsl(),obj.getBeforeYfDcsl())*100+"%");
                }else{
                    obj.setHbzzDcsl("0");
                }
            }else {
                obj.setCurYfYysl(entity.getAllYysl());
                obj.setBeforeYfYysl(0);
                obj.setCurYfDcsl(entity.getAllDcs());
                obj.setBeforeYfDcsl(0);
                obj.setHbzzYysl("0");
                obj.setHbzzDcsl("0");
            }
            resultList.add(obj);
            commonDCList.add(entity.getDeptcode());
        }
        //不相同的deptcode
        for(String key:beforeYfMap.keySet()){
            if(!commonDCList.contains(key)){
                WxyyYyslGuoupDto obj = new WxyyYyslGuoupDto();
                obj.setDeptcode(key);
                obj.setCurYfYysl(0);
                obj.setBeforeYfYysl(beforeYfMap.get(key).getAllYysl());
                obj.setCurYfDcsl(0);
                obj.setBeforeYfDcsl(beforeYfMap.get(key).getAllDcs());
                if(!StringUtils.isEmpty(obj.getBeforeYfYysl())&&obj.getBeforeYfYysl()!=0){
                    obj.setHbzzYysl(div(obj.getCurYfYysl()-obj.getBeforeYfYysl(),obj.getBeforeYfYysl())*100+"%");
                }else{
                    obj.setHbzzYysl("0");
                }
                if(!StringUtils.isEmpty(obj.getBeforeYfDcsl())&&obj.getBeforeYfDcsl()!=0){
                    obj.setHbzzDcsl(div(obj.getCurYfDcsl()-obj.getBeforeYfDcsl(),obj.getBeforeYfDcsl())*100+"%");
                }else{
                    obj.setHbzzDcsl("0");
                }
                resultList.add(obj);
            }
        }
        //总计
        WxyyYyslGuoupDto allObj = new WxyyYyslGuoupDto();
        Optional<Integer> curYfYysl = resultList.stream().map(WxyyYyslGuoupDto::getCurYfYysl).reduce(Integer::sum);
        Optional<Integer> curYfDcsl = resultList.stream().map(WxyyYyslGuoupDto::getCurYfDcsl).reduce(Integer::sum);
        Optional<Integer> beforeYfYysl = resultList.stream().map(WxyyYyslGuoupDto::getBeforeYfYysl).reduce(Integer::sum);
        Optional<Integer> beforeYfDcsl = resultList.stream().map(WxyyYyslGuoupDto::getBeforeYfDcsl).reduce(Integer::sum);
        allObj.setCurYfYysl(curYfYysl.isPresent()?curYfYysl.get():0);
        allObj.setCurYfDcsl(curYfDcsl.isPresent()?curYfDcsl.get():0);
        allObj.setBeforeYfYysl(beforeYfYysl.isPresent()?beforeYfYysl.get():0);
        allObj.setBeforeYfDcsl(beforeYfDcsl.isPresent()?beforeYfDcsl.get():0);
        if(!StringUtils.isEmpty(allObj.getBeforeYfYysl())&&allObj.getBeforeYfYysl()!=0){
            allObj.setHbzzYysl(div(allObj.getCurYfYysl()-allObj.getBeforeYfYysl(),allObj.getBeforeYfYysl())*100+"%");
        }else{
            allObj.setHbzzYysl("0");
        }
        if(!StringUtils.isEmpty(allObj.getBeforeYfDcsl())&&allObj.getBeforeYfDcsl()!=0){
            allObj.setHbzzDcsl(div(allObj.getCurYfDcsl()-allObj.getBeforeYfDcsl(),allObj.getBeforeYfDcsl())*100+"%");
        }else{
            allObj.setHbzzDcsl("0");
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("mx",resultList);
        resultMap.put("zj",allObj);
        return  resultMap;
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后4位，以后的数字四舍五入
     * @param v1            被除数
     * @param v2            除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, 4);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1            被除数
     * @param v2            除数
     * @param scale         表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}

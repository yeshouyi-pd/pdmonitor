package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.PointerDay;
import com.pd.server.main.domain.PointerDayExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.PointerDayService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/pointerDay")
public class PointerDayController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(PointerDayController.class);
    public static final String BUSINESS_NAME = "当日水下环境指针数据";

    @Resource
    private PointerDayService pointerDayService;


    /**
     * 列表查询
     */
    @PostMapping("/listAll")
    public ResponseDto listAll(@RequestBody PointerDayDto pointerDayDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<PointerDay> pointerDayList = new ArrayList<>();
        PointerDayExample example = new PointerDayExample();
        PointerDayExample.Criteria ca = example.createCriteria();
        List<String> useSbbhs = new ArrayList<>();
        if(!StringUtils.isEmpty(pointerDayDto.getXmbh())){
            useSbbhs = userDto.getXmbhsbsns().get(pointerDayDto.getXmbh());
        }
        ca.andCjsjEqualTo(DateUtil.getFormatDate(pointerDayDto.getCjsj(),"yyyy-MM-dd"),"%Y-%m-%d");
        List<PointerDay> lists = pointerDayService.selectByExample(example);
        if(lists!=null && lists.size()>0){
            Map<String,List<PointerDay>> maps = lists.stream().collect(Collectors.groupingBy(PointerDay::getSm));
            if(!StringUtils.isEmpty(pointerDayDto.getXmbh())){
                for(String key : maps.keySet()){
                    if(useSbbhs.contains(key)){
                        List<PointerDay> tempList = maps.get(key);
                        List<PointerDay> collect = tempList.stream().sorted(Comparator.comparing(PointerDay::getCjsj).reversed()).collect(Collectors.toList());
                        pointerDayList.add(collect.get(0));
                    }
                }
            }else{
                for(String key : maps.keySet()){
                    List<PointerDay> tempList = maps.get(key);
                    List<PointerDay> collect = tempList.stream().sorted(Comparator.comparing(PointerDay::getCjsj).reversed()).collect(Collectors.toList());
                    pointerDayList.add(collect.get(0));
                }
            }
        }
        responseDto.setContent(pointerDayList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pointerDayService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody PointerDayDto pointerDayDto) {
        // 保存校验
                ValidatorUtil.length(pointerDayDto.getDecibelValue(), "分贝值", 1, 255);
                ValidatorUtil.length(pointerDayDto.getSm(), "说明", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz(), "备注", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz1(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz2(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz3(), "备注字段", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        pointerDayService.save(pointerDayDto);
        responseDto.setContent(pointerDayDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        pointerDayService.delete(id);
        return responseDto;
    }

}

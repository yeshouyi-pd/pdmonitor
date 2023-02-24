package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.PointerSecond;
import com.pd.server.main.domain.PointerSecondExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.PointerCommenDto;
import com.pd.server.main.service.PointerSecondService;
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
@RequestMapping("/admin/pointerSecond")
public class PointerSecondController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(PointerSecondController.class);
    public static final String BUSINESS_NAME = "实时水下环境指针数据";

    @Resource
    private PointerSecondService pointerSecondService;

    /**
     * 列表查询
     */
    @PostMapping("/listAll")
    public ResponseDto listAll(@RequestBody PointerSecondDto pointerSecondDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<PointerSecond> pointerSecondList = new ArrayList<>();
        PointerSecondExample example = new PointerSecondExample();
        PointerSecondExample.Criteria ca = example.createCriteria();
        List<String> useSbbhs = new ArrayList<>();
        if(!StringUtils.isEmpty(pointerSecondDto.getXmbh())){
            useSbbhs = userDto.getXmbhsbsns().get(pointerSecondDto.getXmbh());
        }
        //ca.andCjsjEqualTo(DateUtil.getFormatDate(pointerSecondDto.getCjsj(),"yyyy-MM-dd"),"%Y-%m-%d");
        ca.andCjsjGreaterThanOrEqualTo(DateUtil.getFormatDate(pointerSecondDto.getCjsj(),"yyyy-MM-dd")+" 00:00:00");
        ca.andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(pointerSecondDto.getCjsj(),"yyyy-MM-dd")+" 23:59:59");
        List<PointerSecond> lists = pointerSecondService.selectByExample(example);
        if(lists!=null && lists.size()>0){
            Map<String,List<PointerSecond>> maps = lists.stream().collect(Collectors.groupingBy(PointerSecond::getSm));
            if(!StringUtils.isEmpty(pointerSecondDto.getXmbh())){
                for(String key : maps.keySet()){
                    if(useSbbhs.contains(key)){
                        List<PointerSecond> tempList = maps.get(key);
                        List<PointerSecond> collect = tempList.stream().sorted(Comparator.comparing(PointerSecond::getCjsj).reversed()).collect(Collectors.toList());
                        pointerSecondList.add(collect.get(0));
                    }
                }
            }else{
                for(String key : maps.keySet()){
                    List<PointerSecond> tempList = maps.get(key);
                    List<PointerSecond> collect = tempList.stream().sorted(Comparator.comparing(PointerSecond::getCjsj).reversed()).collect(Collectors.toList());
                    pointerSecondList.add(collect.get(0));
                }
            }
        }
        responseDto.setContent(pointerSecondList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pointerSecondService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody PointerSecondDto pointerSecondDto) {
        // 保存校验
                ValidatorUtil.length(pointerSecondDto.getDecibelValue(), "分贝值", 1, 255);
                ValidatorUtil.length(pointerSecondDto.getSm(), "说明", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz(), "备注", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz1(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz2(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz3(), "备注字段", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        pointerSecondService.save(pointerSecondDto);
        responseDto.setContent(pointerSecondDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        pointerSecondService.delete(id);
        return responseDto;
    }

}

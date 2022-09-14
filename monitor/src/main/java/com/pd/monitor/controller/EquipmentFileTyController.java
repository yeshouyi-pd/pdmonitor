package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.EquipmentFileTy;
import com.pd.server.main.domain.EquipmentFileTyExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.EquipmentFileTyService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/equipmentFileTy")
public class EquipmentFileTyController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTyController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private EquipmentFileTyService equipmentFileTyService;

    @PostMapping("/detail/{tpmc}")
    public ResponseDto detail(@PathVariable String tpmc){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTyExample example = new EquipmentFileTyExample();
        example.createCriteria().andTpljLike("%"+tpmc+"%");
        List<EquipmentFileTy> lists = equipmentFileTyService.listAll(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/lists")
    public ResponseDto lists(@RequestBody EquipmentFileTyDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileTyExample example = new EquipmentFileTyExample();
        EquipmentFileTyExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(user.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFileTy> lists = equipmentFileTyService.lists(example);
        List<EquipmentFileTyDto> listsDto = CopyUtil.copyList(lists,EquipmentFileTyDto.class);
        PageInfo<EquipmentFileTyDto> pageInfo = new PageInfo<>(listsDto);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(listsDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTyService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileTyDto equipmentFileTyDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileTyDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileTyDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFileTyDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFileTyDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFileTyDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFileTyDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFileTyDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFileTyDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileTyDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFileTyDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFileTyDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFileTyDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFileTyDto.getSm1(), "坐标原数据", 1, 500);
                ValidatorUtil.length(equipmentFileTyDto.getSm2(), "坐标信息", 1, 255);
                ValidatorUtil.length(equipmentFileTyDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyDto.getSm6(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFileTyDto.getTs(), "sm为2或3的时候才会有数据，表示头数", 1, 10);
                ValidatorUtil.length(equipmentFileTyDto.getTxtlx(), "txt文件类型", 1, 5);
                ValidatorUtil.length(equipmentFileTyDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileTyService.save(equipmentFileTyDto);
        responseDto.setContent(equipmentFileTyDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTyService.delete(id);
        return responseDto;
    }

}

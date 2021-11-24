package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.WaterEquiplogExample;
import com.pd.server.main.domain.WaterRawfileExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WaterRawfileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterRawfileService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/waterRawfile")
public class WaterRawfileController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterRawfileController.class);
    public static final String BUSINESS_NAME = "非实时数据上传";

    @Resource
    private WaterRawfileService waterRawfileService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterRawfileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        WaterRawfileExample example = new WaterRawfileExample();
        WaterRawfileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        waterRawfileService.list(pageDto,example);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterRawfileDto waterRawfileDto) {
        // 保存校验
                ValidatorUtil.length(waterRawfileDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(waterRawfileDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(waterRawfileDto.getWjlx(), "文件类型", 1, 128);
                ValidatorUtil.length(waterRawfileDto.getWjlj(), "文件路径", 1, 512);
                ValidatorUtil.length(waterRawfileDto.getZt(), "状态0未分析1已分析2分析失败", 1, 2);
                ValidatorUtil.length(waterRawfileDto.getSm1(), "", 1, 128);
                ValidatorUtil.length(waterRawfileDto.getSm2(), "", 1, 128);
                ValidatorUtil.length(waterRawfileDto.getSm3(), "", 1, 128);
                ValidatorUtil.length(waterRawfileDto.getSm4(), "", 1, 128);
                ValidatorUtil.length(waterRawfileDto.getSm5(), "", 1, 128);

        ResponseDto responseDto = new ResponseDto();
        waterRawfileService.save(waterRawfileDto);
        responseDto.setContent(waterRawfileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterRawfileService.delete(id);
        return responseDto;
    }

}

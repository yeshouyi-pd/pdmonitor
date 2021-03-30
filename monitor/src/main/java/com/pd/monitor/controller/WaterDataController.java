package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.WaterData;
import com.pd.server.main.domain.WaterDataExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WaterDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterDataService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/waterData")
public class WaterDataController  extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterDataController.class);
    public static final String BUSINESS_NAME = "数据中心";

    @Resource
    private WaterDataService waterDataService;

    @PostMapping("/findAll/{deptcode}")
    public ResponseDto findAll(@PathVariable String deptcode) {
        ResponseDto responseDto = new ResponseDto();
        List<String> list = getUpdeptcode(deptcode);
        WaterDataExample waterDataExample = new WaterDataExample();
        WaterDataExample.Criteria ca = waterDataExample.createCriteria();
        ca.andDeptcodeIn(list);
        List<WaterData> waterDataList = waterDataService.list(waterDataExample);
        List<WaterDataDto> waterDataDtoList = CopyUtil.copyList(waterDataList, WaterDataDto.class);
        responseDto.setContent(waterDataDtoList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterDataExample waterDataExample = new WaterDataExample();
        WaterDataExample.Criteria ca = waterDataExample.createCriteria();
        ca.andDeptcodeIn(list);
        List<WaterData> waterDataList = waterDataService.list(waterDataExample);
        PageInfo<WaterData> pageInfo = new PageInfo<>(waterDataList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterDataDto> waterDataDtoList = CopyUtil.copyList(waterDataList, WaterDataDto.class);
        pageDto.setList(waterDataDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterDataDto waterDataDto) {
        // 保存校验
                ValidatorUtil.length(waterDataDto.getDeptcode(), "所属监测点", 1, 45);
                ValidatorUtil.require(waterDataDto.getCenterCode(), "数据中心代码");
                ValidatorUtil.length(waterDataDto.getCenterCode(), "数据中心代码", 1, 45);
                ValidatorUtil.length(waterDataDto.getCenterName(), "数据中心名称", 1, 45);
                ValidatorUtil.require(waterDataDto.getIp(), "数据中心IP");
                ValidatorUtil.length(waterDataDto.getIp(), "数据中心IP", 1, 45);
                ValidatorUtil.require(waterDataDto.getPort(), "数据中心端口");
                ValidatorUtil.length(waterDataDto.getSm(), "数据中心说明", 1, 400);
                ValidatorUtil.length(waterDataDto.getBz(), "数据中心 备注", 1, 400);
                ValidatorUtil.length(waterDataDto.getSm1(), "说明1", 1, 400);
                ValidatorUtil.length(waterDataDto.getSm2(), "说明2", 1, 400);
                ValidatorUtil.length(waterDataDto.getSm3(), "说明3", 1, 400);
                ValidatorUtil.length(waterDataDto.getSm4(), "说明4", 1, 45);
                ValidatorUtil.length(waterDataDto.getCreateBy(), "创建人员", 1, 128);
                ValidatorUtil.length(waterDataDto.getUpdateBy(), "更新人员", 1, 128);

        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        try{
            waterDataService.save(waterDataDto, loginUserDto);
            responseDto.setContent(waterDataDto);
        }catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterDataService.delete(id);
        return responseDto;
    }

}

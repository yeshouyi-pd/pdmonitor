package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.WaterProUser;
import com.pd.server.main.domain.WaterProUserExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WaterProUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WaterProUserService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/waterProUser")
public class WaterProUserController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterProUserController.class);
    public static final String BUSINESS_NAME = "项目人员表";

    @Resource
    private WaterProUserService waterProUserService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterProUserDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        List<String> xmbhs = waterProUserService.findXmbhByUsercode(loginUserDto.getLoginName());
        waterProUserService.list(pageDto, xmbhs);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterProUserDto waterProUserDto) {
        // 保存校验
                ValidatorUtil.length(waterProUserDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(waterProUserDto.getUsercode(), "人员账号", 1, 128);
                ValidatorUtil.length(waterProUserDto.getCjr(), "创建人", 1, 128);
                ValidatorUtil.length(waterProUserDto.getSm1(), "说明1", 1, 1024);
                ValidatorUtil.length(waterProUserDto.getSm2(), "说明2", 1, 1024);
                ValidatorUtil.length(waterProUserDto.getSm3(), "说明3", 1, 1024);
                ValidatorUtil.length(waterProUserDto.getSm4(), "说明4", 1, 1024);
                ValidatorUtil.length(waterProUserDto.getSm5(), "说明5", 1, 1024);
                ValidatorUtil.length(waterProUserDto.getIsboss(), "是否是负责人", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        waterProUserService.save(waterProUserDto);
        responseDto.setContent(waterProUserDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterProUserService.delete(id);
        return responseDto;
    }

    /**
     * 通过项目编号查询
     */
    @PostMapping("/findAllByExample")
    public ResponseDto findAllByExample(@RequestBody WaterProUserDto waterProUserDto) {
        ResponseDto responseDto = new ResponseDto();
        WaterProUserExample example = new WaterProUserExample();
        WaterProUserExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waterProUserDto.getXmbh())){
            ca.andXmbhEqualTo(waterProUserDto.getXmbh());
        }
        if(!StringUtils.isEmpty(waterProUserDto.getIsboss())){
            ca.andIsbossEqualTo(waterProUserDto.getIsboss());
        }
        if(!StringUtils.isEmpty(waterProUserDto.getUsercode())){
            ca.andUsercodeEqualTo(waterProUserDto.getUsercode());
        }
        List<WaterProUser> lists = waterProUserService.selectByExample(example);
        responseDto.setContent(lists);
        return responseDto;
    }

}

package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.WaterProEquip;
import com.pd.server.main.domain.WaterProUser;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.WaterProEquipService;
import com.pd.server.main.service.WaterProUserService;
import com.pd.server.main.service.WaterProjectService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/waterProject")
public class WaterProjectController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterProjectController.class);
    public static final String BUSINESS_NAME = "项目管理";

    @Resource
    private WaterProjectService waterProjectService;
    @Resource
    private WaterProUserService waterProUserService;
    @Resource
    private WaterProEquipService waterProEquipService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterProjectDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        waterProjectService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterProjectDto waterProjectDto) {
        // 保存校验
                ValidatorUtil.length(waterProjectDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(waterProjectDto.getXmmc(), "项目名称", 1, 512);
                ValidatorUtil.length(waterProjectDto.getFzr(), "项目负责人", 1, 128);
                ValidatorUtil.length(waterProjectDto.getDeptcode(), "所属部门", 1, 36);
                ValidatorUtil.length(waterProjectDto.getCjr(), "", 1, 128);
                ValidatorUtil.length(waterProjectDto.getXmyt(), "项目用途", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm1(), "说明1", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm2(), "说明2", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm3(), "说明3", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm4(), "说明4", 1, 1024);
                ValidatorUtil.length(waterProjectDto.getSm5(), "说明5", 1, 1024);

        ResponseDto responseDto = new ResponseDto();
        try{
            LoginUserDto loginUserDto = getRequestHeader();
            if(StringUtils.isEmpty(waterProjectDto.getId())){
                waterProjectDto.setCjr(loginUserDto.getLoginName());
                waterProjectDto.setCjsj(new Date());
            }else{
                waterProjectDto.setGxsj(new Date());
                List<WaterProUser> userList = waterProUserService.findByXmbh(waterProjectDto.getXmbh());
                for(WaterProUser waterProUser : userList){
                    waterProUserService.delete(waterProUser.getId());
                }
                List<WaterProEquip> equipList = waterProEquipService.findByXmbh(waterProjectDto.getXmbh());
                for(WaterProEquip waterProEquip : equipList){
                    waterProEquipService.delete(waterProEquip.getId());
                }
            }
            waterProjectService.save(waterProjectDto);
            //保存参与人员
            WaterProUserDto waterProUserDto = new WaterProUserDto();
            waterProUserDto.setCjr(loginUserDto.getLoginName());
            waterProUserDto.setCjsj(waterProjectDto.getCjsj());
            waterProjectDto.setGxsj(new Date());
            waterProUserDto.setXmbh(waterProjectDto.getXmbh());
            waterProUserDto.setUsercode(waterProjectDto.getFzr());
            waterProUserDto.setIsboss("1");
            waterProUserService.save(waterProUserDto);
            String[] usercode = waterProjectDto.getUserCodes().split(",");
            for(int i=0;i<usercode.length;i++){
                if(!waterProjectDto.getFzr().equals(usercode[i])){
                    WaterProUserDto entity = new WaterProUserDto();
                    entity.setCjr(loginUserDto.getLoginName());
                    entity.setCjsj(waterProjectDto.getCjsj());
                    entity.setGxsj(new Date());
                    entity.setXmbh(waterProjectDto.getXmbh());
                    entity.setUsercode(usercode[i]);
                    entity.setIsboss("0");
                    waterProUserService.save(entity);
                }
            }
            //保存参与设备
            String[] sbsncode = waterProjectDto.getSbsnCodes().split(",");
            for(int i=0;i<sbsncode.length;i++){
                WaterProEquipDto entity = new WaterProEquipDto();
                entity.setCjr(loginUserDto.getLoginName());
                entity.setCjsj(waterProjectDto.getCjsj());
                entity.setGxsj(new Date());
                entity.setXmbh(waterProjectDto.getXmbh());
                entity.setSbsn(sbsncode[i]);
                waterProEquipService.save(entity);
            }
            responseDto.setContent(waterProjectDto);
        }catch (Exception e){
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
        waterProjectService.delete(id);
        return responseDto;
    }

}

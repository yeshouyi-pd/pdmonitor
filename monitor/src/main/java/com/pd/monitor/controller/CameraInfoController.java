package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.CameraInfo;
import com.pd.server.main.domain.CameraInfoExample;
import com.pd.server.main.dto.CameraInfoDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.CameraInfoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/cameraInfo")
public class CameraInfoController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(CameraInfoController.class);
    public static final String BUSINESS_NAME = "设备相机配置表";


    @Resource
    private CameraInfoService cameraInfoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CameraInfoDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        List<String> deptList = getUpdeptcode(loginUserDto.getDeptcode());
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CameraInfoExample cameraInfoExample = new CameraInfoExample();
        CameraInfoExample.Criteria ca = cameraInfoExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbsn())){
            ca.andSbsnEqualTo(pageDto.getSbsn());
        }
        if(!StringUtils.isEmpty(pageDto.getIp())){
            ca.andIpEqualTo(pageDto.getIp());
        }
        if(!StringUtils.isEmpty(pageDto.getUsername())){
            ca.andUsernameEqualTo(pageDto.getUsername());
        }
        if(!CollectionUtils.isEmpty(deptList)){
            ca.andDeptcodeIn(deptList);
        }
        cameraInfoExample.setOrderByClause(" sm2 ");
        List<CameraInfo> cameraInfoList = cameraInfoService.list(cameraInfoExample);
        PageInfo<CameraInfo> pageInfo = new PageInfo<>(cameraInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CameraInfoDto> cameraInfoDtoList = CopyUtil.copyList(cameraInfoList, CameraInfoDto.class);
        pageDto.setList(cameraInfoDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CameraInfoDto cameraInfoDto) {
        // 保存校验
                ValidatorUtil.length(cameraInfoDto.getSbsn(), "设备sn", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getDeptcode(), "机构编号", 1, 45);
                ValidatorUtil.length(cameraInfoDto.getIp(), "摄像头IP", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getUsername(), "摄像头用户名", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getCamerapws(), "摄像头密码", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getDvrip(), "所属DVR的IP（备用地址）", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getSm1(), "", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getSm2(), "", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getSm3(), "", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getSm4(), "", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getSm5(), "", 1, 128);
                ValidatorUtil.length(cameraInfoDto.getXmbh(), "项目编号", 1, 255);
                ValidatorUtil.length(cameraInfoDto.getCjr(), "创建人", 1, 128);

        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        if (StringUtils.isEmpty(cameraInfoDto.getId())) {
            cameraInfoDto.setCjr(userDto.getLoginName());
            cameraInfoDto.setCjsj(new Date());
        } else {
            cameraInfoDto.setGxsj(new Date());
        }
        cameraInfoService.save(cameraInfoDto);
        responseDto.setContent(cameraInfoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        cameraInfoService.delete(id);
        return responseDto;
    }


    @PostMapping("/getDataCamera")
    public ResponseDto getDataCamera(@RequestBody String data) {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String deptcode = jsonObject.getString("deptcode");
        List<CameraInfo> list = cameraInfoService.findByDeptcode(deptcode);
        responseDto.setContent(list);
        return responseDto;
    }

    @PostMapping("/getCamera")
    public ResponseDto getCamera(@RequestBody String data) {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String id = jsonObject.getString("id");
        CameraInfo info = cameraInfoService.findByID(id);
        responseDto.setContent(info);
        return responseDto;
    }

}

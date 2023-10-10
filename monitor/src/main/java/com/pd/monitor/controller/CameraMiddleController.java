package com.pd.monitor.controller;

import com.pd.server.main.dto.CameraMiddleDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.CameraMiddleService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/cameraMiddle")
public class CameraMiddleController {

    private static final Logger LOG = LoggerFactory.getLogger(CameraMiddleController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private CameraMiddleService cameraMiddleService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        cameraMiddleService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CameraMiddleDto cameraMiddleDto) {
        // 保存校验
                ValidatorUtil.length(cameraMiddleDto.getSbbh(), "设备编号", 1, 20);
                ValidatorUtil.length(cameraMiddleDto.getJqsj(), "剪切时间", 1, 50);
                ValidatorUtil.length(cameraMiddleDto.getJgsj(), "间隔时间(秒)", 1, 5);
                ValidatorUtil.length(cameraMiddleDto.getIp(), "摄像头IP", 1, 20);
                ValidatorUtil.length(cameraMiddleDto.getPort(), "摄像头端口", 1, 6);
                ValidatorUtil.length(cameraMiddleDto.getUsername(), "摄像头用户名", 1, 10);
                ValidatorUtil.length(cameraMiddleDto.getCamerapws(), "摄像头密码", 1, 20);
                ValidatorUtil.length(cameraMiddleDto.getTdh(), "通道号", 1, 10);
                ValidatorUtil.length(cameraMiddleDto.getDvrip(), "所属DVR的IP（备用地址）", 1, 20);
                ValidatorUtil.length(cameraMiddleDto.getSfjq(), "是否剪切", 1, 1);
                ValidatorUtil.length(cameraMiddleDto.getBz(), "备注", 1, 20);
                ValidatorUtil.length(cameraMiddleDto.getSm(), "说明", 1, 20);

        ResponseDto responseDto = new ResponseDto();
        cameraMiddleService.save(cameraMiddleDto);
        responseDto.setContent(cameraMiddleDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        cameraMiddleService.delete(id);
        return responseDto;
    }

}

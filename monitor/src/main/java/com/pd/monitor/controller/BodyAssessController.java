package com.pd.monitor.controller;

import com.pd.server.main.domain.BodyAssess;
import com.pd.server.main.dto.BodyAssessDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.UavFlyVideoDto;
import com.pd.server.main.service.BodyAssessService;
import com.pd.server.main.service.UavFlyVideoService;
import com.pd.server.util.UuidUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/admin/bodyAssess")
public class BodyAssessController {

    private static final Logger LOG = LoggerFactory.getLogger(BodyAssessController.class);
    public static final String BUSINESS_NAME = "身体评估数据";

    @Resource
    private BodyAssessService bodyAssessService;
    @Resource
    private UavFlyVideoService uavFlyVideoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        bodyAssessService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @GetMapping("/getById/{id}")
    public ResponseDto getById(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        BodyAssess bodyAssess = bodyAssessService.getInfoById(id);
        responseDto.setContent(bodyAssess);
        return responseDto;
    }

    //岳阳无人机视频关联身体评估数据
    @PostMapping("/saveChoose")
    public ResponseDto saveChoose(@RequestBody BodyAssessDto bodyAssessDto) {
        ResponseDto responseDto = new ResponseDto();
        bodyAssessDto.setCjsj(new Date());
        bodyAssessDto.setId(UuidUtil.getShortUuid());
        bodyAssessService.saveInfo(bodyAssessDto);
        UavFlyVideoDto uavFlyVideoDto = new UavFlyVideoDto();
        uavFlyVideoDto.setId(bodyAssessDto.getUavFlyVideoId());
        uavFlyVideoDto.setSm(bodyAssessDto.getId());
        uavFlyVideoService.save(uavFlyVideoDto);
        responseDto.setContent(bodyAssessDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody BodyAssessDto bodyAssessDto) {
        // 保存校验
                ValidatorUtil.length(bodyAssessDto.getVolume(), "体积", 1, 20);
                ValidatorUtil.length(bodyAssessDto.getBai(), "BAI", 1, 20);
                ValidatorUtil.length(bodyAssessDto.getBodyLength(), "体长", 1, 20);
                ValidatorUtil.length(bodyAssessDto.getAgeGroup(), "估算年龄段", 1, 20);
                ValidatorUtil.length(bodyAssessDto.getTotalWeight(), "总体重(kg)", 1, 20);
                ValidatorUtil.length(bodyAssessDto.getTotalBmi(), "总体重BMI值", 1, 20);
                ValidatorUtil.length(bodyAssessDto.getFatThin(), "胖瘦判定", 1, 20);
                ValidatorUtil.length(bodyAssessDto.getImgUrl(), "图片", 1, 500);
                ValidatorUtil.length(bodyAssessDto.getBz(), "备注", 1, 100);
                ValidatorUtil.length(bodyAssessDto.getSm(), "说明", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        bodyAssessService.save(bodyAssessDto);
        responseDto.setContent(bodyAssessDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        bodyAssessService.delete(id);
        return responseDto;
    }

}

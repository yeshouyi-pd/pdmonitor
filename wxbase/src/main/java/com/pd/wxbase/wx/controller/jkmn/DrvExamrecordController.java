package com.pd.wxbase.wx.controller.jkmn;

import com.pd.server.main.dto.DrvExamrecordDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DrvExamrecordService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/drvExamrecord")
public class DrvExamrecordController {

    private static final Logger LOG = LoggerFactory.getLogger(DrvExamrecordController.class);
    public static final String BUSINESS_NAME = "理论考试答题记录表";

    @Resource
    private DrvExamrecordService drvExamrecordService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DrvExamrecordDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        drvExamrecordService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DrvExamrecordDto drvExamrecordDto) {
        // 保存校验
            ValidatorUtil.require(drvExamrecordDto.getKskm(), "电动车摩托车考试记录");
            ValidatorUtil.length(drvExamrecordDto.getKskm(), "电动车摩托车考试记录", 1, 10);
            ValidatorUtil.length(drvExamrecordDto.getDtlb(), "答题类别1单选2多选", 1, 45);
            ValidatorUtil.require(drvExamrecordDto.getTmbt(), "考试题目标题");
            ValidatorUtil.length(drvExamrecordDto.getTmbt(), "考试题目标题", 1, 1000);
            ValidatorUtil.require(drvExamrecordDto.getA(), "答案A");
            ValidatorUtil.length(drvExamrecordDto.getA(), "答案A", 1, 1000);
            ValidatorUtil.require(drvExamrecordDto.getB(), "答案B");
            ValidatorUtil.length(drvExamrecordDto.getB(), "答案B", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getC(), "答案C", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getD(), "答案D", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getTp1(), "图片1", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getTp2(), "图片2", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getZqda(), "准确答案", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getXzda(), "选择答案", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getWxid(), "微信openid", 1, 1000);
            ValidatorUtil.length(drvExamrecordDto.getDtsm(), "答题说明", 1, 1000);
            ValidatorUtil.require(drvExamrecordDto.getExamlogid(), "答题日志id");

        ResponseDto responseDto = new ResponseDto();
        drvExamrecordService.save(drvExamrecordDto);
        responseDto.setContent(drvExamrecordDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        drvExamrecordService.delete(id);
        return responseDto;
    }
}

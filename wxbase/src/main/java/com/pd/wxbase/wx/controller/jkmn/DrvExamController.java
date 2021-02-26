package com.pd.wxbase.wx.controller.jkmn;

import com.pd.server.main.dto.DrvExamDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DrvExamService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/drvExam")
public class DrvExamController {

    private static final Logger LOG = LoggerFactory.getLogger(DrvExamController.class);
    public static final String BUSINESS_NAME = "真题管理";

    @Resource
    private DrvExamService drvExamService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DrvExamDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        drvExamService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DrvExamDto drvExamDto) {
        // 保存校验
            ValidatorUtil.require(drvExamDto.getKskm(), "电动车摩托车网上学习");
            ValidatorUtil.length(drvExamDto.getKskm(), "电动车摩托车网上学习", 1, 10);
            ValidatorUtil.length(drvExamDto.getDtlb(), "答题类别1单选2多选", 1, 45);
            ValidatorUtil.require(drvExamDto.getTmbt(), "考试题目标题");
            ValidatorUtil.length(drvExamDto.getTmbt(), "考试题目标题", 1, 3000);
            ValidatorUtil.require(drvExamDto.getA(), "答案A");
            ValidatorUtil.length(drvExamDto.getA(), "答案A", 1, 200);
            ValidatorUtil.require(drvExamDto.getB(), "答案B");
            ValidatorUtil.length(drvExamDto.getB(), "答案B", 1, 200);
            ValidatorUtil.length(drvExamDto.getC(), "答案C", 1, 200);
            ValidatorUtil.length(drvExamDto.getD(), "答案D", 1, 200);
            ValidatorUtil.length(drvExamDto.getTp1(), "图片1", 1, 200);
            ValidatorUtil.length(drvExamDto.getTp2(), "图片2", 1, 200);
            ValidatorUtil.length(drvExamDto.getZqda(), "准确答案", 1, 1000);
            ValidatorUtil.length(drvExamDto.getDtsm(), "答题说明", 1, 1000);

        ResponseDto responseDto = new ResponseDto();
        drvExamService.save(drvExamDto);
        responseDto.setContent(drvExamDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        drvExamService.delete(id);
        return responseDto;
    }

}

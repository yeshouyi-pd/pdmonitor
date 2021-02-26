package com.pd.wxbase.wx.controller.jkmn;

import com.pd.server.main.dto.DrvExamlogDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DrvExamlogService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/drvExamlog")
public class DrvExamlogController {

    private static final Logger LOG = LoggerFactory.getLogger(DrvExamlogController.class);
    public static final String BUSINESS_NAME = "答题记录日志";

    @Resource
    private DrvExamlogService drvExamlogService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DrvExamlogDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        drvExamlogService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DrvExamlogDto drvExamlogDto) {
        // 保存校验
            ValidatorUtil.require(drvExamlogDto.getXm(), "姓名");
            ValidatorUtil.length(drvExamlogDto.getXm(), "姓名", 1, 128);
            ValidatorUtil.length(drvExamlogDto.getSfzmhm(), "身份证明号码", 1, 20);
            ValidatorUtil.length(drvExamlogDto.getSjh(), "手机号", 1, 20);
            ValidatorUtil.length(drvExamlogDto.getCfbh(), "处罚编号", 1, 30);
            ValidatorUtil.require(drvExamlogDto.getDtkm(), "答题科目");
            ValidatorUtil.length(drvExamlogDto.getDtkm(), "答题科目", 1, 45);
            ValidatorUtil.require(drvExamlogDto.getKssj(), "开始时间");
            ValidatorUtil.length(drvExamlogDto.getSfjg(), "是否及格1及格0不及格", 1, 1);
            ValidatorUtil.require(drvExamlogDto.getWxid(), "微信openid");
            ValidatorUtil.length(drvExamlogDto.getWxid(), "微信openid", 1, 45);

        ResponseDto responseDto = new ResponseDto();
        drvExamlogService.save(drvExamlogDto);
        responseDto.setContent(drvExamlogDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        drvExamlogService.delete(id);
        return responseDto;
    }
}

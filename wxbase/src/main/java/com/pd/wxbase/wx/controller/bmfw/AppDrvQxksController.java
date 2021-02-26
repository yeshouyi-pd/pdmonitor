package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.main.dto.DrvQxksDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.DrvQxksService;
import com.pd.server.util.DateUtils;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.OrderUtils;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/drvQxks")
public class AppDrvQxksController {

    private static final Logger LOG = LoggerFactory.getLogger(AppDrvQxksController.class);
    public static final String BUSINESS_NAME = "取消考试";

    @Resource
    private DrvQxksService drvQxksService;

    /**
     * 微信前段调用
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(@RequestBody DrvQxksDto drvQxksDto) throws Exception {
        DrvQxksDto info = drvQxksService.findBySfzmhmAndYykssj(drvQxksDto.getSfzmhm(), DateUtils.getStrToDateFormat(drvQxksDto.getYykssjstr(),"yyyy-MM-dd"));
        if(null != info){
            throw new WxStrException("您已经提交过该考试信息,请耐心等待审核!");
        }
        // 保存校验
        ValidatorUtil.length(drvQxksDto.getCreateBy(), "创建人", 1, 50);
        ValidatorUtil.length(drvQxksDto.getUpdateBy(), "更新人", 1, 50);
        ValidatorUtil.length(drvQxksDto.getSysOrgCode(), "所属部门", 1, 64);
        ValidatorUtil.length(drvQxksDto.getXm(), "姓名", 1, 128);
        ValidatorUtil.length(drvQxksDto.getZjlx(), "证件类型", 1, 32);
        ValidatorUtil.length(drvQxksDto.getSfzmhm(), "身份证明号码", 1, 20);
        ValidatorUtil.length(drvQxksDto.getSjhm(), "手机号码", 1, 20);
        ValidatorUtil.length(drvQxksDto.getQxkm(), "取消考试科目", 1, 32);
        ValidatorUtil.length(drvQxksDto.getSqsm(), "申请说明", 1, 2000);
        ValidatorUtil.length(drvQxksDto.getShr(), "审核人", 1, 32);
        ValidatorUtil.length(drvQxksDto.getShsm(), "审核说明", 1, 32);
        ValidatorUtil.length(drvQxksDto.getBz(), "备注", 1, 2000);
        ValidatorUtil.length(drvQxksDto.getOpenid(), "微信用户id", 1, 32);
        ValidatorUtil.length(drvQxksDto.getLsh(), "流水号", 1, 32);
        ValidatorUtil.length(drvQxksDto.getZt(), "状态", 1, 2);

        ResponseDto responseDto = new ResponseDto();
        drvQxksDto.setYykssj(DateUtils.getStrToDateFormat(drvQxksDto.getYykssjstr(),"yyyy-MM-dd"));
        drvQxksDto.setSfzmhm(DesEncrypt.DesJiaMi(drvQxksDto.getSfzmhm()));
        drvQxksDto.setSjhm(DesEncrypt.DesJiaMi(drvQxksDto.getSjhm()));
        drvQxksDto.setCreateBy(drvQxksDto.getXm());
        drvQxksDto.setCreateTime(new Date());
        drvQxksDto.setLsh(OrderUtils.getOrderNo());
        drvQxksDto.setZt("1");
        drvQxksService.save(drvQxksDto);
        responseDto.setContent(drvQxksDto);
        return responseDto;
    }

    /**
     * 微信取消考试结果页面查询
     * @param drvQxksDto
     * @return
     */
    @PostMapping("/selecetDrvQxks")
    public ResponseDto selecetDrvQxks(@RequestBody DrvQxksDto drvQxksDto) throws Exception {
        DrvQxksDto info = drvQxksService.findBySfzmhmAndYykssj(DesEncrypt.DesJiaMi(drvQxksDto.getSfzmhm()), DateUtils.getStrToDateFormat(drvQxksDto.getYykssjstr(),"yyyy-MM-dd"));
        if(null == info){
            throw new WxStrException("提交取消考试信息失败");
        }
        ResponseDto responseDto = new ResponseDto();
        info.setYykssjstr(drvQxksDto.getYykssjstr());
        responseDto.setContent(info);
        return responseDto;
    }




}

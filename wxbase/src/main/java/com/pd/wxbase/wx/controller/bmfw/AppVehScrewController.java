package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehScrewDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.VehScrewService;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.OrderUtils;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/vehScrew")
public class AppVehScrewController {

    private static final Logger LOG = LoggerFactory.getLogger(AppVehScrewController.class);
    public static final String BUSINESS_NAME = "车牌固封螺丝申请";

    @Resource
    private VehScrewService vehScrewService;

    /**
     * 微信前端提交保存
     * @return
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(@RequestBody VehScrewDto vehScrewDto) throws Exception {
        // 保存校验
        ValidatorUtil.length(vehScrewDto.getCreateBy(), "申请人", 1, 50);
        ValidatorUtil.length(vehScrewDto.getUpdateBy(), "更新人", 1, 50);
        ValidatorUtil.length(vehScrewDto.getSysOrgCode(), "申领网点", 1, 64);
        ValidatorUtil.length(vehScrewDto.getSfzmhm(), "身份证明号码", 1, 32);
        ValidatorUtil.length(vehScrewDto.getHphm(), "号牌号码", 1, 32);
        ValidatorUtil.length(vehScrewDto.getSqyy(), "申请原因", 1, 32);
        ValidatorUtil.length(vehScrewDto.getSlsl(), "申领数量", 1, 32);
        ValidatorUtil.length(vehScrewDto.getSjr(), "收件人", 1, 32);
        ValidatorUtil.length(vehScrewDto.getLxdh(), "联系电话", 1, 32);
        ValidatorUtil.length(vehScrewDto.getSf(), "省份", 1, 128);
        ValidatorUtil.length(vehScrewDto.getCs(), "城市", 1, 128);
        ValidatorUtil.length(vehScrewDto.getQx(), "区县", 1, 256);
        ValidatorUtil.length(vehScrewDto.getJddz(), "街道地址", 1, 256);
        ValidatorUtil.length(vehScrewDto.getZt(), "状态", 1, 2);
        ValidatorUtil.length(vehScrewDto.getShr(), "审核人", 1, 128);
        ValidatorUtil.length(vehScrewDto.getBz(), "备注", 1, 4000);
        ValidatorUtil.length(vehScrewDto.getOpenid(), "微信用户id", 1, 32);
        ValidatorUtil.length(vehScrewDto.getShyy(), "审核原因说明", 1, 200);
        ValidatorUtil.length(vehScrewDto.getYwmc(), "业务名称", 1, 32);
        ValidatorUtil.length(vehScrewDto.getLsh(), "流水号", 1, 32);
        ValidatorUtil.length(vehScrewDto.getSfbr(), "是否本人车辆1是0否", 1, 1);
        ValidatorUtil.length(vehScrewDto.getSllb(), "申领类别1线上申领2线下申领", 1, 1);
        VehScrewDto info = vehScrewService.findBySfzmhmAndSllb(DesEncrypt.DesJiaMi(vehScrewDto.getSfzmhm()),vehScrewDto.getSllb());
        if("1".equals(vehScrewDto.getSllb())){
            vehScrewDto.setSfbr("1");
            if(null != info){
                throw new WxStrException("你已经提交过螺丝申领信息,请勿重复提交!");
            }
            int i = vehScrewService.sumCount(DesEncrypt.DesJiaMi(vehScrewDto.getSfzmhm()));
            if(i >= 2){
                throw new WxStrException("一年只能申请两次车牌螺丝,您已经申请过两次！");
            }
            int j = vehScrewService.sumCountday(DesEncrypt.DesJiaMi(vehScrewDto.getSfzmhm()));
            if(i >= 3){
                throw new WxStrException("总共只能申请三次车牌螺丝,您已经申请过三次！");
            }
        }
        ResponseDto responseDto = new ResponseDto();
        if(null != vehScrewDto.getDq()){
            String[] split = vehScrewDto.getDq().split("/");
            if(split.length == 3){
                vehScrewDto.setSf(split[0]);
                vehScrewDto.setCs(split[1]);
                vehScrewDto.setQx(split[2]);
            }
        }
        vehScrewDto.setCreateTime(new Date());
        vehScrewDto.setZt("1");
        vehScrewDto.setLsh(OrderUtils.getOrderNo());
        vehScrewDto.setSfzmhm(DesEncrypt.DesJiaMi(vehScrewDto.getSfzmhm()));
        if(!StringUtils.isEmpty(vehScrewDto.getLxdh())){
            vehScrewDto.setLxdh(DesEncrypt.DesJiaMi(vehScrewDto.getLxdh()));
        }
        vehScrewService.save(vehScrewDto);
        responseDto.setContent(vehScrewDto);
        return responseDto;
    }

    /**
     * 微信前端查詢使用
     * @param vehScrewDto
     * @return
     */
    @PostMapping("/selectVehScrew")
    public ResponseDto selectVehScrew(@RequestBody VehScrewDto vehScrewDto){
        VehScrewDto info = vehScrewService.findBySfzmhm(vehScrewDto.getSfzmhm());
        if(null == info){
            throw new WxStrException("提交螺丝申领信息失败,请稍后重试!");
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(info);
        return responseDto;
    }

}

package com.pd.wxbase.wx.controller;

import com.pd.server.main.dto.WebchatPostinfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatPostinfoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/webchatPostinfo")
public class WebchatPostinfoController {

    private static final Logger LOG = LoggerFactory.getLogger(WebchatPostinfoController.class);
    public static final String BUSINESS_NAME = "邮政快递信息";

    @Resource
    private WebchatPostinfoService webchatPostinfoService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        webchatPostinfoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WebchatPostinfoDto webchatPostinfoDto) {
// 保存校验
        ValidatorUtil.require(webchatPostinfoDto.getLoginName(), "接口用户名");
        ValidatorUtil.length(webchatPostinfoDto.getLoginName(), "接口用户名", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getLoginPwd(), "接口用户密码");
        ValidatorUtil.length(webchatPostinfoDto.getLoginPwd(), "接口用户密码", 1, 128);
        ValidatorUtil.length(webchatPostinfoDto.getSecretKey(), "密钥", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getDistributorOrderid(), "合作方唯一识别号");
        ValidatorUtil.length(webchatPostinfoDto.getDistributorOrderid(), "合作方唯一识别号", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getSenderProvinceCode(), "寄件人省份编号");
        ValidatorUtil.length(webchatPostinfoDto.getSenderProvinceCode(), "寄件人省份编号", 1, 20);
        ValidatorUtil.length(webchatPostinfoDto.getSenderCityCode(), "", 1, 20);
        ValidatorUtil.require(webchatPostinfoDto.getSenderCountyCode(), "寄件人区域编号");
        ValidatorUtil.length(webchatPostinfoDto.getSenderCountyCode(), "寄件人区域编号", 1, 20);
        ValidatorUtil.length(webchatPostinfoDto.getSenderCompany(), "寄件公司名称", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getSenderName(), "寄件人姓名");
        ValidatorUtil.length(webchatPostinfoDto.getSenderName(), "寄件人姓名", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getSenderPhone(), "寄件人手机号码");
        ValidatorUtil.length(webchatPostinfoDto.getSenderPhone(), "寄件人手机号码", 1, 20);
        ValidatorUtil.require(webchatPostinfoDto.getSenderAddress(), "寄件人地址");
        ValidatorUtil.length(webchatPostinfoDto.getSenderAddress(), "寄件人地址", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getProtectPrice(), "保价金额，单位是分，如未保价则填写0");
        ValidatorUtil.require(webchatPostinfoDto.getInsuranceAmount(), "保费，单位是分，如未保价则填写0");
        ValidatorUtil.require(webchatPostinfoDto.getDeliverMoney(), "运费，单位是分");
        ValidatorUtil.require(webchatPostinfoDto.getGoods(), "物品名称");
        ValidatorUtil.length(webchatPostinfoDto.getGoods(), "物品名称", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getGoodsWeight(), "物品重量（单位   克），重量不确定的，就传0");
        ValidatorUtil.length(webchatPostinfoDto.getDemo(), "备注", 1, 128);
        ValidatorUtil.require(webchatPostinfoDto.getEmsType(), "面单类型（0：快递包裹；1：标准快递）");
        ValidatorUtil.length(webchatPostinfoDto.getMailNo(), "面单号码", 1, 45);
        ValidatorUtil.length(webchatPostinfoDto.getOther(), "传递Json格式并与邮政协商好的其他信息", 1, 128);
        ValidatorUtil.length(webchatPostinfoDto.getCreateBy(), "创建人", 1, 128);
        ValidatorUtil.length(webchatPostinfoDto.getUpdateBy(), "更新人", 1, 128);
        ValidatorUtil.length(webchatPostinfoDto.getSysOrgCode(), "操作部门", 1, 45);

        ResponseDto responseDto = new ResponseDto();
        webchatPostinfoService.save(webchatPostinfoDto);
        responseDto.setContent(webchatPostinfoDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        webchatPostinfoService.delete(id);
        return responseDto;
}
}

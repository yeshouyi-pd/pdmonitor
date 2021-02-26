package com.pd.wxbase.wx.controller.ssp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WebchatReward;
import com.pd.server.main.domain.WebchatRewardExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatRewardDto;
import com.pd.server.main.service.WebchatRewardService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/webchatReward")
public class WebchatRewardController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(WebchatRewardController.class);
public static final String BUSINESS_NAME = "";

@Resource
private WebchatRewardService webchatRewardService;


/**
* 列表查询
*/

@PostMapping("/list")
public ResponseDto list(@RequestBody WebchatRewardDto pageDto) {
ResponseDto responseDto = new ResponseDto();
    try {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WebchatRewardExample webchatIllegalExample = new WebchatRewardExample();
        WebchatRewardExample.Criteria ca = webchatIllegalExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getZt())){
            ca.andZtEqualTo(pageDto.getZt());
        }
        if (!StringUtils.isEmpty(pageDto.getFfzt())){
            ca.andFfztEqualTo(pageDto.getFfzt());
        }
        if (!StringUtils.isEmpty(pageDto.getCphm())){
            ca.andCphmEqualTo(pageDto.getCphm());
        }
        if (!StringUtils.isEmpty(pageDto.getCplx())){
            ca.andCplxEqualTo(pageDto.getCplx());
        }
        if (!StringUtils.isEmpty(pageDto.getSfzmhm())){
            ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getSfzmhm()));
        }
        if (!StringUtils.isEmpty(pageDto.getSjh())){
            ca.andSjhEqualTo(DesEncrypt.DesJiaMi(pageDto.getSjh()));
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        List<WebchatReward> webchatIllegalList = webchatRewardService.list(webchatIllegalExample);
        PageInfo<WebchatReward> pageInfo = new PageInfo<>(webchatIllegalList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WebchatRewardDto> webchatIllegalDtoList = CopyUtil.copyList(webchatIllegalList, WebchatRewardDto.class);
        pageDto.setList(webchatIllegalDtoList);
        responseDto.setContent(pageDto);
    }catch (Exception e){
        e.printStackTrace();
    }
    return responseDto;
}


/**
* 保存，id有值时更新，无值时新增
*/

@PostMapping("/save")
public ResponseDto save(WebchatRewardDto webchatRewardDto) throws Exception {
// 保存校验
            ValidatorUtil.length(webchatRewardDto.getCreateBy(), "创建人", 1, 30);
            ValidatorUtil.length(webchatRewardDto.getUpdateBy(), "更新人", 1, 30);
            ValidatorUtil.length(webchatRewardDto.getSysOrgCode(), "所属部门", 1, 50);
            ValidatorUtil.length(webchatRewardDto.getWfxwxx(), "违法行为详细", 1, 200);
            ValidatorUtil.length(webchatRewardDto.getSfzmhm(), "身份证明号码", 1, 20);
            ValidatorUtil.length(webchatRewardDto.getSjh(), "手机号", 1, 11);
            ValidatorUtil.length(webchatRewardDto.getShr(), "审核人", 1, 30);
            ValidatorUtil.length(webchatRewardDto.getShyy(), "审核原因", 1, 30);
            ValidatorUtil.length(webchatRewardDto.getShsm(), "审核说明", 1, 500);
            ValidatorUtil.length(webchatRewardDto.getZt(), "状态", 1, 1);
            ValidatorUtil.length(webchatRewardDto.getOpenid(), "用户openid", 1, 40);
            ValidatorUtil.length(webchatRewardDto.getLsh(), "流水号", 1, 40);
            ValidatorUtil.length(webchatRewardDto.getFfzt(), "奖励发放状态", 1, 1);
            ValidatorUtil.length(webchatRewardDto.getCplx(), "车牌类型", 1, 20);
            ValidatorUtil.length(webchatRewardDto.getCphm(), "车牌号码", 1, 20);
            ValidatorUtil.length(webchatRewardDto.getFfr(), "发放人", 1, 20);

ResponseDto responseDto = new ResponseDto();
webchatRewardService.save(webchatRewardDto,getRequestHeader());
responseDto.setContent(webchatRewardDto);
return responseDto;
}

    /**
     * 列表查询
     */

    @PostMapping("/list2")
    public ResponseDto list2(@RequestBody WebchatRewardDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            WebchatRewardExample webchatIllegalExample = new WebchatRewardExample();
            WebchatRewardExample.Criteria ca = webchatIllegalExample.createCriteria();
            if (!StringUtils.isEmpty(pageDto.getZt())){
                ca.andZtEqualTo(pageDto.getZt());
            }
            if (!StringUtils.isEmpty(pageDto.getFfzt())){
                ca.andFfztEqualTo(pageDto.getFfzt());
            }
            if (!StringUtils.isEmpty(pageDto.getCphm())){
                ca.andCphmEqualTo(pageDto.getCphm());
            }
            if (!StringUtils.isEmpty(pageDto.getCplx())){
                ca.andCplxEqualTo(pageDto.getCplx());
            }
            if (!StringUtils.isEmpty(pageDto.getSfzmhm())){
                ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getSfzmhm()));
            }
            if (!StringUtils.isEmpty(pageDto.getSjh())){
                ca.andSjhEqualTo(DesEncrypt.DesJiaMi(pageDto.getSjh()));
            }
            if (!StringUtils.isEmpty(pageDto.getStime())){
                ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
            }
            if (!StringUtils.isEmpty(pageDto.getEtime())){
                ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
            }
            List<WebchatReward> webchatIllegalList = webchatRewardService.list(webchatIllegalExample);
            PageInfo<WebchatReward> pageInfo = new PageInfo<>(webchatIllegalList);
            pageDto.setTotal(pageInfo.getTotal());
            List<WebchatRewardDto> webchatIllegalDtoList = CopyUtil.copyList(webchatIllegalList, WebchatRewardDto.class);
            pageDto.setList(webchatIllegalDtoList);
            responseDto.setContent(pageDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseDto;
    }

/**
* 删除
*/

@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
webchatRewardService.delete(id);
return responseDto;
}


    /**
     * 奖励金支付
     */

    @PostMapping("/wXPay")
    public ResponseDto wxPay(@RequestBody WebchatRewardDto webchatRewardDto) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        webchatRewardService.updateById(webchatRewardDto.getId(),getRequestHeader());
        responseDto.setContent(webchatRewardDto);
        return responseDto;
    }

}


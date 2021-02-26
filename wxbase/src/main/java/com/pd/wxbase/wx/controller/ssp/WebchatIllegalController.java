package com.pd.wxbase.wx.controller.ssp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatIllegalDto;
import com.pd.server.main.dto.WebchatIllegalSaveDto;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.main.service.WebchatIllegalSaveService;
import com.pd.server.main.service.WebchatIllegalService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/webchatIllegal")
public class WebchatIllegalController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(WebchatIllegalController.class);
public static final String BUSINESS_NAME = "随手拍";

@Resource
private WebchatIllegalService webchatIllegalService;

@Resource
private WebchatFileinfoService webchatFileinfoService;


@Resource
private WebchatIllegalSaveService webchatIllegalSaveService;
/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody WebchatIllegalDto pageDto) {
    ResponseDto responseDto = new ResponseDto();
    try {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WebchatIllegalExample webchatIllegalExample = new WebchatIllegalExample();
        WebchatIllegalExample.Criteria ca = webchatIllegalExample.createCriteria();
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
        if (!StringUtils.isEmpty(pageDto.getWflb())){
            ca.andWflbEqualTo(pageDto.getWflb());
        }
        if (!StringUtils.isEmpty(pageDto.getZt())){
            ca.andZtEqualTo(pageDto.getZt());
        }
        if (!StringUtils.isEmpty(pageDto.getJlff())){
            ca.andJlffEqualTo(pageDto.getJlff());
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andWfsjGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andWfsjLessThanOrEqualTo(pageDto.getEtime());
        }

        webchatIllegalExample.setOrderByClause(" create_time desc");

        List<WebchatIllegal> webchatIllegalList = webchatIllegalService.list(webchatIllegalExample);
        PageInfo<WebchatIllegal> pageInfo = new PageInfo<>(webchatIllegalList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WebchatIllegalDto> webchatIllegalDtoList = CopyUtil.copyList(webchatIllegalList, WebchatIllegalDto.class);
        pageDto.setList(webchatIllegalDtoList);
        responseDto.setContent(pageDto);
    }catch (Exception e){
        e.printStackTrace();
    }
    return responseDto;
}

    /**
     * 列表查询
     */
    @PostMapping("/list2")
    public ResponseDto list2(@RequestBody WebchatIllegalSaveDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            WebchatIllegalSaveExample webchatIllegalExample = new WebchatIllegalSaveExample();
            WebchatIllegalSaveExample.Criteria ca = webchatIllegalExample.createCriteria();
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
            if (!StringUtils.isEmpty(pageDto.getWflb())){
                ca.andWflbEqualTo(pageDto.getWflb());
            }
            if (!StringUtils.isEmpty(pageDto.getZt())){
                ca.andZtEqualTo(pageDto.getZt());
            }
            if (!StringUtils.isEmpty(pageDto.getJlff())){
                ca.andJlffEqualTo(pageDto.getJlff());
            }
            if (!StringUtils.isEmpty(pageDto.getStime())){
                ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
            }
            if (!StringUtils.isEmpty(pageDto.getEtime())){
                ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
            }
            if (!StringUtils.isEmpty(pageDto.getIsChecked())){
                ca.andIsCheckedEqualTo(pageDto.getIsChecked());
            }

            webchatIllegalExample.setOrderByClause(" create_time desc");

            List<WebchatIllegalSave> webchatIllegalList = webchatIllegalSaveService.list(webchatIllegalExample);
            PageInfo<WebchatIllegalSave> pageInfo = new PageInfo<>(webchatIllegalList);
            pageDto.setTotal(pageInfo.getTotal());
            List<WebchatIllegalDto> webchatIllegalDtoList = CopyUtil.copyList(webchatIllegalList, WebchatIllegalDto.class);
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
public ResponseDto save(@RequestBody WebchatIllegalDto webchatIllegalDto) throws Exception {
// 保存校验
            ValidatorUtil.length(webchatIllegalDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(webchatIllegalDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(webchatIllegalDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(webchatIllegalDto.getWfxwms(), "违法行为描述", 1, 2000);
            ValidatorUtil.length(webchatIllegalDto.getWflb(), "违法类别", 1, 32);
            ValidatorUtil.length(webchatIllegalDto.getWfxw(), "违法行为", 1, 256);
            ValidatorUtil.length(webchatIllegalDto.getCzzp(), "是否车载照片", 1, 1);
            ValidatorUtil.length(webchatIllegalDto.getWfld(), "违法路段", 1, 256);
            ValidatorUtil.length(webchatIllegalDto.getWfgps(), "坐标信息", 1, 128);
            ValidatorUtil.length(webchatIllegalDto.getWfldxxdz(), "详细地址", 1, 256);
            ValidatorUtil.length(webchatIllegalDto.getCplx(), "车牌类型", 1, 10);
            ValidatorUtil.length(webchatIllegalDto.getCphm(), "车牌号码", 1, 10);
            ValidatorUtil.length(webchatIllegalDto.getJbr(), "举报人", 1, 128);
            ValidatorUtil.length(webchatIllegalDto.getSfzmhm(), "身份证明号码", 1, 20);
            ValidatorUtil.length(webchatIllegalDto.getSjh(), "手机号", 1, 20);
            ValidatorUtil.length(webchatIllegalDto.getBz(), "备注", 1, 4000);
            ValidatorUtil.length(webchatIllegalDto.getShr(), "审核人", 1, 128);
            ValidatorUtil.length(webchatIllegalDto.getShyy(), "审核原因", 1, 200);
            ValidatorUtil.length(webchatIllegalDto.getShsm(), "审核说明", 1, 4000);
            ValidatorUtil.length(webchatIllegalDto.getZt(), "状态", 1, 2);
            ValidatorUtil.length(webchatIllegalDto.getOpenid(), "微信用户id", 1, 32);
            ValidatorUtil.length(webchatIllegalDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.length(webchatIllegalDto.getJlff(), "奖励发放0未发放1已发放", 1, 1);
            ValidatorUtil.length(webchatIllegalDto.getWfxwxx(), "违法行为详细", 1, 20);

ResponseDto responseDto = new ResponseDto();
    LoginUserDto loginUserDto = getRequestHeader();
    webchatIllegalService.save(webchatIllegalDto,loginUserDto);
responseDto.setContent(webchatIllegalDto);
return responseDto;
}
    /**
     * 获取需要拍照的图片
     * @param webchatIllegalDto
     * @return
     */
    @PostMapping("/getpicById")
    public ResponseDto getpicById(@RequestBody WebchatIllegalDto webchatIllegalDto){
        String filetype = "pic";
        ResponseDto responseDto = new ResponseDto();
        if(null != webchatIllegalDto){
            if(!StringUtils.isEmpty(webchatIllegalDto.getLsh())){
                List<WebchatFileinfo> piclist =webchatFileinfoService.getFileinfoByLsh(webchatIllegalDto.getLsh(),filetype);
                if(!CollectionUtils.isEmpty(piclist)){
                    responseDto.setContent(piclist);
                }
            }
        }
        return responseDto;
    }

    /**
     * 获取需要拍照的视频
     * @param webchatIllegalDto
     * @return
     */
    @PostMapping("/getVideoById")
    public ResponseDto getVideoById(@RequestBody WebchatIllegalDto webchatIllegalDto){
        String filetype = "video";
        ResponseDto responseDto = new ResponseDto();
        if(null != webchatIllegalDto){
            if(!StringUtils.isEmpty(webchatIllegalDto.getLsh())){
                List<WebchatFileinfo> piclist =webchatFileinfoService.getFileinfoByLsh(webchatIllegalDto.getLsh(), filetype);
                if(!CollectionUtils.isEmpty(piclist)){
                    responseDto.setContent(piclist);
                }
            }
        }
        return responseDto;
    }
/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
webchatIllegalService.delete(id);
return responseDto;
}

    /**
     * 获取需要奖励的奖励金额
     * @param webchatIllegalDto
     * @return
     */
    @PostMapping("/getJljeByCode")
    public ResponseDto getJljeByCode(@RequestBody WebchatIllegalDto webchatIllegalDto){
        ResponseDto responseDto = new ResponseDto();
        if(null != webchatIllegalDto){
            if(!StringUtils.isEmpty(webchatIllegalDto.getId())){
                WebchatReportIllcode webchatIllegal1 =webchatIllegalService.getJljeByCode(webchatIllegalDto.getWfxwxx());
                if(null!=webchatIllegal1){
                    responseDto.setContent(webchatIllegal1);
                }
            }
        }
        return responseDto;
    }
}

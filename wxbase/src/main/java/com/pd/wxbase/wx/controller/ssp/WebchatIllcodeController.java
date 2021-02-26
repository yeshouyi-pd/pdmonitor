package com.pd.wxbase.wx.controller.ssp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WebchatIllcode;
import com.pd.server.main.domain.WebchatIllcodeExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WebchatIllcodeDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatIllcodeService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/webchatIllcode")
public class WebchatIllcodeController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WebchatIllcodeController.class);
    public static final String BUSINESS_NAME = "违法代码";

    @Resource
    private WebchatIllcodeService webchatIllcodeService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WebchatIllcodeDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            WebchatIllcodeExample webchatIllcodeExample = new WebchatIllcodeExample();
            WebchatIllcodeExample.Criteria ca = webchatIllcodeExample.createCriteria();
            if (!StringUtils.isEmpty(pageDto.getWfdm())){
                ca.andWfdmEqualTo(pageDto.getWfdm());
            }
            if (!StringUtils.isEmpty(pageDto.getWfxw())){
                ca.andWfxwEqualTo(pageDto.getWfxw());
            }
            if (!StringUtils.isEmpty(pageDto.getLb())){
                ca.andLbEqualTo(pageDto.getLb());
            }
            if (!StringUtils.isEmpty(pageDto.getZt())){
                ca.andZtEqualTo(pageDto.getZt());
            }
            webchatIllcodeExample.setOrderByClause(" fbsj desc");
            List<WebchatIllcode> webchatIllcodeList = webchatIllcodeService.list(webchatIllcodeExample);
            PageInfo<WebchatIllcode> pageInfo = new PageInfo<>(webchatIllcodeList);
            pageDto.setTotal(pageInfo.getTotal());
            List<WebchatIllcodeDto> webchatIllcodeDtoList = CopyUtil.copyList(webchatIllcodeList, WebchatIllcodeDto.class);
            pageDto.setList(webchatIllcodeDtoList);
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
    public ResponseDto save(@RequestBody WebchatIllcodeDto webchatIllcodeDto) {
        // 保存校验
            ValidatorUtil.require(webchatIllcodeDto.getWfdm(), "违法代码");
            ValidatorUtil.length(webchatIllcodeDto.getWfdm(), "违法代码", 1, 15);
            ValidatorUtil.require(webchatIllcodeDto.getWfxw(), "违法行为");
            ValidatorUtil.length(webchatIllcodeDto.getWfxw(), "违法行为", 1, 4000);
            ValidatorUtil.length(webchatIllcodeDto.getWftl(), "违法条例", 1, 4000);
            ValidatorUtil.length(webchatIllcodeDto.getCfyj(), "处罚依据", 1, 4000);
            ValidatorUtil.length(webchatIllcodeDto.getJf(), "记分", 1, 45);
            ValidatorUtil.length(webchatIllcodeDto.getQtcf(), "其他处罚", 1, 400);
            ValidatorUtil.length(webchatIllcodeDto.getCs(), "措施", 1, 400);
            ValidatorUtil.length(webchatIllcodeDto.getBz(), "备注", 1, 400);
            ValidatorUtil.length(webchatIllcodeDto.getDeptcode(), "发布机构", 1, 45);
            ValidatorUtil.length(webchatIllcodeDto.getUsercode(), "发布用户", 1, 45);
            ValidatorUtil.length(webchatIllcodeDto.getZt(), "状态", 1, 45);
            ValidatorUtil.length(webchatIllcodeDto.getLb(), "违法代码类别", 1, 45);

        ResponseDto responseDto = new ResponseDto();
        WebchatIllcodeExample webchatIllcodeExample = new WebchatIllcodeExample();
        WebchatIllcodeExample.Criteria ca = webchatIllcodeExample.createCriteria();
        if (!StringUtils.isEmpty(webchatIllcodeDto.getWfdm())){
            ca.andWfdmEqualTo(webchatIllcodeDto.getWfdm());
        }
        List<WebchatIllcode> webchatIllcodeList = webchatIllcodeService.list(webchatIllcodeExample);
        if(!((StringUtils.isEmpty(webchatIllcodeDto.getId())&&webchatIllcodeList.isEmpty())||
                (!StringUtils.isEmpty(webchatIllcodeDto.getId())&&(webchatIllcodeList.isEmpty()||(!webchatIllcodeList.isEmpty()&&webchatIllcodeList.size()==1&&webchatIllcodeList.get(0).getId().equals(webchatIllcodeDto.getId())))))){
            responseDto.setSuccess(false);
            responseDto.setMessage("违法代码重复");
            return responseDto;
        }
        LoginUserDto loginUserDto   = getRequestHeader();
        webchatIllcodeDto.setUsercode(loginUserDto.getLoginName());
        webchatIllcodeDto.setDeptcode(loginUserDto.getDeptcode());
        webchatIllcodeService.save(webchatIllcodeDto);
        responseDto.setContent(webchatIllcodeDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        webchatIllcodeService.delete(id);
        return responseDto;
    }

    @PostMapping("/deleteBatch")
    public ResponseDto deleteBatch(@RequestBody WebchatIllcodeDto webchatIllcodeDto) {
        ResponseDto responseDto = new ResponseDto();
        if(!StringUtils.isEmpty(webchatIllcodeDto.getIdList())&&!webchatIllcodeDto.getIdList().isEmpty()){
            for(String id : webchatIllcodeDto.getIdList()){
                webchatIllcodeService.delete(id);
            }
        }
        return responseDto;
    }
}

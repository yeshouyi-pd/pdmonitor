package com.pd.wxbase.wx.controller.ssp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.RewardCheck;
import com.pd.server.main.domain.RewardCheckExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.RewardCheckDto;
import com.pd.server.main.dto.WebchatRewardDto;
import com.pd.server.main.service.RewardCheckService;
import com.pd.server.main.service.WebchatRewardService;
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
@RequestMapping("/admin/rewardCheck")
public class RewardCheckController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(RewardCheckController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private RewardCheckService rewardCheckService;

    @Resource
    private WebchatRewardService webchatRewardService;


    /**
    * 列表查询
    */

    @PostMapping("/list")
    public ResponseDto list(@RequestBody RewardCheckDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            RewardCheckExample webchatIllegalExample = new RewardCheckExample();
            RewardCheckExample.Criteria ca = webchatIllegalExample.createCriteria();
            if (!StringUtils.isEmpty(pageDto.getZt())){
                ca.andZtEqualTo(pageDto.getZt());
            }

            List<RewardCheck> webchatIllegalList = rewardCheckService.list(webchatIllegalExample);
            PageInfo<RewardCheck> pageInfo = new PageInfo<RewardCheck>(webchatIllegalList);
            pageDto.setTotal(pageInfo.getTotal());
            List<RewardCheckDto> webchatIllegalDtoList = CopyUtil.copyList(webchatIllegalList, RewardCheckDto.class);
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
    @PostMapping("/save2")
    public ResponseDto save(RewardCheckDto rewardCheckDto) {
        // 保存校验
        ValidatorUtil.length(rewardCheckDto.getCreateBy(), "创建人", 1, 50);
        ValidatorUtil.length(rewardCheckDto.getUpdateBy(), "更新人", 1, 50);
        ValidatorUtil.length(rewardCheckDto.getSysOrgCode(), "所属部门", 1, 64);
        ValidatorUtil.length(rewardCheckDto.getWfxwms(), "违法行为描述", 1, 2000);
        ValidatorUtil.length(rewardCheckDto.getWflb(), "违法类别(codeset-0621)", 1, 32);
        ValidatorUtil.length(rewardCheckDto.getWfxw(), "违法行为", 1, 256);
        ValidatorUtil.length(rewardCheckDto.getCzzp(), "是否车载照片", 1, 1);
        ValidatorUtil.length(rewardCheckDto.getWfld(), "违法路段", 1, 256);
        ValidatorUtil.length(rewardCheckDto.getWfgps(), "坐标信息", 1, 128);
        ValidatorUtil.length(rewardCheckDto.getWfldxxdz(), "详细地址", 1, 256);
        ValidatorUtil.length(rewardCheckDto.getCplx(), "车牌类型", 1, 10);
        ValidatorUtil.length(rewardCheckDto.getCphm(), "车牌号码", 1, 10);
        ValidatorUtil.length(rewardCheckDto.getJbr(), "举报人", 1, 128);
        ValidatorUtil.length(rewardCheckDto.getSfzmhm(), "身份证明号码", 1, 60);
        ValidatorUtil.length(rewardCheckDto.getSjh(), "手机号", 1, 60);
        ValidatorUtil.length(rewardCheckDto.getBz(), "备注", 1, 4000);
        ValidatorUtil.length(rewardCheckDto.getShr(), "审核人", 1, 128);
        ValidatorUtil.length(rewardCheckDto.getShyy(), "审核原因", 1, 200);
        ValidatorUtil.length(rewardCheckDto.getShsm(), "审核说明", 1, 4000);
        ValidatorUtil.length(rewardCheckDto.getZt(), "状态1已上报2审核通过3审核不通过", 1, 2);
        ValidatorUtil.length(rewardCheckDto.getOpenid(), "微信用户id", 1, 32);
        ValidatorUtil.length(rewardCheckDto.getLsh(), "流水号", 1, 32);
        ValidatorUtil.length(rewardCheckDto.getJlff(), "奖励发放0未发放1已发放", 1, 1);
        ValidatorUtil.length(rewardCheckDto.getWfxwxx(), "违法行为详细", 1, 20);

        ResponseDto responseDto = new ResponseDto();
        rewardCheckService.save(rewardCheckDto);
        responseDto.setContent(rewardCheckDto);
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
        ValidatorUtil.length(webchatRewardDto.getSfzmhm(), "身份证明号码", 1, 60);
        ValidatorUtil.length(webchatRewardDto.getSjh(), "手机号", 1, 60);
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
        LoginUserDto loginUserDto = getRequestHeader();
        webchatRewardService.save(webchatRewardDto, loginUserDto);
        responseDto.setContent(webchatRewardDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
        public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        rewardCheckService.delete(id);
        return responseDto;
    }
}


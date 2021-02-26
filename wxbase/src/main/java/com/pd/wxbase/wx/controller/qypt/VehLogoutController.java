package com.pd.wxbase.wx.controller.qypt;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehLogout;
import com.pd.server.main.domain.VehLogoutExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehLogoutDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.service.VehLogoutService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/vehLogout")
public class VehLogoutController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(VehLogoutController.class);
    public static final String BUSINESS_NAME = "机动车注销";

    @Resource
    private VehLogoutService vehLogoutService;
    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VehLogoutDto pageDto) {
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        ResponseDto responseDto = new ResponseDto();
        try{
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            VehLogoutExample vehLogoutExample = new VehLogoutExample();
            VehLogoutExample.Criteria ca = vehLogoutExample.createCriteria();
            if(!list.isEmpty()){
                ca.andSysOrgCodeIn(list);
            }
            if (!StringUtils.isEmpty(pageDto.getHphm())){
                //ca.andHphmEqualTo(pageDto.getHphm());
                ca.andHphmLike("%"+pageDto.getHphm()+"%");
            }
            if (!StringUtils.isEmpty(pageDto.getHpzl())){
                ca.andHpzlEqualTo(pageDto.getHpzl());
            }
            if (!StringUtils.isEmpty(pageDto.getClsbdh())){
                //ca.andClsbdhEqualTo(pageDto.getClsbdh());
                ca.andClsbdhLike("%"+pageDto.getClsbdh()+"%");
            }
            if (!StringUtils.isEmpty(pageDto.getSysOrgCode())){
                ca.andSysOrgCodeEqualTo(pageDto.getSysOrgCode());
            }
            if (!StringUtils.isEmpty(pageDto.getZt())){
                ca.andZtEqualTo(pageDto.getZt());
            }
            if (!StringUtils.isEmpty(pageDto.getStime())){
                ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
            }
            if (!StringUtils.isEmpty(pageDto.getEtime())){
                ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
            }
            vehLogoutExample.setOrderByClause(" create_time desc");
            List<VehLogout> vehLogoutList = vehLogoutService.list(vehLogoutExample);
            PageInfo<VehLogout> pageInfo = new PageInfo<>(vehLogoutList);
            pageDto.setTotal(pageInfo.getTotal());
            List<VehLogoutDto> vehLogoutDtoList = CopyUtil.copyList(vehLogoutList, VehLogoutDto.class);
            pageDto.setList(vehLogoutDtoList);

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
    public ResponseDto save(@RequestBody VehLogoutDto vehLogoutDto) {
    // 保存校验
            ValidatorUtil.length(vehLogoutDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(vehLogoutDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(vehLogoutDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(vehLogoutDto.getXh(), "机动车序号", 1, 32);
            ValidatorUtil.length(vehLogoutDto.getHpzl(), "号牌种类", 1, 10);
            ValidatorUtil.length(vehLogoutDto.getHphm(), "号牌号码", 1, 10);
            ValidatorUtil.length(vehLogoutDto.getZxyy(), "注销原因", 1, 2);
            ValidatorUtil.length(vehLogoutDto.getHsqymc(), "回收企业名称", 1, 256);
            ValidatorUtil.length(vehLogoutDto.getHshp(), "回收号牌", 1, 32);
            ValidatorUtil.length(vehLogoutDto.getHsxsz(), "回收行驶证", 1, 32);
            ValidatorUtil.length(vehLogoutDto.getHsdjzs(), "回收登记证书", 1, 32);
            ValidatorUtil.length(vehLogoutDto.getCfjg(), "处罚机关", 1, 256);
            ValidatorUtil.length(vehLogoutDto.getJdsbh(), "处罚决定书编号", 1, 32);
            ValidatorUtil.length(vehLogoutDto.getJxmj(), "监销民警", 1, 128);
            ValidatorUtil.length(vehLogoutDto.getZxjg(), "注销机关", 1, 32);
            ValidatorUtil.length(vehLogoutDto.getJbr(), "经办人", 1, 32);
            ValidatorUtil.length(vehLogoutDto.getGlbm(), "", 1, 128);
            ValidatorUtil.length(vehLogoutDto.getBz(), "备注", 1, 4000);
            ValidatorUtil.length(vehLogoutDto.getClpp1(), "车辆品牌", 1, 256);
            ValidatorUtil.length(vehLogoutDto.getClxh(), "车辆型号", 1, 256);
            ValidatorUtil.length(vehLogoutDto.getSyr(), "机动车所有人", 1, 256);
            ValidatorUtil.length(vehLogoutDto.getClsbdh(), "车辆识别代号", 1, 25);
            ValidatorUtil.length(vehLogoutDto.getLsh(), "流水号", 1, 255);
            ValidatorUtil.length(vehLogoutDto.getShr(), "审核人", 1, 128);
            ValidatorUtil.length(vehLogoutDto.getShyy(), "审核原因", 1, 128);
            ValidatorUtil.length(vehLogoutDto.getShsm(), "", 1, 2000);
            ValidatorUtil.length(vehLogoutDto.getZt(), "状态", 1, 1);
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"号牌种类");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"号牌号码");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"车辆识别代号");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"机动车序号");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"车辆品牌");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"车辆型号");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"机动车所有人");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"处罚决定书编号");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"处罚时间");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"注销原因");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"监销民警");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"回收号牌");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"回收行驶证");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"回收登记证书");
            ValidatorUtil.require(vehLogoutDto.getHpzl(),"处罚机关");
            ValidatorUtil.require(vehLogoutDto.getZxyy(),"注销原因");

        ResponseDto responseDto = new ResponseDto();
        try {
            LoginUserDto loginUserDto = getRequestHeader();
            if (StringUtils.isEmpty(vehLogoutDto.getId())) {
                //vehLogoutDto.setGlbm(loginUserDto.getDeptcode());//设置管理部门（审核部门）
                vehLogoutDto.setCreateBy(loginUserDto.getLoginName());
                vehLogoutDto.setCreateTime(new Date());
                vehLogoutDto.setZt("1");
                vehLogoutDto.setLsh(UuidUtil.getUuid());//设置流水号
                vehLogoutDto.setSysOrgCode(loginUserDto.getDeptcode());//回收企业代码
            }else{
                vehLogoutDto.setUpdateBy(loginUserDto.getLoginName());
                vehLogoutDto.setUpdateTime(new Date());
            }
            if("1".equals(vehLogoutDto.getZt())){
                if(!StringUtils.isEmpty(vehLogoutDto.getId())){
                    webchatFileinfoService.deleteByLsh(vehLogoutDto.getLsh());
                }
                if(!StringUtils.isEmpty(vehLogoutDto.getTpListStr())&&!vehLogoutDto.getTpListStr().isEmpty()){
                    for(String tplj : vehLogoutDto.getTpListStr()){
                        WebchatFileinfoDto fileinfo = new WebchatFileinfoDto();
                        fileinfo.setLsh(vehLogoutDto.getLsh());
                        fileinfo.setZplj(tplj);
                        fileinfo.setCjsj(new Date());
                        fileinfo.setZpzl("pic");
                        fileinfo.setZplx("1");
                        webchatFileinfoService.save(fileinfo);
                    }
                }
                if(!StringUtils.isEmpty(vehLogoutDto.getVideoLj())){
                    WebchatFileinfoDto fileinfo = new WebchatFileinfoDto();
                    fileinfo.setLsh(vehLogoutDto.getLsh());
                    fileinfo.setZplj(vehLogoutDto.getVideoLj());
                    fileinfo.setCjsj(new Date());
                    fileinfo.setZpzl("video");
                    fileinfo.setZplx("2");
                    webchatFileinfoService.save(fileinfo);
                }
            }
            if (!StringUtils.isEmpty(vehLogoutDto.getZt()) && "1" != vehLogoutDto.getZt()) {
                vehLogoutDto.setShr(loginUserDto.getName());
                vehLogoutDto.setJbr(loginUserDto.getLoginName());
                vehLogoutDto.setShsj(new Date());
                vehLogoutDto.setGlbm(loginUserDto.getDeptcode());
                vehLogoutDto.setUpdateBy(loginUserDto.getLoginName());
                vehLogoutDto.setUpdateTime(new Date());
                //企业名称，代码

            }
            vehLogoutService.save(vehLogoutDto);
            responseDto.setContent(vehLogoutDto);
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
        vehLogoutService.delete(id);
        return responseDto;
    }
}

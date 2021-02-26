package com.pd.wxbase.wx.controller.qypt;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehImpawn;
import com.pd.server.main.domain.VehImpawnExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehImpawnDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.service.VehImpawnService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
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
@RequestMapping("/admin/vehImpawn")
public class VehImpawnController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(VehImpawnController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private VehImpawnService vehImpawnService;
    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VehImpawnDto vehImpawnDto) {
        LoginUserDto loginUserDto   = getRequestHeader();
        ResponseDto responseDto = new ResponseDto();
        try{
            List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
            PageHelper.startPage(vehImpawnDto.getPage(), vehImpawnDto.getSize());
            VehImpawnExample vehImpawnExample = new VehImpawnExample();
            VehImpawnExample.Criteria ca = vehImpawnExample.createCriteria();
            if (!StringUtils.isEmpty(vehImpawnDto.getXh())){
                ca.andXhEqualTo(vehImpawnDto.getXh());
            }
            if(!list.isEmpty()){
                ca.andSysOrgCodeIn(list);
            }
            if (!StringUtils.isEmpty(vehImpawnDto.getClsbdh())){
                ca.andClsbdhEqualTo(vehImpawnDto.getClsbdh());
            }
            if (!StringUtils.isEmpty(vehImpawnDto.getZhth())){
                ca.andZhthEqualTo(vehImpawnDto.getZhth());
            }
            if (!StringUtils.isEmpty(vehImpawnDto.getDyhth())){
                ca.andDyhthEqualTo(vehImpawnDto.getDyhth());
            }
            if (!StringUtils.isEmpty(vehImpawnDto.getSfzmhm())){
                ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(vehImpawnDto.getSfzmhm()));
            }
            if (!StringUtils.isEmpty(vehImpawnDto.getStime())){
                ca.andCreateTimeGreaterThanOrEqualTo(vehImpawnDto.getStime());
            }
            if (!StringUtils.isEmpty(vehImpawnDto.getEtime())){
                ca.andCreateTimeLessThanOrEqualTo(vehImpawnDto.getEtime());
            }

            List<VehImpawn> vehImpawnList = vehImpawnService.list(vehImpawnExample);

            PageInfo<VehImpawn> pageInfo = new PageInfo<>(vehImpawnList);
            vehImpawnDto.setTotal(pageInfo.getTotal());
            List<VehImpawnDto> vehImpawnDtoList = CopyUtil.copyList(vehImpawnList, VehImpawnDto.class);
            vehImpawnDto.setList(vehImpawnDtoList);

            responseDto.setContent(vehImpawnDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody VehImpawnDto vehImpawnDto) throws Exception {
        // 保存校验
            ValidatorUtil.require(vehImpawnDto.getXh(), "机动车序号");
            ValidatorUtil.length(vehImpawnDto.getXh(), "机动车序号", 1, 14);
            ValidatorUtil.require(vehImpawnDto.getClsbdh(), "车辆识别代号");
            ValidatorUtil.length(vehImpawnDto.getClsbdh(), "车辆识别代号", 1, 20);
            ValidatorUtil.require(vehImpawnDto.getZhth(), "主合同号");
            ValidatorUtil.length(vehImpawnDto.getZhth(), "主合同号", 1, 64);
            ValidatorUtil.require(vehImpawnDto.getDyhth(), "抵押合同号");
            ValidatorUtil.length(vehImpawnDto.getDyhth(), "抵押合同号", 1, 32);
            ValidatorUtil.length(vehImpawnDto.getJbr(), "经办人", 1, 128);
            ValidatorUtil.length(vehImpawnDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.require(vehImpawnDto.getSfzmmc(), "身份证明名称");
            ValidatorUtil.length(vehImpawnDto.getSfzmmc(), "身份证明名称", 1, 10);
            ValidatorUtil.require(vehImpawnDto.getSfzmhm(), "身份证明号码");
            ValidatorUtil.length(vehImpawnDto.getSfzmhm(), "身份证明号码", 1, 20);
            ValidatorUtil.require(vehImpawnDto.getXzqh(), "抵押权人行政区划");
            ValidatorUtil.length(vehImpawnDto.getXzqh(), "抵押权人行政区划", 1, 128);
            ValidatorUtil.require(vehImpawnDto.getXxdz(), "详细地址");
            ValidatorUtil.length(vehImpawnDto.getXxdz(), "详细地址", 1, 256);
            ValidatorUtil.length(vehImpawnDto.getYzbm(), "邮政编码", 1, 6);
            ValidatorUtil.require(vehImpawnDto.getLxdh(), "联系电话");
            ValidatorUtil.length(vehImpawnDto.getLxdh(), "联系电话", 1, 13);
            ValidatorUtil.length(vehImpawnDto.getXgzl(), "相关资料", 1, 20);
            ValidatorUtil.length(vehImpawnDto.getBz(), "备注", 1, 1000);
            ValidatorUtil.length(vehImpawnDto.getDyqrdm(), "抵押权人代码", 1, 128);
            ValidatorUtil.length(vehImpawnDto.getDyqrlx(), "抵押权人类型", 1, 20);
            ValidatorUtil.length(vehImpawnDto.getDyqrsfzmmc(), "抵押权人身份证名称", 1, 20);
            ValidatorUtil.length(vehImpawnDto.getDyqrsfzmhm(), "抵押权人身份证号码", 1, 20);
            ValidatorUtil.length(vehImpawnDto.getSjhm(), "抵押权人手机号", 1, 13);
            ValidatorUtil.length(vehImpawnDto.getHpzl(), "号牌种类", 1, 2);
            ValidatorUtil.length(vehImpawnDto.getHphm(), "号牌号码", 1, 20);
            ValidatorUtil.length(vehImpawnDto.getDyzt(), "抵押状态", 1, 1);

        ResponseDto responseDto = new ResponseDto();
        LoginUserDto requestHeader = this.getRequestHeader();
        vehImpawnService.save(vehImpawnDto,requestHeader);
        responseDto.setContent(vehImpawnDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        vehImpawnService.delete(id);
        return responseDto;
    }

    /* double类型转String 类型的小数点后的00
     * @param num double参数
     * @return String 类型
     */
    public static String doubleTransform(double num){
        String strNum = num+"";
        int a = strNum.indexOf(".");
        if(a>0){
            //获取小数点后面的数字
            String dianAfter = strNum.substring(a+1);
            if("0".equals(dianAfter)){
                return strNum+"0";
            }else{
                if(dianAfter.length()==1){
                    return strNum +"0";
                }else{
                    return strNum;
                }
            }
        }else{
            return strNum+".00";
        }
    }
}

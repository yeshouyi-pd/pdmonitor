package com.pd.wxbase.wx.controller.tcc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehParkLot;
import com.pd.server.main.domain.VehParkLotExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.dto.basewx.my.VehParkLotGuoupDto;
import com.pd.server.main.service.VehParkLotService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/vehParkLot")
public class VehParkLotController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(VehParkLotController.class);
public static final String BUSINESS_NAME = "违法车场";

@Resource
private VehParkLotService vehParkLotService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VehParkLotDto pageDto) {
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        ResponseDto responseDto = new ResponseDto();
        try{
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            VehParkLotExample vehParkLotExample = new VehParkLotExample();
            VehParkLotExample.Criteria ca = vehParkLotExample.createCriteria();
            if(!list.isEmpty()){
                ca.andSysOrgCodeIn(list);
            }
            if(!StringUtils.isEmpty(pageDto.getGl())){
                ca.andGlEqualTo(pageDto.getGl());
            }
            if(!StringUtils.isEmpty(pageDto.getCllb())){
                ca.andCllbEqualTo(pageDto.getCllb());
            }
            if(!StringUtils.isEmpty(pageDto.getHphm())){
                ca.andHphmLike("%"+pageDto.getHphm()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getLsh())){
                ca.andLshLike("%"+pageDto.getLsh()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getClsbdh())){
                ca.andClsbdhLike("%"+pageDto.getClsbdh()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getPzbh())){
                ca.andPzbhLike("%"+pageDto.getPzbh()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getCsys())){
                ca.andCsysLike("%"+pageDto.getCsys()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getFdjh())){
                ca.andFdjhLike("%"+pageDto.getFdjh()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getFxjgmc())){
                ca.andFxjgmcLike("%"+pageDto.getFxjgmc()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getTfwz())){
                ca.andTfwzLike("%"+pageDto.getTfwz()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getSysOrgCode())){
                ca.andSysOrgCodeEqualTo(pageDto.getSysOrgCode());
            }
            if(!StringUtils.isEmpty(pageDto.getStartTimeIn())){
                ca.andRcrqGreaterThanOrEqualTo(pageDto.getStartTimeIn());
            }
            if(!StringUtils.isEmpty(pageDto.getEndTimeIn())){
                ca.andRcrqLessThanOrEqualTo(pageDto.getEndTimeIn());
            }
            if(!StringUtils.isEmpty(pageDto.getStartTimeOut())){
                ca.andCcrqGreaterThanOrEqualTo(pageDto.getStartTimeOut());
            }
            if(!StringUtils.isEmpty(pageDto.getEndTimeOut())){
                ca.andCcrqLessThanOrEqualTo(pageDto.getEndTimeOut());
            }
            if(!StringUtils.isEmpty(pageDto.getXh())){
                ca.andXhLike("%"+pageDto.getXh()+"%");
            }
            if(!StringUtils.isEmpty(pageDto.getZt())){
                ca.andZtEqualTo(pageDto.getZt());
            }
            if(!StringUtils.isEmpty(pageDto.getStime())){
                ca.andCreateTimeGreaterThanOrEqualTo(DateUtils.getStrToDateFormat(pageDto.getStime(),"yyyy-MM-dd"));
            }
            if(!StringUtils.isEmpty(pageDto.getEtime())){
                ca.andCreateTimeLessThanOrEqualTo(DateUtils.getStrToDateFormat(pageDto.getEtime()+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
            }
            vehParkLotExample.setOrderByClause(" create_time desc");
            List<VehParkLot> vehParkLotList = vehParkLotService.list(vehParkLotExample);
            PageInfo<VehParkLot> pageInfo = new PageInfo<>(vehParkLotList);
            pageDto.setTotal(pageInfo.getTotal());
            List<VehParkLotDto> vehParkLotDtoList = CopyUtil.copyList(vehParkLotList, VehParkLotDto.class);
            pageDto.setList(vehParkLotDtoList);
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
    public ResponseDto save(@RequestBody VehParkLotDto vehParkLotDto) {
        // 保存校验
            ValidatorUtil.length(vehParkLotDto.getGl(), "归类", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getLsh(), "条形码流水号", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getHphm(), "号牌号码", 1, 20);
            ValidatorUtil.length(vehParkLotDto.getClsbdh(), "车辆识别代号", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getFdjh(), "发动机号", 1, 20);
            ValidatorUtil.length(vehParkLotDto.getCpys(), "车牌颜色", 1, 2);
            ValidatorUtil.length(vehParkLotDto.getCsys(), "车身颜色", 1, 2);
            ValidatorUtil.length(vehParkLotDto.getSyr(), "车主姓名", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getSfzmhm(), "身份证明号码", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getPzbh(), "凭证编号", 1, 15);
            ValidatorUtil.length(vehParkLotDto.getXh(), "机动车序号", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getDabh(), "档案编号", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getZsxxdz(), "详细地址", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getWfdd(), "违法地点", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getWfdz(), "违法地址", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getZqmj(), "执勤民警", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getFxjgmc(), "大队名称", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getFzjg(), "大队代码", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getSjxm(), "拯救司机姓名", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getZjcbh(), "拯救车编号", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getWfdm(), "违法代码", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getYcy(), "验车员", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getTfwz(), "停放位置", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getBz(), "备注", 1, 2000);
            ValidatorUtil.length(vehParkLotDto.getCcyy(), "出厂原因", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getYjdw(), "移交单位", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getYjdwmc(), "移交单位名称", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getCcjsr(), "出场接收人", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getCcrlxdh(), "出场人联系电话", 1, 128);
            ValidatorUtil.length(vehParkLotDto.getCcms(), "出场描述", 1, 45);
            ValidatorUtil.length(vehParkLotDto.getZt(), "状态1入场2出场", 1, 45);

        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();

        if (StringUtils.isEmpty(vehParkLotDto.getId()) && StringUtils.isEmpty(vehParkLotDto.getZt())) {
            vehParkLotDto.setCreateBy(loginUserDto.getLoginName());
            vehParkLotDto.setCreateTime(new Date());
            vehParkLotDto.setSysOrgCode(loginUserDto.getDeptcode());
            vehParkLotDto.setZt("1");
        }
        vehParkLotDto.setUpdateBy(loginUserDto.getLoginName());
        vehParkLotDto.setUpdateTime(new Date());
        try {
            if(!StringUtils.isEmpty(vehParkLotDto.getCcrlxdh())){
                vehParkLotDto.setCcrlxdh(DesEncrypt.DesJiaMi(vehParkLotDto.getCcrlxdh()));
            }
            if(!StringUtils.isEmpty(vehParkLotDto.getSfzmhm())){
                vehParkLotDto.setSfzmhm(DesEncrypt.DesJiaMi(vehParkLotDto.getSfzmhm()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!StringUtils.isEmpty(vehParkLotDto.getZt())&&!vehParkLotDto.getZt().equals("2")){
            vehParkLotService.save(vehParkLotDto);
        }else{
            vehParkLotDto.setCcrq(new Date());
            if(!StringUtils.isEmpty(vehParkLotDto.getIdList())&&vehParkLotDto.getIdList().size()>0){//批量出场
                for(String id : vehParkLotDto.getIdList()){
                    vehParkLotDto.setId(id);
                    vehParkLotService.save(vehParkLotDto);
                }
            }else{//单一出场
                vehParkLotService.save(vehParkLotDto);
            }
        }
        responseDto.setContent(vehParkLotDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        vehParkLotService.delete(id);
        return responseDto;
    }

    /**
     * 本月、本年出场数量
     */
    @GetMapping("/getWfccslByDay")
    public ResponseDto getWfccslByDay(){
        Map<String, Object> map = new HashMap<>();
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        VehParkLotDto vehParkLotDto = new VehParkLotDto();
        //获取本月
        vehParkLotDto.setChooseType("%Y-%m");
        vehParkLotDto.setCheckdate(DateTools.getFormatDate(new Date(),"yyyy-MM"));
        vehParkLotDto.setZt("2");
        if(!StringUtils.isEmpty(list)){
            vehParkLotDto.setSysOrgCodeListStr(list);
        }else{
            vehParkLotDto.setSysOrgCodeListStr(new ArrayList<>());
        }
        List<VehParkLotGuoupDto> yfCcsl = vehParkLotService.getStatisticsByDay(vehParkLotDto);
        //获取本年
        vehParkLotDto.setChooseType("%Y");
        vehParkLotDto.setCheckdate(DateTools.getFormatDate(new Date(),"yyyy"));
        List<VehParkLotGuoupDto> nfCcsl = vehParkLotService.getStatisticsByDay(vehParkLotDto);
        map.put("yfCcsl",!StringUtils.isEmpty(yfCcsl)?yfCcsl:new ArrayList<>());
        map.put("nfCcsl",!StringUtils.isEmpty(nfCcsl)?nfCcsl:new ArrayList<>());
        responseDto.setContent(map);
        return responseDto;
    }
}

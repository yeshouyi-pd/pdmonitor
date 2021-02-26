package com.pd.wxbase.wx.controller.ywyy;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.MyWxYyDto;
import com.pd.server.main.dto.basewx.my.YwyyParamDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.my.MyDeptMapper;
import com.pd.server.main.service.DeptService;
import com.pd.server.main.service.DeptYwlxService;
import com.pd.server.main.service.DeptYysjService;
import com.pd.server.main.service.WxPageService;
import com.pd.server.main.service.ywyy.YwyyWxService;
import com.pd.server.util.BarcodeUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.conf.WorkingdaysUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 业务预约 微信端
 */
@RestController
@RequestMapping("/wx/ywyy")
public class YwyyWxController  extends BaseWxController {
    public static final String BUSINESS_NAME = "微信预约【微信端】";

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private YwyyWxService ywyyWxService;
    @Resource
    private MyDeptMapper myDeptMapper;

    @Resource
    private DeptService deptService;

    @Resource
    private DeptYysjService deptYysjService;

    @Resource
    private DeptYwlxService deptYwlxService;

    @Resource
    private WxPageService wxPageService;


    /**
     * 业务预约前获取当前用户今天
     * 是否存在已经预约但是未办结的业务
     * @return
     */

    @PostMapping("queryCountByopenid")
    public ResponseDto queryCountByopenid(@RequestBody WxYyDto wxYyDto){
        ResponseDto responseDto = new ResponseDto();
        WxYyExample example = new WxYyExample();
        WxYyExample.Criteria ca = example.createCriteria();
        ca.andZtEqualTo("1");//已预约
        ca.andOpenidEqualTo(wxYyDto.getOpenid());
        example.setOrderByClause( "cjsj desc ");
        List<WxYyDto> list = ywyyWxService.selectByExample(example);
        responseDto.setContent(list);
        return  responseDto;

    }

    /**
     * 当点击个人预约或企业预约时
     * 校验当前部门的预约次数是否充足
     * @param wxYyDto
     * @return
     */
    @PostMapping("queryCanyyBydept")
    public  ResponseDto queryCanyyBydept(@RequestBody WxYyDto wxYyDto){
        ResponseDto responseDto = new ResponseDto();
        int daymax = wxYyDto.getDaymax();
        Integer domax = myDeptMapper.queryCanyyBydept(wxYyDto);
        if(null != domax){
            daymax = daymax -domax;//当前部门的预约详情
        }
        responseDto.setContent(daymax);
        return  responseDto;

    }


    /**
     * 获取所有再办业务类型
     * @return
     */
    @GetMapping("/getAllYwlx")
    public ResponseDto getAllYwlx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String, List<Codeset>> nmap = new LinkedHashMap<String, List<Codeset>>();
        Map<String, Object> alls =(Map<String, Object>)redisTemplate.opsForValue().get(RedisCode.CODESETVO);
        Object jdc = (Object)alls.get(CodeType.CODE_JDC_YWLX);
        Object jsr = (Object)alls.get(CodeType.CODE_JSR_YWLX);
        Object wf = (Object)alls.get(CodeType.CODE_WF_YWLX);
        List<Codeset> listjdc = new ArrayList<Codeset>();
        List<Codeset> listjsr = new ArrayList<Codeset>();
        List<Codeset> listwf = new ArrayList<Codeset>();
        List<Codeset> listTEST = new ArrayList<Codeset>();
        WxPageDto wxPageDto = new WxPageDto();
        wxPageDto.setIsEnable("1");
        wxPageDto.setLevel("3");
        List<WxPage> wxPages = wxPageService.findAllByParam(wxPageDto);
        Set<String> linkedIds = wxPages.stream().map(WxPage::getLinkId).collect(Collectors.toSet());
        if(null !=jdc ){
            List<Codeset> temp = JSONObject.parseArray(jdc.toString(),Codeset.class);
            for(Codeset entity : temp){
                if(linkedIds.contains(entity.getId())){
                    listjdc.add(entity);
                }
            }
        }
        if(null !=jsr ){
            List<Codeset> temp = JSONObject.parseArray(jsr.toString(),Codeset.class);
            for(Codeset entity : temp){
                if(linkedIds.contains(entity.getId())){
                    listjsr.add(entity);
                }
            }
        }
        if(null !=wf ){
            List<Codeset> temp = JSONObject.parseArray(wf.toString(),Codeset.class);
            for(Codeset entity : temp){
                if(linkedIds.contains(entity.getId())){
                    listwf.add(entity);
                }
            }
        }
        nmap.put("CODE_JDC_YWLX",listjdc);
        nmap.put("CODE_JSR_YWLX",listjsr);
        nmap.put("CODE_WF_YWLX",listwf);
        responseDto.setContent(nmap);
        return responseDto;
    }


    /**
     * 获取
     * @return
     */
    @GetMapping("/getZjlx")
    public  ResponseDto  getZjlx(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto>   list  = getzljx(CodeType.CODE_FZFMMC);
        responseDto.setContent(list);
        return responseDto;
    }


    /**
     * 根据微信页面选择的业务分类和业务类型查询
     * 能办理改业务的所有部门
     * @param ywyyParamDto
     * @return
     */
    @PostMapping("/getDeptByYwlx")
    public ResponseDto getDeptByYwlx(@RequestBody YwyyParamDto ywyyParamDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != ywyyParamDto){
            if(StringUtils.isNotBlank(ywyyParamDto.getYwfl())&&
                    StringUtils.isNotBlank(ywyyParamDto.getYwlx())){
                if(StringUtils.isNotBlank(ywyyParamDto.getSearchvalue())){
                    ywyyParamDto.setSearchvalue("%"+ywyyParamDto.getSearchvalue().trim()+"%");
                }
                List<DeptDto> list  = ywyyWxService.getDeptByYwlx(ywyyParamDto);
                if(!CollectionUtils.isEmpty(list)){
                    //开始排序
                    responseDto.setContent(list);
                }
            }
        }
        return responseDto;
    }


    /**
     * 获取当前部门预约状态
     * @param deptDto
     * @return
     */
    @PostMapping("/getDeptDay")
    public ResponseDto getDeptDay(@RequestBody DeptDto deptDto){
        Dept vo  = deptService.queryByDeptcode(deptDto.getDeptcode());
        /**
         * 获取当前部门最大预约日期 是否节假日
         */
        Map<String,String>  map  = WorkingdaysUtils.getWorkingDays(vo.getMaxday());
        /**
         *  获取当前部门每天预约总量
         */
        Map<String, Integer>  mapmun = (Map<String, Integer>) redisTemplate.opsForValue().get(RedisCode.DEPTYYNUM);
        Integer maxmun = mapmun.get(vo.getDeptcode());//获取当天的最大预约数
        /**
         * 根据当前部门查询当前部门当天以及之后的预约情况
         */
        List<KvIntDto>  list = myDeptMapper.getYysjMum(vo.getDeptcode());
        Map<String,Integer> listmap = new LinkedHashMap<String,Integer>();
          if(!CollectionUtils.isEmpty(list)){
              for(KvIntDto  kv  : list){
                      listmap.put(kv.getKey(),kv.getValue());
              }
          }
        /**
         * 开始处理数据
         */
         Map<String,Object>  repmap = new HashMap<String,Object>();
         List<MyWxYyDto> replist = new ArrayList<MyWxYyDto>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
             MyWxYyDto vos = new MyWxYyDto();
              vos.setYysj(entry.getKey());//预约日期
              vos.setZt(entry.getValue());//预约状态
              Integer yiyuyue = listmap.get(entry.getKey());//当天已预约数量
              if(null != yiyuyue){
                  vos.setYysl(maxmun -yiyuyue);
              }else{
                  vos.setYysl(maxmun);
              }
            replist.add(vos);
        }
        repmap.put("yysl",replist);//有效预约天内每天的有效预约情况
        repmap.put("dept",vo);//当前部门信息
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(repmap);
        return responseDto;
    }

    /**
     * 根据选择的时间获取当前部门当天的预约数时段数量
     * @param deptYysjDto
     * @return
     */

    @PostMapping("/getYysd")
    public ResponseDto getYysd(@RequestBody DeptYysjDto deptYysjDto){
        ResponseDto responseDto = new ResponseDto();
        /**
         * 根据当前部门查询每日的预约情况
         */
        List<DeptYysjDto> deptYysjDtoList  = deptYysjService.queryList(deptYysjDto.getDeptcode());

        /**
         * 根据当天情况查询ww_yy表各时段的预约情况
         */
        List<KvIntDto> ListKV   = myDeptMapper.getYysjByDayMum(deptYysjDto);
         //当天各时段的预约情况
        Map<String,Integer> maplv = new LinkedHashMap<String,Integer>();
        if(!CollectionUtils.isEmpty(ListKV)){
            for(KvIntDto  kv  : ListKV){
                maplv.put(kv.getKey(),kv.getValue());
            }
        }
        /**
         * 各时段总量减除当天各时段预约量得到当天各业务的的实时预约量
         */
        if(!CollectionUtils.isEmpty(deptYysjDtoList)){
            for(DeptYysjDto yyssdao : deptYysjDtoList){
                Integer yymun = maplv.get(yyssdao.getId());
                if(null != yymun){
                    yyssdao.setYymun(yyssdao.getYymun() - yymun);//当天可约量
                }

            }

        }
        responseDto.setContent(deptYysjDtoList);
        return responseDto;

    }

    /**
     * 保存预约信息
     * @param wxYyDto
     * @return
     */
    @PostMapping("/savewwyy")
    public  ResponseDto savewwyy(@RequestBody WxYyDto wxYyDto){
        ResponseDto responseDto = new ResponseDto();
        /**
         * 验证参数合理性
         */
         ValidatorUtil.require(wxYyDto.getId(), "操作异常，请重试");
         ValidatorUtil.require(wxYyDto.getYwfl(), "操作异常，请重试");
         ValidatorUtil.require(wxYyDto.getYwlx(), "操作异常，请重试");
         ValidatorUtil.require(wxYyDto.getYysj(), "操作异常，请重试");
         ValidatorUtil.require(wxYyDto.getYysd(), "操作异常，请重试");
         ValidatorUtil.require(wxYyDto.getYyrq(), "操作异常，请重试");
         ValidatorUtil.require(wxYyDto.getDeptcode(), "操作异常，请重试");
         ValidatorUtil.require(wxYyDto.getYysl(), "操作异常，请重试");
        // ValidatorUtil.require(wxYyDto.getOpenid(), "操作异常，请重试");
        ValidatorUtil.require(wxYyDto.getZjhm(), "证件号码不能为空");
        ValidatorUtil.require(wxYyDto.getName(), "预约人姓名不能为空");

        /**
         * 保存前线查询当天预约量是否充足
         */

        /**
         *  获取当前部门当前时段预约总量
         */
        Integer maxmun = myDeptMapper.getYysjByDayYysdMum(wxYyDto);
        /**
         * 获取当天部门当天当前时段已预约总量
         */
        Integer nowmun   = myDeptMapper.getYysjByDayNowMum(wxYyDto);
        if(null ==  nowmun){ //查不到返回null  赋值0
            nowmun = 0;
        }
        Integer syl = maxmun - nowmun;//剩余量

                if(syl <= 0){//当天已经预约完
                    throw  new WxStrException("当前时段预约量不足！");
                }
                if(null != wxYyDto.getYysl()){
                    if(maxmun < wxYyDto.getYysl()){
                        throw  new WxStrException("当前时段预约量不足！");
                    }
                    if(syl < wxYyDto.getYysl()){
                        throw  new WxStrException("当前时段预约量不足！");
                    }
                }

        wxYyDto.setCjsj(DateTools.getFormatDate(new Date(),DateTools.yyyy_MM_dd_HH_mm_ss));
        wxYyDto.setZt("1");//正常
        //wxYyDto.setId(UUID.randomUUID().toString()); //前端已经生成
        ywyyWxService.savewwyy(wxYyDto);

        return responseDto;
    }


    /**
     * 更具ID查询预约情况
     * @param wxYyDto
     * @return
     */
    @PostMapping("/getYyxxInfo")
    public  ResponseDto getYyxxInfo(@RequestBody WxYyDto wxYyDto){
        ResponseDto responseDto = new ResponseDto();
        WxYyDto yyinfo = null;
        Dept dept = null;
        DeptYwlx ywlx = null;
        if(null != wxYyDto){
            if(StringUtils.isNotBlank(wxYyDto.getId())){
                yyinfo= ywyyWxService.queryById(wxYyDto.getId());
            }

        }
        /**
         * 查询部门信息
         */
        if(null != yyinfo){
            String  ywm = BarcodeUtil.generateBase64(yyinfo.getId());//一维码
            if(StringUtils.isNotBlank(ywm)){
                ywm = "data:image/png;base64,"+ywm;
                yyinfo.setYwm(ywm);//添加一维码
            }

            dept   = deptService.queryByDeptcode(yyinfo.getDeptcode());
            /**
             * 获取该部门下的业务流程信息
             */
            if(null != dept){
                DeptYwlxExample example = new DeptYwlxExample();
                DeptYwlxExample.Criteria ca  = example.createCriteria();
                ca.andDeptcodeEqualTo(dept.getDeptcode());
                ca.andYwflEqualTo(yyinfo.getYwfl());
                ca.andYwlxEqualTo(yyinfo.getYwlx());
                ywlx =deptYwlxService.queryOneByExample(example);


             }

        }
        //待传递到前端对象
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("yyxxinfo",yyinfo);//预约信息
        map.put("deptinfo",dept);//部门信息
        map.put("ywlxinfo",ywlx);//流程信息
        responseDto.setContent(map);
        return responseDto;

    }


    /**
     * 获取预约信息
     * @param wxYyDto
     * @return
     */
    @PostMapping("/queryYyInfo")
    public  ResponseDto queryYyInfo(@RequestBody WxYyDto wxYyDto){
        ResponseDto responseDto = new ResponseDto();
        List<WxYyDto> listdata = new ArrayList<WxYyDto>();
        if(null != wxYyDto){
            if(StringUtils.isNotBlank(wxYyDto.getOpenid())){
                WxYyExample example = new WxYyExample();
                WxYyExample.Criteria ca = example.createCriteria();
                ca.andOpenidEqualTo(wxYyDto.getOpenid());
                if(StringUtils.isNotBlank(wxYyDto.getZt())){
                    ca.andZtEqualTo(wxYyDto.getZt());
                }
                if(StringUtils.isNotBlank(wxYyDto.getZjhm())){
                    ca.andZjhmEqualTo(wxYyDto.getZjhm());
                }
                example.setOrderByClause(" cjsj desc");
                List<WxYyDto> list  =ywyyWxService.selectByExample(example);
                //业务类型解析
                Map<String, Map<String, String>> all =(Map<String, Map<String, String>>)redisTemplate.opsForValue().get(RedisCode.CODESET);
                //部门参数
                Map<String, String> deptmap   = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.DEPTCODENAME);
                if(!CollectionUtils.isEmpty(list)){
                    for(WxYyDto vo : list){
                        Map<String, String> ywflmap = all.get(vo.getYwfl());
                        if(null != ywflmap ){
                            String yelxname =ywflmap.get(vo.getYwlx());
                            vo.setYelxname(yelxname);//业务类型名字
                        }
                        if(null != deptmap){
                            vo.setDeptname(deptmap.get(vo.getDeptcode()));
                        }
                        listdata.add(vo);
                    }

                }

            }
        }
        responseDto.setContent(listdata);
        return responseDto;

    }


    /**
     * 取消预约
     * @param wxYyDto
     * @return
     */
    @PostMapping("/xyyy")
    public  ResponseDto xyyy(@RequestBody WxYyDto wxYyDto){
        ResponseDto responseDto = new ResponseDto();
        int flag = 0;
        if(null != wxYyDto){
            if(StringUtils.isNotBlank(wxYyDto.getId())){
                WxYy vo = new  WxYy();
                vo.setId(wxYyDto.getId());
                vo.setZt("2");//取消
                flag =  ywyyWxService.updateById(vo);
            }

        }
        responseDto.setContent(flag);
        return responseDto;
    }

}

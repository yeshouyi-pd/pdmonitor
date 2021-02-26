package com.pd.system.controller.admin;

import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.DeptYwlxService;
import com.pd.server.main.service.DeptYysjService;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.RedisConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/deptYwlx")
public class DeptYwlxController {

    private static final Logger LOG = LoggerFactory.getLogger(DeptYwlxController.class);
    public static final String BUSINESS_NAME = "部门业务";

    @Resource
    private DeptYwlxService deptYwlxService;//部门预约业务类型
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DeptYysjService deptYysjService;//预约时段


    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DeptYwlxPageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        deptYwlxService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/listall")
    public ResponseDto listall(@RequestBody DeptYwlxPageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        List<DeptYwlxDto> listallbydept=deptYwlxService.listallbydept(pageDto);
        responseDto.setContent(listallbydept);
        return responseDto;
    }



    /**
     * 保存，批量
     */
    @PostMapping("/saveall")
    public ResponseDto saveall(@RequestBody DeptYwlxDto deptYwlxDto) {
// 保存校验
        ValidatorUtil.length(deptYwlxDto.getDeptcode(), "部门编号", 1, 50);
        ValidatorUtil.length(deptYwlxDto.getBslc(), "办事流程", 1, 1000);
        ValidatorUtil.length(deptYwlxDto.getSxzl(), "所需资料", 1, 1000);
        ValidatorUtil.length(deptYwlxDto.getLcto(), "流程图一", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLctt(), "流程图二", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLcth(), "流程图三", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLctf(), "流程图四", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLctfi(), "流程图五", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getBz(), "备注", 1, 300);

        ResponseDto responseDto = new ResponseDto();
        deptYwlxService.saveall(deptYwlxDto);

        responseDto.setContent(deptYwlxDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DeptYwlxDto deptYwlxDto) {
// 保存校验
        ValidatorUtil.length(deptYwlxDto.getDeptcode(), "部门编号", 1, 50);
        ValidatorUtil.length(deptYwlxDto.getYwfl(), "业务分类", 1, 50);
        ValidatorUtil.length(deptYwlxDto.getYwlx(), "业务类型", 1, 50);
        ValidatorUtil.length(deptYwlxDto.getBslc(), "办事流程", 1, 1000);
        ValidatorUtil.length(deptYwlxDto.getSxzl(), "所需资料", 1, 1000);
        ValidatorUtil.length(deptYwlxDto.getLcto(), "流程图一", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLctt(), "流程图二", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLcth(), "流程图三", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLctf(), "流程图四", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getLctfi(), "流程图五", 1, 300);
        ValidatorUtil.length(deptYwlxDto.getBz(), "备注", 1, 300);

        ResponseDto responseDto = new ResponseDto();
        deptYwlxService.save(deptYwlxDto);
        responseDto.setContent(deptYwlxDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        deptYwlxService.delete(id);
        return responseDto;
    }

    /**
     * 根据当前部门获取所有的业务类型
     */
    @PostMapping("/getYwlxByDept")
    public ResponseDto getYwlxByDept(@RequestBody DeptDto deptDto) {
        ResponseDto responseDto = new ResponseDto();
        List<DeptYwlxDto>   list  = deptYwlxService.selectByDept( deptDto.getDeptcode());
        Map<String, List<String> >nmap = new LinkedHashMap<String, List<String>>();
        List<String> jdclist =  new   ArrayList<String >();
        List<String> jsrlist =  new   ArrayList<String >();
        List<String> wflist =  new   ArrayList<String >();
        if(!CollectionUtils.isEmpty(list)){
            for(DeptYwlxDto vo :list ){
                if(CodeType.CODE_JDC_YWLX.equals(vo.getYwfl())){//已经选中的机动车业务
                    jdclist.add(vo.getYwlx());
                }
                if(CodeType.CODE_JSR_YWLX.equals(vo.getYwfl())){//已经选中的驾驶人业务
                    jsrlist.add(vo.getYwlx());
                }
                if(CodeType.CODE_WF_YWLX.equals(vo.getYwfl())){//已经选中的违法业务
                    wflist.add(vo.getYwlx());
                }
            }
        }
        nmap.put("CODE_JDC_YWLX",jdclist);
        nmap.put("CODE_JSR_YWLX",jsrlist);
        nmap.put("CODE_WF_YWLX",wflist);
        responseDto.setContent(nmap);
        return responseDto;
    }

    /**
     * 获取业务分类和业务类型
     */
    @GetMapping("/getywflandyewlx")
    public  ResponseDto getywflandylx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String, Map<String, String>> nmap = new LinkedHashMap<String, Map<String, String>>();
        Map<String, Map<String, String>> all =(Map<String, Map<String, String>>)redisTemplate.opsForValue().get(RedisCode.CODESET);
        nmap.put("CODE_JDC_YWLX",all.get(CodeType.CODE_JDC_YWLX));
        nmap.put("CODE_JSR_YWLX",all.get(CodeType.CODE_JSR_YWLX));
        nmap.put("CODE_WF_YWLX",all.get(CodeType.CODE_WF_YWLX));
        nmap.put("YWZL_CODE",all.get(CodeType.YWZL_CODE));
        responseDto.setContent(nmap);

        return responseDto;
    }

    /**
     * 保存修改时间段
     * @param deptYysjDto
     * @return
     */
    @PostMapping("/addDeptYysj")
    public ResponseDto addDeptYysj(@RequestBody DeptYysjDto deptYysjDto) {
        ResponseDto responseDto = new ResponseDto();
           if(null != deptYysjDto){
               if(StringUtils.isNotBlank(deptYysjDto.getStime()) &&
                       StringUtils.isNotBlank(deptYysjDto.getEtime())){
                   if(null != deptYysjDto.getYymun() && deptYysjDto.getYymun() >0 ){
                       //开始保存
                        deptYysjService.save(deptYysjDto);
                        RedisConfig.init_Yynum();//刷新预约缓存
                   }else{
                       throw  new BusinessException(BusinessExceptionCode.YYTIME_VAILD);
                   }//抛出异常

               }else{
                   throw  new BusinessException(BusinessExceptionCode.YYTIME_VAILD);
               }

           }else{
               throw  new BusinessException(BusinessExceptionCode.YYTIME_VAILD);
    }


        return responseDto;
    }

    /**
     * 根据当前部门查询所有数据
     * @param deptDto
     * @return
     */
    @PostMapping("/queryDeptYysj")
    public ResponseDto queryDeptYysj(@RequestBody DeptDto deptDto) {
        ResponseDto responseDto = new ResponseDto();
        List<DeptYysjDto>  list  =  deptYysjService.queryList(deptDto.getDeptcode());
        responseDto.setContent(list);
        return responseDto;

    }


    /**
     * 删除部门预约时间配置信息
     */
    @DeleteMapping("/deleteDeptYysj/{id}")
    public ResponseDto deleteDeptYysj(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        deptYysjService.delete(id);
        RedisConfig.init_Yynum();//刷新预约缓存
        return responseDto;
    }


}

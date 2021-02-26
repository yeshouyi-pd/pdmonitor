package com.pd.system.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.Dept;
import com.pd.server.main.domain.DeptExample;
import com.pd.server.main.dto.DeptDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DeptService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.BaseController;
import com.pd.system.controller.conf.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/dept")
public class DeptController extends BaseController {

private static final Logger LOG = LoggerFactory.getLogger(DeptController.class);
public static final String BUSINESS_NAME = "部门";

@Resource
private DeptService deptService;

    @Resource
    private RedisTemplate redisTemplate;




    /**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody DeptDto deptDto) {
ResponseDto responseDto = new ResponseDto();

    DeptExample deptExample = new DeptExample();
    DeptExample.Criteria ca = deptExample.createCriteria();
    if(!StringUtils.isEmpty(deptDto.getUpcode())){
        //ca.andUpcodeIn(getUpdeptcode(deptDto.getUpcode()));
        ca.andUpcodeEqualTo(deptDto.getUpcode());
    }else{//根据当前用户获取当前部门以及下级部门
        ca.andUpcodeIn(getUpdeptcode(""));
    }
    if(!StringUtils.isEmpty(deptDto.getDeptname())){
        ca.andDeptnameLike("%"+deptDto.getDeptname()+"%");
    }
    if(!StringUtils.isEmpty(deptDto.getDeptcode())){
        ca.andDeptcodeEqualTo(deptDto.getDeptcode());
    }
    if(!StringUtils.isEmpty(deptDto.getStatus())){
        ca.andStatusEqualTo(deptDto.getStatus());
    }
    if(!StringUtils.isEmpty(deptDto.getDepttype())){
        ca.andDepttypeEqualTo(deptDto.getDepttype());
    }
    PageHelper.startPage(deptDto.getPage(), deptDto.getSize());
    List<Dept> deptList =deptService.list(deptExample);
    PageInfo<Dept> pageInfo = new PageInfo<>(deptList);
    deptDto.setTotal(pageInfo.getTotal());
    List<DeptDto> deptDtoList = CopyUtil.copyList(deptList, DeptDto.class);
    deptDto.setList(deptDtoList);
     responseDto.setContent(deptDto);
     return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody DeptDto deptDto) {
// 保存校验
            ValidatorUtil.require(deptDto.getUpcode(), "上级部门");
            ValidatorUtil.require(deptDto.getDeptcode(), "部门编号");
            ValidatorUtil.length(deptDto.getDeptcode(), "部门编号", 1, 36);
            ValidatorUtil.require(deptDto.getDeptname(), "部门名称");
            ValidatorUtil.length(deptDto.getDeptname(), "部门名称", 1, 100);
            ValidatorUtil.length(deptDto.getUpcode(), "上级部门", 1, 100);
            ValidatorUtil.length(deptDto.getDeptdesc(), "描述", 1, 200);
            ValidatorUtil.length(deptDto.getLinkadd(), "部门地址", 1, 200);
            ValidatorUtil.length(deptDto.getLinktel(), "部门电话", 1, 50);
            ValidatorUtil.length(deptDto.getLinkman(), "联系人", 1, 100);
            ValidatorUtil.require(deptDto.getJd(), "经度");
            ValidatorUtil.require(deptDto.getWd(), "纬度");
            ValidatorUtil.require(deptDto.getGryymax(), "个人每日最大预约数");
            ValidatorUtil.require(deptDto.getQyyymax(), "企业每日最大预约数");
            ValidatorUtil.require(deptDto.getDepttype(), "机构类别");
            ValidatorUtil.require(deptDto.getMaxday(), "最大提前预约天数");

    ResponseDto responseDto = new ResponseDto();
    deptDto.setCreatetime(new Date());//创建时间
    deptDto.setUpdatetime(new Date());//修改时间
    LoginUserDto user    =getRequestHeader();
    deptDto.setCreateman(user.getLoginName());
    deptDto.setUpdateman(user.getLoginName());

    deptService.save(deptDto);
    RedisConfig.init_dept();//刷新部门缓存
responseDto.setContent(deptDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
deptService.delete(id);
    RedisConfig.init_dept();//刷新部门缓存
return responseDto;
}


    /**
     * 资源树查询
     */
    @GetMapping("/load-deptTree")
    public ResponseDto loadTree() {
        ResponseDto responseDto = new ResponseDto();
        List<String> deptcodestr =  getUpdeptcode("");
        List<DeptDto>deptDtoList = deptService.loadTree(deptcodestr);
        responseDto.setContent(deptDtoList);
        return responseDto;
    }

    /**
     * 资源树查询
     */
    @GetMapping("/getAllDept")
    public ResponseDto getAllDept() {
        ResponseDto responseDto = new ResponseDto();
        List<DeptDto>deptDtoList = deptService.getAllDept();
        responseDto.setContent(deptDtoList);
        return responseDto;
    }

    /**
     * 获取所有业务类型
     */
    @GetMapping("/getAllYwlx")
    public ResponseDto getAllYwlx() {
        ResponseDto responseDto = new ResponseDto();
        Map<String, Map<String, String>> nmap = new LinkedHashMap<String, Map<String, String>>();
        Map<String, Map<String, String>> all =(Map<String, Map<String, String>>)redisTemplate.opsForValue().get(RedisCode.CODESET);
        nmap.put("CODE_JDC_YWLX",all.get(CodeType.CODE_JDC_YWLX));
        nmap.put("CODE_JSR_YWLX",all.get(CodeType.CODE_JSR_YWLX));
        nmap.put("CODE_WF_YWLX",all.get(CodeType.CODE_WF_YWLX));
        nmap.put("CODE_XX_DEPTTYPE",all.get(CodeType.CODE_XX_DEPTTYPE));
        responseDto.setContent(nmap);
        return responseDto;
    }


}

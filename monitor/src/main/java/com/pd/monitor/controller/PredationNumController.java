package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.PredationNum;
import com.pd.server.main.domain.PredationNumExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PredationNumDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.PredationNumService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/predationNum")
public class PredationNumController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(PredationNumController.class);
    public static final String BUSINESS_NAME = "捕食次数统计";

    @Resource
    private PredationNumService predationNumService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PredationNumDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<String> deptList = getUpdeptcode(userDto.getDeptcode());
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        PredationNumExample example = new PredationNumExample();
        PredationNumExample.Criteria ca = example.createCriteria();
        if(!CollectionUtils.isEmpty(deptList)){
            ca.andDeptcodeIn(deptList);
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(userDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(userDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime(),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime(),"%Y-%m-%d");
        }
        example.setOrderByClause(" cjsj desc ");
        List<PredationNum> predationNumList = predationNumService.list(example);
        PageInfo<PredationNum> pageInfo = new PageInfo<>(predationNumList);
        pageDto.setTotal(pageInfo.getTotal());
        List<PredationNumDto> predationNumDtoList = CopyUtil.copyList(predationNumList, PredationNumDto.class);
        pageDto.setList(predationNumDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody PredationNumDto predationNumDto) {
        // 保存校验
                ValidatorUtil.length(predationNumDto.getSbbh(), "设备编号", 1, 255);
                ValidatorUtil.length(predationNumDto.getDeptcode(), "部门编号", 1, 255);
                ValidatorUtil.length(predationNumDto.getXmbh(), "项目编号", 1, 255);
                ValidatorUtil.length(predationNumDto.getSm1(), "", 1, 500);
                ValidatorUtil.length(predationNumDto.getSm2(), "", 1, 500);
                ValidatorUtil.length(predationNumDto.getSm3(), "", 1, 500);
                ValidatorUtil.length(predationNumDto.getSm4(), "", 1, 500);
                ValidatorUtil.length(predationNumDto.getSm5(), "", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        predationNumService.save(predationNumDto);
        responseDto.setContent(predationNumDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        predationNumService.delete(id);
        return responseDto;
    }

}

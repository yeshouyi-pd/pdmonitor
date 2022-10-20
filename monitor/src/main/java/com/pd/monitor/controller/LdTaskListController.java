package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.LdTaskList;
import com.pd.server.main.domain.LdTaskListExample;
import com.pd.server.main.dto.LdTaskListDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AttrService;
import com.pd.server.main.service.LdTaskListService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/ldTaskList")
public class LdTaskListController {

    private static final Logger LOG = LoggerFactory.getLogger(LdTaskListController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private LdTaskListService ldTaskListService;
    @Resource
    private AttrService attrService;

    @PostMapping("/restart/{sbcj}")
    public ResponseDto restart(@PathVariable String sbcj){
        ResponseDto responseDto = new ResponseDto();
        String restartinterval = attrService.findByAttrKey("restartinterval");
        try {
            LdTaskListDto dto = new LdTaskListDto();
            dto.setIccid(sbcj);
            dto.setTask("cmd:203");
            dto.setFsdate(new Date());
            ldTaskListService.save(dto);
            Thread.sleep(Long.parseLong(restartinterval));
            dto.setIccid(sbcj);
            dto.setTask("cmd:202");
            dto.setFsdate(new Date());
            ldTaskListService.save(dto);
            return responseDto;
        }catch (Exception e){
            responseDto.setSuccess(false);
            return responseDto;
        }
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody LdTaskListDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        LdTaskListExample ldTaskListExample = new LdTaskListExample();
        LdTaskListExample.Criteria ca = ldTaskListExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getState())){
            ca.andStateEqualTo(pageDto.getState());
        }
        if(!StringUtils.isEmpty(pageDto.getIccid())){
            ca.andIccidEqualTo(pageDto.getIccid());
        }
        ldTaskListExample.setOrderByClause(" fsdate desc ");
        List<LdTaskList> ldTaskListList = ldTaskListService.list(ldTaskListExample);
        PageInfo<LdTaskList> pageInfo = new PageInfo<>(ldTaskListList);
        pageDto.setTotal(pageInfo.getTotal());
        List<LdTaskListDto> ldTaskListDtoList = CopyUtil.copyList(ldTaskListList, LdTaskListDto.class);
        pageDto.setList(ldTaskListDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody LdTaskListDto ldTaskListDto) {
        ResponseDto responseDto = new ResponseDto();
        ldTaskListDto.setFsdate(new Date());
        ldTaskListService.save(ldTaskListDto);
        responseDto.setContent(ldTaskListDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        ldTaskListService.delete(id);
        return responseDto;
    }

}

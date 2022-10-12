package com.pd.monitor.controller;

import com.pd.server.main.dto.LdTaskListDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.LdTaskListService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/admin/ldTaskList")
public class LdTaskListController {

    private static final Logger LOG = LoggerFactory.getLogger(LdTaskListController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private LdTaskListService ldTaskListService;

    @PostMapping("/restart/{sbcj}")
    public ResponseDto restart(@PathVariable String sbcj){
        ResponseDto responseDto = new ResponseDto();
        try {
            LdTaskListDto dto = new LdTaskListDto();
            dto.setIccid(sbcj);
            dto.setTask("cmd:203");
            dto.setFsdate(new Date());
            ldTaskListService.save(dto);
            Thread.sleep(3000);
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
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        ldTaskListService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody LdTaskListDto ldTaskListDto) {
        ResponseDto responseDto = new ResponseDto();
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

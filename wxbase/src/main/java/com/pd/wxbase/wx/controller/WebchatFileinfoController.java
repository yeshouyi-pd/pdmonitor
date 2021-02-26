package com.pd.wxbase.wx.controller;

import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/webchatFileinfo")
public class WebchatFileinfoController {

private static final Logger LOG = LoggerFactory.getLogger(WebchatFileinfoController.class);
public static final String BUSINESS_NAME = "微信照片文件存储表";

@Resource
private WebchatFileinfoService webchatFileinfoService;


/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();
webchatFileinfoService.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

    /**
     * 列表查询
     */
    @GetMapping("/getFileinfoByLsh/{lsh}")
    public ResponseDto getFileinfoByLsh(@PathVariable String lsh) {
        ResponseDto responseDto = new ResponseDto();
        List<WebchatFileinfo> webchatFileinfoList = webchatFileinfoService.getFileinfoByLsh(lsh);
        responseDto.setContent(webchatFileinfoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @GetMapping("/getFileinfoByLsh/{lsh}/{zplx}")
    public ResponseDto getFileinfoByLsh(@PathVariable String lsh, @PathVariable String zplx) {
        ResponseDto responseDto = new ResponseDto();
        List<WebchatFileinfo> webchatFileinfoList = webchatFileinfoService.getFileinfoByLshAndZplx(lsh, zplx);
        responseDto.setContent(webchatFileinfoList);
        return responseDto;
    }

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody WebchatFileinfoDto webchatFileinfoDto) {
// 保存校验
            ValidatorUtil.require(webchatFileinfoDto.getZpzl(), "照片代码");
            ValidatorUtil.length(webchatFileinfoDto.getZpzl(), "照片代码", 1, 20);
            ValidatorUtil.require(webchatFileinfoDto.getZplj(), "照片路径");
            ValidatorUtil.length(webchatFileinfoDto.getZplj(), "照片路径", 1, 512);
            ValidatorUtil.require(webchatFileinfoDto.getLsh(), "流水号");
            ValidatorUtil.length(webchatFileinfoDto.getLsh(), "流水号", 1, 36);
            ValidatorUtil.require(webchatFileinfoDto.getBz(), "备注");
            ValidatorUtil.length(webchatFileinfoDto.getBz(), "备注", 1, 450);
            ValidatorUtil.require(webchatFileinfoDto.getCjsj(), "采集时间");

ResponseDto responseDto = new ResponseDto();
webchatFileinfoService.save(webchatFileinfoDto);
responseDto.setContent(webchatFileinfoDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
webchatFileinfoService.delete(id);
return responseDto;
}
}

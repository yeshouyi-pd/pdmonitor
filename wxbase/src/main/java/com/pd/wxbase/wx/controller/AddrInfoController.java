package com.pd.wxbase.wx.controller;

import com.pd.server.main.domain.AddrInfo;
import com.pd.server.main.dto.AddrInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AddrInfoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/addrInfo")
public class AddrInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(AddrInfoController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AddrInfoService addrInfoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        addrInfoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AddrInfoDto addrInfoDto) {
        // 保存校验
            ValidatorUtil.length(addrInfoDto.getCode(), "地址编码", 1, 255);
            ValidatorUtil.length(addrInfoDto.getName(), "地址名称", 1, 255);
            ValidatorUtil.length(addrInfoDto.getPcode(), "上一级地址编码", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        addrInfoService.save(addrInfoDto);
        responseDto.setContent(addrInfoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        addrInfoService.delete(id);
        return responseDto;
    }

    /**
     * 查询所有
     */
    @GetMapping("/findListAll")
    public ResponseDto findListAll() {
        ResponseDto responseDto = new ResponseDto();
        List<AddrInfo> addrInfoList = addrInfoService.findListAll();
        responseDto.setContent(addrInfoList);
        return responseDto;
    }
}

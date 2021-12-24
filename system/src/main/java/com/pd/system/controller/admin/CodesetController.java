package com.pd.system.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.Codeset;
import com.pd.server.main.domain.CodesetExample;
import com.pd.server.main.dto.CodesetDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.CodesetService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/codeset")
public class CodesetController {

    private static final Logger LOG = LoggerFactory.getLogger(CodesetController.class);
    public static final String BUSINESS_NAME = "代码维护";

    @Resource
    private CodesetService codesetService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CodesetDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            CodesetExample codesetExample = new CodesetExample();
            CodesetExample.Criteria ca = codesetExample.createCriteria();
            ca.andTypeNotEqualTo("alltype");

            if (!StringUtils.isEmpty(pageDto.getType())){
                ca.andTypeEqualTo(pageDto.getType());
            }
            if (!StringUtils.isEmpty(pageDto.getCode())){

                ca.andCodeEqualTo(pageDto.getCode());
            }
            if (!StringUtils.isEmpty(pageDto.getName())){
                ca.andNameEqualTo(pageDto.getName());
            }
            codesetExample.setOrderByClause(" type , code");
            List<Codeset> codesetList = codesetService.list(codesetExample);
            PageInfo<Codeset> pageInfo = new PageInfo<>(codesetList);
            pageDto.setTotal(pageInfo.getTotal());
            List<CodesetDto> codesetDtoList = CopyUtil.copyList(codesetList, CodesetDto.class);
            pageDto.setList(codesetDtoList);
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
    public ResponseDto save(@RequestBody CodesetDto codesetDto) {
        // 保存校验
            ValidatorUtil.length(codesetDto.getCode(), "code值", 1, 100);
            ValidatorUtil.length(codesetDto.getName(), "名称", 1, 100);
            ValidatorUtil.length(codesetDto.getType(), "代码类别", 1, 100);
            ValidatorUtil.length(codesetDto.getContent(), "描述", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        codesetService.save(codesetDto);
        RedisConfig.init_code();//刷新code 缓存
        responseDto.setContent(codesetDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        codesetService.delete(id);
        RedisConfig.init_code();//刷新code 缓存
        return responseDto;
    }

    /**
     * 获取所有种类
     * @return
     */
    @GetMapping("/getAlltype")
    public ResponseDto getAlltype(){
        ResponseDto responseDto = new ResponseDto();
        List<CodesetDto> codesetDtoList  =  codesetService.getAlltype();
        responseDto.setContent(codesetDtoList);
        return responseDto;
    }

}

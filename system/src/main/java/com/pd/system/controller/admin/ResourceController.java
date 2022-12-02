package com.pd.system.controller.admin;

import com.pd.server.main.domain.ResourceExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.ResourceService;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);
    public static final String BUSINESS_NAME = "资源";

    @Resource
    private ResourceService resourceService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        resourceService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody String jsonStr) {
        // 保存校验
        ValidatorUtil.require(jsonStr, "资源");

        ResponseDto responseDto = new ResponseDto();
        resourceService.saveJson(jsonStr);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        resourceService.delete(id);
        return responseDto;
    }

    /**
     * 资源树查询
     */
    @GetMapping("/load-tree")
    public ResponseDto loadTree() {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<ResourceDto> resourceDtoList = resourceService.loadTree(userDto);
        responseDto.setContent(resourceDtoList);
        return responseDto;
    }

    /**
     * 资源树查询
     */
    @GetMapping("/load-tree-simple")
    public ResponseDto loadSimpleTree() {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        List<ResourceDto> resourceDtoList = resourceService.loadSimpleTree(userDto);
        responseDto.setContent(resourceDtoList);
        return responseDto;
    }


    /**
     * 查询所有菜单
     * @return
     */
    @PostMapping("/getAllRes")
    public ResponseDto getAllRes(@RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();

        List<ResourceDto> resourceDtoList = resourceService.selectAll(userDto);
        responseDto.setContent(resourceDtoList);
        return responseDto;
    }

    /**
     * 根据条件获取下级菜单
     * @return
     */
    @PostMapping("/getResById")
    public ResponseDto getResById(@RequestBody ResourceDto resourceDto) {
        ResponseDto responseDto = new ResponseDto();
        List<ResourceDto> resourceDtoList = new ArrayList<ResourceDto>();

        if(null !=resourceDto &&!StringUtils.isEmpty(resourceDto.getId())){
            List<String>  rescode = resourceService.getUpRescode(resourceDto.getId());
            if(!CollectionUtils.isEmpty(rescode)){
                ResourceExample example = new ResourceExample();
                ResourceExample.Criteria ca = example.createCriteria();
                ca.andIdIn(rescode);
                resourceDtoList = resourceService.selectexample(example);
            }
        }
        responseDto.setContent(resourceDtoList);
        return responseDto;
    }


}

package com.pd.system.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.SysLog;
import com.pd.server.main.domain.SysLogExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.SysLogDto;
import com.pd.server.main.service.SysLogService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/sysLog")
public class SysLogController {

    private static final Logger LOG = LoggerFactory.getLogger(SysLogController.class);
    public static final String BUSINESS_NAME = "系统日志";

    @Resource
    private SysLogService sysLogService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SysLogDto sysLogDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(sysLogDto.getPage(), sysLogDto.getSize());
        SysLogExample sysLogExample = new SysLogExample();
        SysLogExample.Criteria ca = sysLogExample.createCriteria();
        if(null != sysLogDto){
            if(!StringUtils.isEmpty(sysLogDto.getCzr())){
                ca.andCzrLike("%"+sysLogDto.getCzr().trim()+"%");
            }
            if(!StringUtils.isEmpty(sysLogDto.getCzcd())){
                ca.andCzcdLike("%"+sysLogDto.getCzcd().trim()+"%");
            }
            if(!StringUtils.isEmpty(sysLogDto.getRzlx())){
               ca.andRzlxEqualTo(sysLogDto.getRzlx());
            }
            if(!StringUtils.isEmpty(sysLogDto.getCzjg())){
                ca.andCzjgEqualTo(sysLogDto.getCzjg());
            }
            if(!StringUtils.isEmpty(sysLogDto.getStime())){
                ca.andCzsjGreaterThanOrEqualTo(sysLogDto.getStime());
            }
            if(!StringUtils.isEmpty(sysLogDto.getEtime())){
                ca.andCzsjLessThanOrEqualTo(sysLogDto.getEtime());
            }
        }
        sysLogExample.setOrderByClause( "czsj desc ");

        List<SysLog> sysLogList = sysLogService.list(sysLogExample);
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogList);
        sysLogDto.setTotal(pageInfo.getTotal());
        List<SysLogDto> sysLogDtoList = CopyUtil.copyList(sysLogList, SysLogDto.class);
        sysLogDto.setList(sysLogDtoList);
        responseDto.setContent(sysLogDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SysLogDto sysLogDto) {
     // 保存校验
        ValidatorUtil.length(sysLogDto.getCzr(), "操作人", 1, 100);
        ValidatorUtil.length(sysLogDto.getCzip(), "操作ip", 1, 100);
        ValidatorUtil.length(sysLogDto.getCzcd(), "操作菜单", 1, 100);
        ValidatorUtil.length(sysLogDto.getCzsm(), "操作说明", 1, 100);
        ValidatorUtil.length(sysLogDto.getCzsbyy(), "操作失败原因", 1, 500);
        ValidatorUtil.length(sysLogDto.getCztj(), "操作条件", 1, 100);
        ValidatorUtil.length(sysLogDto.getQqsj(), "请求数据", 1, 4000);
        ValidatorUtil.length(sysLogDto.getFfsj(), "返回数据", 1, 4000);
        ValidatorUtil.length(sysLogDto.getJyw(), "校验位", 1, 100);
        ResponseDto responseDto = new ResponseDto();
        sysLogService.save(sysLogDto);
        responseDto.setContent(sysLogDto);
        return responseDto;
    }


}

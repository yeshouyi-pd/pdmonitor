package com.pd.system.controller.app;

import com.pd.server.main.dto.AppMonitorExpDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppMonitorExpService;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/appMonitorExp")
public class AppMonitorExpController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AppMonitorExpController.class);
    public static final String BUSINESS_NAME = "导出记录";

    @Resource
    private AppMonitorExpService appMonitorExpService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody AppMonitorExpDto appMonitorExpDto) {
        ResponseDto responseDto = new ResponseDto();
        PageDto pageDto = appMonitorExpService.list(appMonitorExpDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppMonitorExpDto appMonitorExpDto) {
        // 保存校验
                ValidatorUtil.length(appMonitorExpDto.getDate1(), "年月日 20250903", 1, 10);
                ValidatorUtil.length(appMonitorExpDto.getTime1(), "时分 0826", 1, 10);
                ValidatorUtil.length(appMonitorExpDto.getDeclat(), "经度", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getDeclong(), "纬度", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getEvents(), "事件", 1, 10);
                ValidatorUtil.length(appMonitorExpDto.getData1(), "数据1", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData2(), "数据2", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData3(), "数据3", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData4(), "数据4", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData5(), "数据5", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData6(), "数据6", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData7(), "数据7", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData8(), "数据8", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData9(), "", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData10(), "", 1, 100);
                ValidatorUtil.length(appMonitorExpDto.getData11(), "", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        appMonitorExpService.save(appMonitorExpDto);
        responseDto.setContent(appMonitorExpDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appMonitorExpService.delete(id);
        return responseDto;
    }

}

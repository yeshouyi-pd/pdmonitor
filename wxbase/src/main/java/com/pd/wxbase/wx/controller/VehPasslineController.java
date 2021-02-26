package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehPassline;
import com.pd.server.main.domain.VehPasslineExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehPasslineDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VehPasslineService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/vehPassline")
public class VehPasslineController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(VehPasslineController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private VehPasslineService vehPasslineService;

    /**
     * 获取线路
     *
     * @return
     */
    @GetMapping("/getPassLine")
    public ResponseDto getPassLine() {
        ResponseDto responseDto = new ResponseDto();
        Map<String, VehPassline> map = new LinkedHashMap<String, VehPassline>();
        List<VehPassline> list = vehPasslineService.getVehPassline();
        List<Map> listPassLine = new ArrayList<>();
        for (VehPassline vehPassline : list) {
            Map vehPasslineMap = new HashMap();
            vehPasslineMap.put(vehPassline.getQx(), vehPassline.getJddz());
            listPassLine.add(vehPasslineMap);
        }
        map = mapCombine(listPassLine);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取区县下的值
     *
     * @param list
     * @return
     */
    public static Map mapCombine(List<Map> list) {
        Map<Object, List> map = new HashMap<>();
        for (Map m : list) {
            Iterator<Object> it = m.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                if (!map.containsKey(key)) {
                    List newList = new ArrayList<>();
                    newList.add(m.get(key));
                    map.put(key, newList);
                } else {
                    map.get(key).add(m.get(key));
                }
            }
        }
        return map;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VehPasslineDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            VehPasslineExample vehPasslineExample = new VehPasslineExample();
            VehPasslineExample.Criteria ca = vehPasslineExample.createCriteria();
            if (!StringUtils.isEmpty(pageDto.getZt())) {
                ca.andZtEqualTo(pageDto.getZt());
            }
            if (!StringUtils.isEmpty(pageDto.getQx())) {
                ca.andQxEqualTo(pageDto.getQx());
            }

            if (!StringUtils.isEmpty(pageDto.getJddz())) {
                ca.andJddzLike("%" + pageDto.getJddz() + "%");
            }

            List<VehPassline> vehPasslineList = vehPasslineService.list(vehPasslineExample);
            PageInfo<VehPassline> pageInfo = new PageInfo<>(vehPasslineList);
            pageDto.setTotal(pageInfo.getTotal());
            List<VehPasslineDto> vehPasslineDtoList = CopyUtil.copyList(vehPasslineList, VehPasslineDto.class);
            pageDto.setList(vehPasslineDtoList);
            responseDto.setContent(pageDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody VehPasslineDto vehPasslineDto) {
// 保存校验
        ValidatorUtil.require(vehPasslineDto.getQx(), "区县");
        ValidatorUtil.length(vehPasslineDto.getQx(), "区县", 1, 128);
        ValidatorUtil.length(vehPasslineDto.getJddz(), "线路名称", 1, 256);
            ValidatorUtil.require(vehPasslineDto.getJddz(), "线路名称");
            ValidatorUtil.length(vehPasslineDto.getMs(), "描述", 1, 256);
            ValidatorUtil.length(vehPasslineDto.getBz(), "备注", 1, 2000);
            ValidatorUtil.length(vehPasslineDto.getCreateBy(), "创建人", 1, 256);
            ValidatorUtil.length(vehPasslineDto.getUpdateBy(), "更新人", 1, 256);
            ValidatorUtil.length(vehPasslineDto.getSysOrgCode(), "创建部门", 1, 64);
            ValidatorUtil.length(vehPasslineDto.getJddm(), "线路代码", 1, 64);
            ValidatorUtil.require(vehPasslineDto.getZt(), "状态");
            ValidatorUtil.length(vehPasslineDto.getZt(), "状态", 1, 1);

ResponseDto responseDto = new ResponseDto();
    LoginUserDto loginUserDto   = getRequestHeader();
    if (StringUtils.isEmpty(vehPasslineDto.getId())) {
        vehPasslineDto.setSysOrgCode(loginUserDto.getDeptcode());
        vehPasslineDto.setCreateBy(loginUserDto.getLoginName());
        vehPasslineDto.setCreateTime(new Date());
    }
    vehPasslineDto.setUpdateBy(loginUserDto.getLoginName());
    vehPasslineDto.setUpdateTime(new Date());

vehPasslineService.save(vehPasslineDto);
responseDto.setContent(vehPasslineDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
vehPasslineService.delete(id);
return responseDto;
}
}

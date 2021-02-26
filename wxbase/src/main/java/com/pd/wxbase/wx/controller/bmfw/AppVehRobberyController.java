package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.config.CodeType;
import com.pd.server.main.domain.VehRobbery;
import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehRobberyDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.VehRobberyService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/vehRobbery")
public class AppVehRobberyController {

    private static final Logger LOG = LoggerFactory.getLogger(AppVehRobberyController.class);
    public static final String BUSINESS_NAME = "套牌车报案";

    @Resource
    private VehRobberyService vehRobberyService;

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(@RequestBody VehRobberyDto vehRobberyDto) throws Exception {
        // 保存校验
        ValidatorUtil.length(vehRobberyDto.getCreateBy(), "创建人", 1, 50);
        ValidatorUtil.length(vehRobberyDto.getUpdateBy(), "更新人", 1, 50);
        ValidatorUtil.length(vehRobberyDto.getSysOrgCode(), "所属部门", 1, 64);
        ValidatorUtil.length(vehRobberyDto.getXm(), "报案人", 1, 128);
        ValidatorUtil.length(vehRobberyDto.getSfzmhm(), "身份证明号码", 1, 20);
        ValidatorUtil.length(vehRobberyDto.getLxdh(), "联系电话", 1, 20);
        ValidatorUtil.length(vehRobberyDto.getJtzz(), "家庭住址", 1, 512);
        ValidatorUtil.length(vehRobberyDto.getHphm(), "被套车牌", 1, 20);
        ValidatorUtil.length(vehRobberyDto.getClpp(), "被套车品牌", 1, 256);
        ValidatorUtil.length(vehRobberyDto.getZj(), "车辆被套牌证据", 1, 256);
        ValidatorUtil.length(vehRobberyDto.getQksm(), "情况说明", 1, 3000);
        ValidatorUtil.length(vehRobberyDto.getBz(), "备注", 1, 4000);
        ValidatorUtil.length(vehRobberyDto.getZt(), "状态", 1, 2);
        ValidatorUtil.length(vehRobberyDto.getOpenid(), "微信用户id", 1, 128);
        ValidatorUtil.length(vehRobberyDto.getLsh(), "流水号", 1, 45);
        ValidatorUtil.length(vehRobberyDto.getShr(), "审核人", 1, 128);
        ValidatorUtil.length(vehRobberyDto.getShsm(), "审核说明", 1, 500);
        ValidatorUtil.length(vehRobberyDto.getLb(), "车辆类别1个人车辆2单位车辆", 1, 1);
        ValidatorUtil.length(vehRobberyDto.getHpzl(), "号牌种类", 1, 2);

        ResponseDto responseDto = new ResponseDto();
        vehRobberyDto.setLsh(OrderUtils.getOrderNo());
        vehRobberyDto.setSfzmhm(DesEncrypt.DesJiaMi(vehRobberyDto.getSfzmhm()));
        vehRobberyDto.setLxdh(DesEncrypt.DesJiaMi(vehRobberyDto.getLxdh()));
        vehRobberyDto.setCreateBy(vehRobberyDto.getXm());
        vehRobberyDto.setZt("1");//1已上报 2已处理
        vehRobberyDto.setCreateTime(new Date());
        String id = vehRobberyService.save(vehRobberyDto);
        vehRobberyDto.setId(id);
        responseDto.setContent(vehRobberyDto);
        return responseDto;
    }

    @PostMapping("/wxsavepic")
    public ResponseDto wxsavepic(@RequestParam(value = "id") String id, @RequestParam(value = "barlist") List<String> barlist, @RequestParam(value = "cllist") List<String> cllist){
        ResponseDto responseDto = new ResponseDto();
        VehRobberyDto vehRobberyDto = vehRobberyService.findByID(id);
        if(null == vehRobberyDto){
            throw new WxStrException("请按照流程进行上报");
        }
        List<WebchatFileinfo> list = webchatFileinfoService.getFileinfoByLsh(vehRobberyDto.getLsh());
        if(null != list && list.size() > 0){
            throw new WxStrException("您已经上报成功，请勿重复提交照片。");
        }
        for (int i = 0; i < barlist.size(); i++) {
            WebchatFileinfoDto file = new WebchatFileinfoDto();
            String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
            String filename = vehRobberyDto.getLsh() + "_" + new Date().getTime() + ".jpg";
            String[] s = barlist.get(i).split("_");
            Base64Image.GenerateImage(ResizeImg.resizeImage(s[1]), fileDriectory + filename);
            file.setZpzl(CodeType.CODE_XX_TBCSBBAR);
            file.setLsh(vehRobberyDto.getLsh());
            file.setZplj(WxRedisConfig.fileStr + filename);
            file.setCjsj(new Date());
            file.setZplx(s[0]);
            webchatFileinfoService.save(file);
        }
        for (int j = 0; j < cllist.size(); j++) {
            WebchatFileinfoDto file = new WebchatFileinfoDto();
            String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
            String filename = vehRobberyDto.getLsh() + "_" + new Date().getTime() + ".jpg";
            String[] s = cllist.get(j).split("_");
            Base64Image.GenerateImage(ResizeImg.resizeImage(s[1]), fileDriectory + filename);
            file.setZpzl(CodeType.CODE_XX_TBCSBCL);
            file.setLsh(vehRobberyDto.getLsh());
            file.setZplj(WxRedisConfig.fileStr + filename);
            file.setCjsj(new Date());
            file.setZplx(s[0]);
            webchatFileinfoService.save(file);
        }
        vehRobberyService.save(vehRobberyDto);
        return responseDto;
    }

    /**
     * 根据身份证明号码查询
     * @param sfzmhm
     * @return
     */
    @PostMapping("/getRobbery")
    public ResponseDto getRobbery(@RequestParam(value = "sfzmhm") String sfzmhm) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        List<VehRobbery> list = vehRobberyService.findBySfzmhm(DesEncrypt.DesJiaMi(sfzmhm));
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 根据身份证明号码查询
     * @param id
     * @return
     */
    @PostMapping("/selectinfo")
    public ResponseDto selectinfo(@RequestParam(value = "id") String id){
        ResponseDto responseDto = new ResponseDto();
        VehRobberyDto info = vehRobberyService.findByID(id);
        List<WebchatFileinfo> piclist = new LinkedList<WebchatFileinfo>();
        if(!StringUtils.isEmpty(info.getLsh())){
            piclist = webchatFileinfoService.getFileinfoByLsh(info.getLsh());
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("info",info);
        map.put("piclist", piclist);
        responseDto.setContent(map);
        return responseDto;
    }


}

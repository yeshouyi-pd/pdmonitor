package com.pd.wxbase.wx.controller.lstxz;


import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.VehPass;
import com.pd.server.main.domain.VehPassExample;
import com.pd.server.main.dto.KvMapDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehPassDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.service.VehPassService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.UuidUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/lstxz")
public class LstxzWxController  extends BaseWxController {


    public static final String BUSINESS_NAME = "临时通行证【微信端】";


    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    @Resource
    private VehPassService vehPassService;


    /**
     * 获取通行证业务类型
     * @return
     */
    @GetMapping("/gettzlywlx")
    public ResponseDto getZjlx(){
        ResponseDto responseDto = new ResponseDto();
        /**
         * 通行证临时号牌类型
         */
        List<KvMapDto> hpzllist  = getzljx(CodeType.CODE_XX_TZXLSHP);
        /**
         * 通行证类型
         */
        List<KvMapDto> txzlist = getzljx(CodeType.CODE_XX_TXZLX);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("hpzllist",hpzllist);
        map.put("txzlist",txzlist);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 通行证待拍资料
     * @return
     */
    @GetMapping("/gettxzdpzl")
    public ResponseDto gettxzdpzl(){
        ResponseDto responseDto = new ResponseDto();
        List<Map<String,Object>> list =getuplodzljx(CodeType.CODE_XX_TXZDCZL);
        responseDto.setContent(list);
        return responseDto;
    }


    /**
     *
     * @param file 图片对象
     * @param use  用途 业务类型
     * @param zplx 照片类型 大类
     * @param zpzl 照片类型 小类
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadpic")
    public ResponseDto uploadpic(@RequestParam MultipartFile file, String use, String zplx , String zpzl,String mid) throws IOException {
        // 保存文件到本地
        String key = UuidUtil.getShortUuid();
        String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
        String basePath = picStorePath +use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM");
        File filepath = new File(basePath);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        String fullPath = basePath+File.separator+key+".jpg";
        File dest = new File(fullPath);
        file.transferTo(dest);//写入当本地磁盘
        WebchatFileinfoDto fildto = new WebchatFileinfoDto();
        fildto.setCjsj(new Date());
        fildto.setLsh(mid);
        fildto.setZpzl(zpzl);
        fildto.setZplx(zplx);
        String readpath = "/system/f/"+use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM")+File.separator+key+".jpg";
        fildto.setZplj(readpath);
        webchatFileinfoService.save(fildto);
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }

    /**
     * 保存基本信息
     * @return
     */
    @PostMapping("/savetxz")
    public ResponseDto savetxz(@RequestBody VehPassDto vehPassDto){
        ResponseDto responseDto = new ResponseDto();
        vehPassDto.setCreateTime(new Date());
        vehPassDto.setZt("1");
        vehPassService.add(vehPassDto);
        return responseDto;
    }

    /**
     * 通行证查询
     * @param vehPassDto
     * @return
     */
    @PostMapping("/queryTxz")
    public ResponseDto queryTxz(@RequestBody VehPassDto vehPassDto){
        ResponseDto responseDto = new ResponseDto();
        if(null != vehPassDto){
            if(!StringUtils.isEmpty(vehPassDto.getOpenid())){
                VehPassExample example = new VehPassExample();
                VehPassExample.Criteria ca = example.createCriteria();
                ca.andOpenidEqualTo(vehPassDto.getOpenid());
                example.setOrderByClause(" create_time asc ");
                List<VehPass> list = vehPassService.list(example);
                List<VehPassDto> listDto = CopyUtil.copyList(list,VehPassDto.class);
                responseDto.setContent(listDto);

            }
        }
        return responseDto;
    }

    /**
     * 获取证件类型
     * @return
     */
    @GetMapping("getZljx")
    public ResponseDto getZljx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String, Map<String, String>> all =(Map<String, Map<String, String>>)redisTstaticemplate.opsForValue().get(RedisCode.CODESET);
        Map<String, String>  map   = all.get(CodeType.CODE_XX_TXZLX);
        responseDto.setContent(map);
        return responseDto;
    }


}

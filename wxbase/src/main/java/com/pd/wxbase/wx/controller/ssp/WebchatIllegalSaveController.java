package com.pd.wxbase.wx.controller.ssp;


import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.main.service.WebchatIllegalSaveService;
import com.pd.server.main.service.WebchatUsersService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/webchatIllegalSave")
public class WebchatIllegalSaveController extends BaseWxController {

    private static final String PIC_FILE_TYPE = "pic";
    private static final Logger LOG = LoggerFactory.getLogger(WebchatIllegalSaveController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private WebchatIllegalSaveService webchatIllegalSaveService;

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private WebchatUsersService webchatUsersService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WebchatIllegalSaveExample example = new WebchatIllegalSaveExample();
        WebchatIllegalSaveExample.Criteria ca = example.createCriteria();
        if(!list.isEmpty()){
            ca.andSysOrgCodeIn(list);
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        example.or().andSysOrgCodeIsNull();
        example.setOrderByClause(" create_time desc");
        List<WebchatIllegalSave> listDto = webchatIllegalSaveService.list(example);
        PageInfo<WebchatIllegalSave> pageInfo = new PageInfo<WebchatIllegalSave>(listDto);
        pageDto.setTotal(pageInfo.getTotal());
        List<WebchatIllegalSaveDto> dtoList = CopyUtil.copyList(listDto, WebchatIllegalSaveDto.class);
        pageDto.setList(dtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 微信端提交保存
     * @param
     * @param info
     * @return
     * @throws IOException
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(String images, WebchatIllegalSaveDto info) throws Exception {
        JSONArray imgArray = JSONArray.parseArray(images);
        String lsh = OrderUtils.getOrderNo();
        saveImageFile(imgArray, lsh);
        info.setCreateBy(info.getJbr());
        info.setCreateTime(new Date());
        info.setSjh(DesEncrypt.DesJiaMi(info.getSjh()));
        info.setSfzmhm(DesEncrypt.DesJiaMi(info.getSfzmhm()));
        info.setZt("1");//状态|1已上报2审核通过3审核不通过
        info.setLsh(lsh);
        info.setJlff("0");//奖励发放0未发放1已发放
        info.setIsChecked("0");//是否审核|0未审核1审核
        webchatIllegalSaveService.save(info);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(info);
        return responseDto;
    }

    private void saveImageFile(JSONArray imgArray, String lsh) {
        if(null != imgArray && imgArray.size() > 0){
            for (int i = 0; i < imgArray.size(); i++) {
                WebchatFileinfoDto file = new WebchatFileinfoDto();
                String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
                String filename = lsh + "_" + new Date().getTime() + ".jpg";
                String image = imgArray.getString(i);
                image = image.substring(image.lastIndexOf(",")+1);
                Base64Image.GenerateImage(ResizeImg.resizeImage(image), fileDriectory + filename);
                file.setLsh(lsh);
                file.setZplj(WxRedisConfig.fileStr + filename);
                file.setZpzl("pic");
                file.setZplx("1");
                file.setCjsj(new Date());
                webchatFileinfoService.save(file);
            }
        }
    }

    private void saveVideoFile(JSONArray imgArrayVideo, String lsh) {
        if(null != imgArrayVideo && imgArrayVideo.size() > 0){
            for (int i = 0; i < imgArrayVideo.size(); i++) {
                String key = UuidUtil.getShortUuid();
                String image = imgArrayVideo.getString(i);
                image = image.substring(image.lastIndexOf(",")+1);
                String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
                String basePath = picStorePath +"pic"+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM");
                File filepath = new File(basePath);
                if(!filepath.exists()){
                    filepath.mkdirs();
                }
                String fullPath = basePath+File.separator+key+".mp4";
                Base64Image.base64ToVideo(image, fullPath);
                WaterMarkFont.createWaterMarkByText(new File(fullPath), DateTools.getFormatDate(new Date(),"yyyy-MM-dd HH:mm"),new File(fullPath),45);
                WebchatFileinfoDto fileinfo = new WebchatFileinfoDto();
                fileinfo.setLsh(lsh);
                String readpath = "/system/f/"+"pic"+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM")+File.separator+key+".mp4";
                fileinfo.setZplj(readpath);
                fileinfo.setCjsj(new Date());
                fileinfo.setZpzl("video");
                webchatFileinfoService.wxsave(fileinfo);
            }
        }
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        webchatIllegalSaveService.delete(id);
        return responseDto;
    }

    /**
     * 微信端查询调用
     * @return
     */
    @PostMapping("/myssp")
    public ResponseDto myssp(String openid){
        ResponseDto responseDto = new ResponseDto();
        List<WebchatIllegalSave> list = webchatIllegalSaveService.findByOpenid(openid);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * @param id
     * @return
     */
    @PostMapping("/selectinfo")
    public ResponseDto selectinfo(String id){
        ResponseDto responseDto = new ResponseDto();
        WebchatIllegalSave info = webchatIllegalSaveService.findByID(id);
        List<WebchatFileinfo> piclist = new LinkedList<WebchatFileinfo>();
        if(!StringUtils.isEmpty(info.getLsh())){
            piclist = webchatFileinfoService.getFileinfoByLsh(info.getLsh(),PIC_FILE_TYPE);
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("info",info);
        map.put("piclist", piclist);
        responseDto.setContent(map);
        return responseDto;
    }
}

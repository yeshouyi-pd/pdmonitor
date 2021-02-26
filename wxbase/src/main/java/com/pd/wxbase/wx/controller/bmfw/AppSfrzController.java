package com.pd.wxbase.wx.controller.bmfw;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.WebchatUsers;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatUsersDto;
import com.pd.server.main.dto.basewx.my.FACE_FacecompareDto;
import com.pd.server.main.dto.basewx.my.OCR_IdCardocrDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.WebchatUsersService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import com.pd.wxbase.wx.wxutlis.utils.IDCardOCR;
import com.pd.wxbase.wx.wxutlis.utils.TencentAITools;
import com.pd.wxbase.wx.wxutlis.utils.TencentAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/sfrz")
public class AppSfrzController {

    private static final Logger LOG = LoggerFactory.getLogger(AppSfrzController.class);
    public static final String BUSINESS_NAME = "实名认证";

    @Resource
    private WebchatUsersService wxUsersService;

    public static RedisTemplate  redisTstaticemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
    }

    public static void main(String[] args) {
        String a = "imagega;agda,/98945,464";
        System.out.println(a);
        System.out.println(a.substring(a.lastIndexOf(",")+1));
    }

    /**
     * 识别身份证正面
     * @param data
     * @return
     */
    @PostMapping("/identpic")
    public ResponseDto identpic(@RequestBody String data) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String imageszm = jsonObject.getString("base64imageszm");
        String openid = jsonObject.getString("openid");
        imageszm = ResizeImg.resizeImage(imageszm.substring(imageszm.lastIndexOf(",")+1));
        String jsonIDZstr = IDCardOCR.Identification(imageszm, "FRONT");
        if(null != jsonIDZstr){
            JSONObject jsonIDZ = JSONObject.parseObject(jsonIDZstr);
            String xm = jsonIDZ.getString("Name");
            String sfzmhm = jsonIDZ.getString("IdNum");
            WebchatUsers wxUsers = wxUsersService.findBySfzmhm(DesEncrypt.DesJiaMi(sfzmhm));
            if(null  != wxUsers ){
                if(StringUtils.isEmpty(openid)){
                    throw new WxStrException("请通过微信客户端办理业务");
                }
                if( "1".equals(wxUsers.getSfrz())){
                    throw new WxStrException("您已经实名认证,无需重复操作");
                }
            }
            /**
             * 插入数据操作
             */
            WebchatUsersDto wxUsersDto = new WebchatUsersDto();
            if(null != wxUsers && !StringUtils.isEmpty(wxUsers.getId())){
                wxUsersDto.setId(wxUsers.getId());
            }
            wxUsersDto.setOpenid(openid);
            wxUsersDto.setZjmc(xm);
            wxUsersDto.setZjhm(DesEncrypt.DesJiaMi(sfzmhm));
            wxUsersDto.setSex(jsonIDZ.getString("Sex"));
            wxUsersDto.setZjdz(jsonIDZ.getString("Address"));
            String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
            Base64Image.GenerateImage(imageszm, fileDriectory + sfzmhm+"_zm.jpg");
            wxUsersDto.setTp2(WxRedisConfig.fileStr + sfzmhm+"_zm.jpg");
            wxUsersService.save(wxUsersDto);
            wxUsersDto.setZjhm(sfzmhm);
            wxUsersDto.setTp2(fileDriectory + sfzmhm+"_zm.jpg");
            responseDto.setContent(wxUsersDto);
        }else{
            throw new WxStrException("识别身份证正面图像失败");
        }
        return responseDto;
    }

    /**
     * 验证身份证反面信息
     */
    @PostMapping("/saveImage")
    public ResponseDto saveImage(@RequestBody String data) throws Exception{
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String sfzmhm = jsonObject.getString("sfzmhm");
        String lxdh = jsonObject.getString("lxdh");
        if(StringUtils.isEmpty(sfzmhm)){
            throw new WxStrException("请先上传身份证正面照片");
        }
        String imagesfm = jsonObject.getString("base64imagesfm");
        imagesfm = ResizeImg.resizeImage(imagesfm.substring(imagesfm.lastIndexOf(",")+1));
        WebchatUsers wxUsers = wxUsersService.findBySfzmhm(DesEncrypt.DesJiaMi(sfzmhm));
        if(null != wxUsers ){
            if(StringUtils.isEmpty(wxUsers.getOpenid())){
                throw new WxStrException("请通过微信客户端办理业务");
            }
            if( "1".equals(wxUsers.getSfrz())){
                throw new WxStrException("您已经实名认证,无需重复操作");
            }
        }
        String jsonIDFstr = IDCardOCR.Identification(imagesfm, "BACK");
        if(null != jsonIDFstr){
                /**
                 * 插入数据操作
                 */
                if(null != wxUsers && !StringUtils.isEmpty(wxUsers.getId())){
                    wxUsers.setId(wxUsers.getId());
                }
                wxUsers.setLxdh(DesEncrypt.DesJiaMi(lxdh));
                String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
                Base64Image.GenerateImage(imagesfm, fileDriectory + sfzmhm+"_fm.jpg");
                wxUsers.setTp3(WxRedisConfig.fileStr + sfzmhm+"_fm.jpg");
                WebchatUsersDto wxUsersDto = CopyUtil.copy(wxUsers, WebchatUsersDto.class);
                wxUsersService.save(wxUsersDto);
        }else{
            throw new WxStrException("识别身份证反面图像失败");
        }
        return responseDto;
    }

    /**
     * 保存微信用户信息
     * @param data
     * @return
     */
    @PostMapping("/saveWxUser")
    public ResponseDto saveWxUser(@RequestBody String data) throws Exception{
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String sfzmhm = jsonObject.getString("sfzmhm");
        String tp2 = jsonObject.getString("tp2");
        if(StringUtils.isEmpty(sfzmhm)){
            throw new WxStrException("请按照流程进行实名认证");
        }
        String imagesbr = jsonObject.getString("base64imagesbr");
        imagesbr = ResizeImg.resizeImage(imagesbr.substring(imagesbr.lastIndexOf(",")+1));
        WebchatUsers wxUsers = wxUsersService.findBySfzmhm(DesEncrypt.DesJiaMi(sfzmhm));
        if(null == wxUsers){
            throw new WxStrException("请按照流程进行实名认证");
        }
        if(null != wxUsers && "1".equals(wxUsers.getSfrz())){
            throw new WxStrException("您已经实名认证,无需重复操作");
        }
        //实名认证校验
        String result = IDCardOCR.FaceComparison(Base64Image.GetImageStr(tp2), imagesbr);
        if (null != result){
            JSONObject json = JSONObject.parseObject(result);
            String score = json.getString("Score");
            if (Double.valueOf(score).intValue() < 70) {
                throw new WxStrException("人证比对识别率较低,请重新拍照认证!");
            }
            /**
             * 更新数据操作
             */
            String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
            Base64Image.GenerateImage(imagesbr, fileDriectory + sfzmhm+"_br.jpg");
            wxUsers.setTp1(WxRedisConfig.fileStr + sfzmhm+"_br.jpg");
            wxUsers.setSimilarity(score);
            wxUsers.setSfrz("1");//是否认证 1认证
            wxUsers.setCjsj(new Date());
            WebchatUsersDto wxUsersDto = CopyUtil.copy(wxUsers, WebchatUsersDto.class);
            wxUsersService.save(wxUsersDto);
            wxUsersDto.setZjhm(DesEncrypt.DesJieMi(wxUsersDto.getZjhm()));
            wxUsersDto.setLxdh(DesEncrypt.DesJieMi(wxUsersDto.getLxdh()));
            responseDto.setContent(wxUsersDto);
        } else {
            throw new WxStrException("实名认证校验失败");
        }
        return responseDto;
    }

    /**
     * 实名认证校验
     * @param imageszm
     * @param imagesbr
     * @return
     */
    public JSONObject wxUsersCheck(String imageszm, String imagesbr) throws Exception{
        JSONObject jsonObj = new JSONObject();
        Map<String,String> person_Id_body = new HashMap<>();
        person_Id_body.put("image_a",ResizeImg.resizeImage(imageszm));
        person_Id_body.put("image_b",ResizeImg.resizeImage(imagesbr));
        String result = "0";//1:成功 0:失败
        person_Id_body = TencentAITools.getParamsMap(person_Id_body);
        JSONObject aiResultJson = TencentAITools.getAIResultJson(TencentAPI.FACE_FACECOMPARE,person_Id_body);
        LOG.info("实名认证返回:"+aiResultJson.toJSONString());
        String ret = aiResultJson.getString("ret");
        if("0".equals(ret)){
            JSONObject jsonObject = aiResultJson.getJSONObject("data");
            FACE_FacecompareDto data = (FACE_FacecompareDto) JSONObject.toJavaObject(jsonObject, FACE_FacecompareDto.class);
            if(Integer.parseInt(data.getSimilarity()) >= 75){
                result = "1";
            }else{
                jsonObj.put("msg", "相似度低于75");
            }
            jsonObj.put("xsd", data.getSimilarity());
        }
        jsonObj.put("result", result);
        return jsonObj;
    }

    /**
     * 识别身份证图像上面的详细身份信息
     * @param image
     * @param type 身份证图片类型，0-正面，1-反面
     * @return
     */
    public JSONObject IdentificationID(String image,String type) throws Exception{
        JSONObject jsonObj = new JSONObject();
        Map<String, String> person_Id_body = new HashMap<>();
        String result = "0";//1:成功 0:失败
        person_Id_body.put("image", image);
        person_Id_body.put("card_type", type);
        person_Id_body = TencentAITools.getParamsMap(person_Id_body);
        JSONObject aiResultJson = TencentAITools.getAIResultJson(TencentAPI.PERSON_ID,person_Id_body);
        LOG.info("识别身份证图像返回" + aiResultJson.toJSONString());
        String ret = aiResultJson.getString("ret");
        if ("0".equals(ret)) {
            result = "1";
            JSONObject jsonObject = aiResultJson.getJSONObject("data");
            OCR_IdCardocrDto data = (OCR_IdCardocrDto) JSONObject.toJavaObject(jsonObject,OCR_IdCardocrDto.class);
            jsonObj.put("xm", data.getName());
            jsonObj.put("sfzmhm", data.getId());
        }
        jsonObj.put("result", result);
        return jsonObj;
    }



}

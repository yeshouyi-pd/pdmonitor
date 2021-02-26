package com.pd.wxbase.wx.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.*;
import com.pd.server.util.Base64Util;
import com.pd.server.util.DateUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.wxutlis.utils.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/CodeSetUtil")
public class CodeSetUtilController extends BaseWxController {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DeptService deptService;

    @Resource
    private CodesetService codesetService;

    @Resource
    private WebchatIllcodeService webchatIllcodeService;

    @Resource
    private WebchatReportIllcodeService webchatReportIllcodeService;

    @Resource
    private AddrInfoService addrInfoService;

    @Resource
    private WebchatPostinfoService webchatPostinfoService;

    @Resource
    private WxYyController wxYyController;

    @Resource
    private DeptYwlxService deptYwlxService;

    /**
     * 获取随手拍违法类别
     * @return
     */
    @GetMapping("/getWflb")
    public ResponseDto getWflb(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_WFLB);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取抵押权人
     * @return
     */
    @GetMapping("/getDyqr")
    public ResponseDto getDyqr() {
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_DYQR);
        responseDto.setContent(map);
        return responseDto;

    }

    /**
     * 获取号牌种类
     * @return
     */
    @GetMapping("/getHpzl")
    public ResponseDto getHpzl(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_HPZL);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 微信公众调用获取号牌种类
     * @return
     */
    @GetMapping("/getWxHpzl")
    public ResponseDto getWxHpzl(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = getzljx(CodeType.CODE_XX_HPZL);
        responseDto.setContent(list);
        return responseDto;
    }
    /**
     * 获取行政区划
     * @return
     */
    @GetMapping("/getXzqh")
    public ResponseDto getXzqh(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_XZQH);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取base64解码信息
     * @return
     */
    @GetMapping("/getDecoder")
    public ResponseDto getDecoder(String str){
        ResponseDto responseDto = new ResponseDto();
        String deocderStr = Base64Util.getDecoder(str);
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put(str,deocderStr);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取交安故障类型类型
     * @return
     */
    @GetMapping("/getFacility")
    public ResponseDto getFacility(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_FACILITY);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取考试科目类型
     * @return
     */
    @GetMapping("/getKskm")
    public ResponseDto getKskm(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_KSKM);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 微信公众号调用考试科目类型
     * @return
     */
    @GetMapping("/getWxKskm")
    public ResponseDto getWxKskm(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list  = getzljx(CodeType.CODE_XX_KSKM);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 获取固封螺丝申领原因
     * @return
     */
    @GetMapping("/getScrewYy")
    public ResponseDto getScrewYy(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_SCREWYY);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取固封螺丝申领原因微信前端调用
     * @return
     */
    @GetMapping("/getWxScrewYy")
    public ResponseDto getWxScrewYy(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = getzljx(CodeType.CODE_XX_SCREWYY);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 获取车牌类型
     * @return
     */
    @GetMapping("/getCplx")
    public ResponseDto getCplx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_CPLX);
        responseDto.setContent(map);
        return responseDto;
    }


    /**
     * 获取车牌类型--（微信端调用）
     * @return
     */
    @GetMapping("/getWxCplx")
    public ResponseDto getWxCplx(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = getzljx(CodeType.CODE_XX_CPLX);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 获取黑名单原因
     * @return
     */
    @GetMapping("/getBlackyy")
    public ResponseDto getBlackyy(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_BlACKYY);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取套牌车报案人资料图片信息
     * @return
     */
    @GetMapping("/getTbcsbBar")
    public ResponseDto getTbcsbBar(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>> allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_TBCSBBAR);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取套牌车车辆资料图片信息
     * @return
     */
    @GetMapping("/getTbcsbCl")
    public ResponseDto getTbcsbCl(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>> allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_TBCSBCL);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取交通事故拍照信息
     * @return
     */
    @GetMapping("/getJtsgsbpic")
    public ResponseDto getJtsgsbpic(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>> allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_SGSBPZ);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取交通事故情形
     * @return
     */
    @GetMapping("/getJtsgqx")
    public ResponseDto getJtsgqx(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = getzljx(CodeType.CODE_XX_SGFSQX);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 获取发布信息类型
     * @return
     */
    @GetMapping("/getNewtype")
    public ResponseDto getNewtype(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>> allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_XXLX);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 身份证件类型
     * @return
     */
    @GetMapping("/getSfzjlx")
    public ResponseDto getSfzjlx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>> allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_FZFMMC);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 微信公众调用身份证件类型
     * @return
     */
    @GetMapping("/getWxZjlx")
    public ResponseDto getWxZjlx(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = getzljx(CodeType.CODE_FZFMMC);
        responseDto.setContent(list);
        return responseDto;
    }


    /**
     * 获取所有的部门信息
     * @return
     */
    @GetMapping("/getDeptInfo")
    public ResponseDto getDeptInfo(String type){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = new ArrayList<KvMapDto>();
        DeptExample example = new DeptExample();
        if(!StringUtils.isEmpty(type)){
            example.createCriteria().andDepttypeEqualTo(type);
        }
        List<Dept> deptlist = deptService.list(example);
        for (int i = 0; i < deptlist.size(); i++) {
            KvMapDto dto = new KvMapDto();
            Dept dept = deptlist.get(i);
            dto.setCode(dept.getDeptcode());
            dto.setText(dept.getDeptname());
            list.add(dto);
        }
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 获取所有的部门信息
     * @return
     */
    @PostMapping("/getDepts")
    public ResponseDto getDepts(String depttype){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = new ArrayList<KvMapDto>();
        DeptExample example = new DeptExample();
        example.createCriteria().andDepttypeEqualTo(depttype);
        List<Dept> deptlist = deptService.list(example);
        responseDto.setContent(deptlist);
        return responseDto;
    }

    /**
     * 获取所有的部门信息
     * @return
     */
    @GetMapping("/getDeptMap")
    public ResponseDto getDeptMap(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        List<Dept> deptlist = deptService.list(null);
        for (int i = 0; i < deptlist.size(); i++) {
            Dept dept = deptlist.get(i);
            map.put(dept.getDeptcode(), dept.getDeptname());
        }
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取审核原因
     * @return
     */
    @GetMapping("/getShyy")
    public ResponseDto getShyy(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_SHYY);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取注销原因
     * @return
     */
    @GetMapping("/getZxyy")
    public ResponseDto getZxyy(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_ZXYY);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取部门类型
     * @return
     */
    @GetMapping("/getDepttype")
    public ResponseDto getDepttype(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>> allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_DEPTTYPE);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 违法行为详细
     * @return
     */
    @PostMapping("/getWfxwByWflb")
    public ResponseDto getWfxwByWflb(WebchatReportIllcodeDto webchatReportIllcodeDto){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        WebchatReportIllcodeExample webchatReportIllcodeExample = new WebchatReportIllcodeExample();
        WebchatReportIllcodeExample.Criteria ca = webchatReportIllcodeExample.createCriteria();
        if (!StringUtils.isEmpty(webchatReportIllcodeDto.getWflb())){
            ca.andWflbEqualTo(webchatReportIllcodeDto.getWflb());
        }
        List<WebchatReportIllcode> webchatIllcodeList = webchatReportIllcodeService.list(webchatReportIllcodeExample);
        for (int i = 0; i < webchatIllcodeList.size(); i++) {
            WebchatReportIllcode webchatReportIllcode = webchatIllcodeList.get(i);
            map.put(webchatReportIllcode.getWfxwxx(), webchatReportIllcode.getWfxwms());
        }
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 违法行为详细
     * @return
     */
    @GetMapping("/getWfxw")
    public ResponseDto getWfxw(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        List<WebchatReportIllcode> webchatIllcodeList = webchatReportIllcodeService.list(null);
        for (int i = 0; i < webchatIllcodeList.size(); i++) {
            WebchatReportIllcode webchatReportIllcode = webchatIllcodeList.get(i);
            map.put(webchatReportIllcode.getWfxwxx(), webchatReportIllcode.getWfxwms());
        }
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取车辆类别
     * @return
     */
    @GetMapping("/getCllb")
    public ResponseDto getCllb(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_CLLB);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 微信公众调用车辆类别
     * @return
     */
    @GetMapping("/getWxCllb")
    public ResponseDto getWxCllb(){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = getzljx(CodeType.CODE_XX_CLLB);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 获取违法车归类
     * @return
     */
    @GetMapping("/getWfgl")
    public ResponseDto getWfgl(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_WFGL);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 出场原因
     * @return
     */
    @GetMapping("/getCcyy")
    public ResponseDto getCcyy(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_CCYY);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取事故情形
     * @return
     */
    @GetMapping("/getSgqx")
    public ResponseDto getSgqx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.CODE_XX_SGFSQX);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 保存地址信息
     */
    @GetMapping("/saveAddressInfo")
    public ResponseDto saveAddressInfo(){
        ResponseDto responseDto = new ResponseDto();
        String url = "https://dayin.hainanpost.cn/api/order/get_all_address";
        Map<String, Object> parameters = new HashMap<String,Object>();
        StringBuilder sb = new StringBuilder();
        WebchatPostinfoDto webchatPostinfoDto = webchatPostinfoService.getOne();
        String miYaoKey = "";
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSecretKey())){
            miYaoKey = webchatPostinfoDto.getSecretKey();
        }else{
            sb.append("密钥为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getLoginName())){
            parameters.put("login_name",webchatPostinfoDto.getLoginName());
        }else{
            sb.append("接口用户名为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getLoginPwd())){
            parameters.put("login_pwd",webchatPostinfoDto.getLoginPwd());
        }else{
            sb.append("接口用户密码为空，");
        }
        parameters.put("time_stamp",Long.toString(new Date().getTime()));
        if(sb.length()<=0){
            String sign = BuildSign.buildSign(parameters,miYaoKey);
            Map<String, Object> requestParam = new HashMap<String, Object>();
            requestParam.put("sign",sign);
            requestParam.put("data",parameters);
            System.out.println("请求参数："+requestParam);
            JSONObject result = SendPostUtil.sendPost(url,requestParam);
            if(result.get("status").toString().equals("0")){
                List<AddrInfo> addrInfoDtoList = addrInfoService.findListAll();
                for(AddrInfo entity:addrInfoDtoList){
                    addrInfoService.delete(entity.getId());
                }
                JSONArray resultData = (JSONArray) result.get("data");
                if(!StringUtils.isEmpty(resultData)&&resultData.size()>0){
                    for(int i=0;i<resultData.size();i++){
                        JSONObject entity = (JSONObject) resultData.get(i);
                        AddrInfoDto addrInfoDto = new AddrInfoDto();
                        addrInfoDto.setCode(StringUtils.isEmpty(entity.get("code"))?"":entity.get("code").toString());
                        addrInfoDto.setName(StringUtils.isEmpty(entity.get("name"))?"":entity.get("name").toString());
                        addrInfoDto.setPcode(StringUtils.isEmpty(entity.get("pcode"))?"":entity.get("pcode").toString());
                        addrInfoService.save(addrInfoDto);
                    }
                }
            }
            responseDto.setContent(result);
        }else{
            responseDto.setContent(sb.toString());
        }
        return responseDto;
    }

    @PostMapping("/getEmailNo")
    public ResponseDto getEmailNo(@RequestBody Map<String, Object> obj){
        ResponseDto responseDto = new ResponseDto();
        String url = "https://dayin.hainanpost.cn/api/order/createorder";
        Map<String, Object> parameters = new HashMap<String,Object>();
        StringBuilder sb = new StringBuilder();
        WebchatPostinfoDto webchatPostinfoDto = webchatPostinfoService.getOne();
        String miYaoKey = "";
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSecretKey())){
            miYaoKey = webchatPostinfoDto.getSecretKey();
        }else{
            sb.append("密钥为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getLoginName())){
            parameters.put("login_name",webchatPostinfoDto.getLoginName());
        }else{
            sb.append("接口用户名为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getLoginPwd())){
            parameters.put("login_pwd",webchatPostinfoDto.getLoginPwd());
        }else{
            sb.append("接口用户密码为空，");
        }
        parameters.put("time_stamp",Long.toString(new Date().getTime()));
        if(!StringUtils.isEmpty(obj.get("distributor_orderID"))){
            parameters.put("distributor_orderID",obj.get("distributor_orderID").toString());
        }else{
            sb.append("合作方唯一识别号为空，");
        }
        if(!StringUtils.isEmpty(obj.get("receiver_company"))){
            parameters.put("receiver_company",obj.get("receiver_company").toString());
        }
        if(!StringUtils.isEmpty(obj.get("receiver_name"))){
            parameters.put("receiver_name",obj.get("receiver_name").toString());
        }else {
            sb.append("收件人姓名为空，");
        }
        if(!StringUtils.isEmpty(obj.get("receiver_phone"))){
            parameters.put("receiver_phone",obj.get("receiver_phone").toString());
        }else {
            sb.append("收件人手机号为空，");
        }
        if(!StringUtils.isEmpty(obj.get("receiver_province_code"))){
            parameters.put("receiver_province_code",obj.get("receiver_province_code").toString());
        }else {
            sb.append("收件人省份编号为空，");
        }
        if(!StringUtils.isEmpty(obj.get("receiver_city_code"))){
            parameters.put("receiver_city_code",obj.get("receiver_city_code").toString());
        }else {
            sb.append("收件人城市编号为空，");
        }
        if(!StringUtils.isEmpty(obj.get("receiver_county_code"))){
            parameters.put("receiver_county_code",obj.get("receiver_county_code").toString());
        }else {
            sb.append("收件人区域编号为空，");
        }
        if(!StringUtils.isEmpty(obj.get("receiver_address"))){
            parameters.put("receiver_address",obj.get("receiver_address").toString());
        }else {
            sb.append("收件人地址为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderProvinceCode())){
            parameters.put("sender_province_code",webchatPostinfoDto.getSenderProvinceCode());
        }else{
            sb.append("寄件人省份编号为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderCityCode())){
            parameters.put("sender_city_code",webchatPostinfoDto.getSenderCityCode());
        }else{
            sb.append("寄件人城市编号为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderCountyCode())){
            parameters.put("sender_county_code",webchatPostinfoDto.getSenderCountyCode());
        }else {
            sb.append("寄件人区域编号为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderCompany())){
            parameters.put("sender_company",webchatPostinfoDto.getSenderCompany());
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderName())){
            parameters.put("sender_name", webchatPostinfoDto.getSenderName());
        }else {
            sb.append("寄件人姓名为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderPhone())){
            parameters.put("sender_phone", webchatPostinfoDto.getSenderPhone());
        }else {
            sb.append("寄件人手机号码为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderAddress())){
            parameters.put("sender_address",webchatPostinfoDto.getSenderAddress());
        }else{
            sb.append("寄件人地址为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getProtectPrice())){
            parameters.put("protect_price",webchatPostinfoDto.getProtectPrice());
        }else{
            parameters.put("protect_price",0);
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getInsuranceAmount())){
            parameters.put("insurance_amount",webchatPostinfoDto.getInsuranceAmount());
        }else {
            parameters.put("insurance_amount",0);
        }
//        if(!StringUtils.isEmpty(webchatPostinfoDto.getDeliverMoney())){
//            parameters.put("deliver_money",webchatPostinfoDto.getDeliverMoney());
//        }
        parameters.put("deliver_money",-1);
        if(!StringUtils.isEmpty(webchatPostinfoDto.getGoods())){
            parameters.put("goods",webchatPostinfoDto.getGoods());
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getGoodsQuantity())){
            parameters.put("goods_quantity",webchatPostinfoDto.getGoodsQuantity());
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getGoodsWeight())){
            parameters.put("goods_weight",webchatPostinfoDto.getGoodsWeight());
        }else{
            parameters.put("goods_weight",0);
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getDemo())){
            parameters.put("demo",webchatPostinfoDto.getDemo());
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getEmsType())){
            parameters.put("ems_type",webchatPostinfoDto.getEmsType());
        }else{
            sb.append("面单类型为空，");
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getOther())){
            parameters.put("other",webchatPostinfoDto.getOther());
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getEmsType())&&webchatPostinfoDto.getEmsType().equals(1)){
            if(!StringUtils.isEmpty(webchatPostinfoDto.getWhoPay())){
                parameters.put("who_pay",webchatPostinfoDto.getWhoPay());
            }else{
                parameters.put("who_pay",0);
            }
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getSenderAddressType())){
            parameters.put("sender_address_type",webchatPostinfoDto.getSenderAddressType());
        }else{
            parameters.put("sender_address_type",0);
        }
        if(!StringUtils.isEmpty(webchatPostinfoDto.getReceiverAddressType())){
            parameters.put("receiver_address_type",webchatPostinfoDto.getReceiverAddressType());
        }else{
            parameters.put("receiver_address_type",0);
        }
        if(sb.length()<=0){
            String sign = BuildSign.buildSign(parameters,miYaoKey);
            Map<String, Object> requestParam = new HashMap<String, Object>();
            requestParam.put("sign",sign);
            requestParam.put("data",parameters);
            System.out.println("请求参数："+requestParam);
            JSONObject result = SendPostUtil.sendPost(url,requestParam);
            responseDto.setContent(result);
        }else{
            responseDto.setSuccess(false);
            responseDto.setContent(sb.substring(0,sb.length()-1).toString());
        }
        return responseDto;
    }

    /**
     * 获取离用户最近的车管所
     * @param latitude
     * @param longitude
     * @return
     * @throws Exception
     */
    @PostMapping("/getapproach")
    public ResponseDto approach(String latitude, String longitude) throws Exception{
        System.out.println("latitude:"+latitude+"--"+"longitude:"+longitude);
        ResponseDto responseDto = new ResponseDto();
        List<Dept> list = deptService.findByJdAndWd(longitude, latitude);
        Map<String,String> map = new HashMap<String, String>();
        if(null != list && list.size() > 0){
            Dept entity = list.get(0);
            map.put("latitude", entity.getWd());
            map.put("longitude", entity.getJd());
            map.put("deptcode", entity.getDeptcode());
            map.put("deptname", entity.getDeptname());
        }
        responseDto.setContent(map);
        return responseDto;
    }

}

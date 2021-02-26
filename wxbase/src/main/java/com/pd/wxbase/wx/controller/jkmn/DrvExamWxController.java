package com.pd.wxbase.wx.controller.jkmn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.DrvExam;
import com.pd.server.main.domain.DrvExamlog;
import com.pd.server.main.dto.DrvExamlogDto;
import com.pd.server.main.dto.DrvExamrecordDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DrvExamService;
import com.pd.server.main.service.DrvExamlogService;
import com.pd.server.main.service.DrvExamrecordService;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wx/drvExam")
public class DrvExamWxController {
    private static final Logger LOG = LoggerFactory.getLogger(DrvExamWxController.class);
    public static final String BUSINESS_NAME = "真题管理微信端";

    @Resource
    private DrvExamService drvExamService;
    @Resource
    private DrvExamlogService drvExamlogService;
    @Resource
    private DrvExamrecordService drvExamrecordService;

    /**
     * 随机查询出100条数据
     */
    @GetMapping("/selectByLimit")
    public ResponseDto selectByLimit(){
        ResponseDto responseDto = new ResponseDto();
        List<DrvExam> drvExamList = drvExamService.selectByLimit();
        responseDto.setContent(drvExamList);
        return responseDto;
    }

    /**
     * 保存答题记录
     */
    @PostMapping("/saveSubmitRecord")
    @Transactional
    public ResponseDto saveSubmitRecord(@RequestBody JSONObject map) throws ParseException {
        ResponseDto responseDto = new ResponseDto();
        if(!StringUtils.isEmpty(map.get("logid"))){
            drvExamlogService.delete(map.getString("logid"));
            drvExamrecordService.deleteByLogid(map.getString("logid"));
        }
        DrvExamlog drvExamlog = new DrvExamlog();
        drvExamlog.setId(UuidUtil.getShortUuid());
        drvExamlog.setXm(map.get("xm").toString());
        drvExamlog.setSfzmhm(map.get("sfzmhm").toString());
        drvExamlog.setSjh(map.get("sjh").toString());
        drvExamlog.setCfbh(map.get("cfbh").toString());
        drvExamlog.setDtkm(map.get("dtkm").toString());
        drvExamlog.setKssj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("kssj").toString()));
        drvExamlog.setJssj(new Date());
        drvExamlog.setSdfs((Integer) map.get("sdfs"));
        if(drvExamlog.getSdfs()>90){
            drvExamlog.setSfjg("1");
        }else{
            drvExamlog.setSfjg("0");
        }
        drvExamlog.setWxid(map.get("wxid").toString());
        drvExamlogService.insert(drvExamlog);
        JSONArray answerList = new JSONArray();
        if(!StringUtils.isEmpty(map.getJSONArray("answerList"))){
            answerList = map.getJSONArray("answerList");
            for(int i=0;i<answerList.size();i++){
                DrvExamrecordDto drvExamrecordDto = new DrvExamrecordDto();
                JSONObject obj = answerList.getJSONObject(i);
                drvExamrecordDto.setKskm(obj.getString("kskm"));
                drvExamrecordDto.setDtlb(obj.getString("dtlb"));
                drvExamrecordDto.setTmbt(obj.getString("tmbt"));
                drvExamrecordDto.setA(obj.getString("a"));
                drvExamrecordDto.setB(obj.getString("b"));
                drvExamrecordDto.setC(obj.getString("c"));
                drvExamrecordDto.setD(obj.getString("d"));
                drvExamrecordDto.setTp1(obj.getString("tp1"));
                drvExamrecordDto.setTp2(obj.getString("tp2"));
                drvExamrecordDto.setZqda(obj.getString("zqda"));
                drvExamrecordDto.setXzda(obj.getString("xzda"));
                drvExamrecordDto.setDtsm(obj.getString("dtsm"));
                drvExamrecordDto.setWxid(drvExamlog.getWxid());
                drvExamrecordDto.setKtsx(i+1);
                drvExamrecordDto.setExamlogid(drvExamlog.getId());
                drvExamrecordDto.setExamid(obj.getString("id"));
                drvExamrecordService.save(drvExamrecordDto);
            }
        }
        List<String> tmIdList = new ArrayList<String>();
        if(!StringUtils.isEmpty(map.get("tmIdList"))){
            tmIdList = (List<String>) map.get("tmIdList");
        }
        if(!StringUtils.isEmpty(map.getJSONArray("questionList"))){
            JSONArray questionList = map.getJSONArray("questionList");
            int ktsx = answerList.size()+1;
            for(int j=0;j<questionList.size();j++){
                JSONObject obj = questionList.getJSONObject(j);
                String idTemp;
                if(!StringUtils.isEmpty(map.get("logid"))){
                    idTemp = obj.getString("examid");
                }else{
                    idTemp = obj.getString("id");
                }
                if(!tmIdList.contains(idTemp)){
                    DrvExamrecordDto drvExamrecordDto = new DrvExamrecordDto();
                    drvExamrecordDto.setKskm(obj.getString("kskm"));
                    drvExamrecordDto.setDtlb(obj.getString("dtlb"));
                    drvExamrecordDto.setTmbt(obj.getString("tmbt"));
                    drvExamrecordDto.setA(obj.getString("a"));
                    drvExamrecordDto.setB(obj.getString("b"));
                    drvExamrecordDto.setC(obj.getString("c"));
                    drvExamrecordDto.setD(obj.getString("d"));
                    drvExamrecordDto.setTp1(obj.getString("tp1"));
                    drvExamrecordDto.setTp2(obj.getString("tp2"));
                    drvExamrecordDto.setZqda(obj.getString("zqda"));
                    //drvExamrecordDto.setXzda(obj.getString("xzda"));
                    drvExamrecordDto.setDtsm(obj.getString("dtsm"));
                    drvExamrecordDto.setWxid(drvExamlog.getWxid());
                    drvExamrecordDto.setExamlogid(drvExamlog.getId());
                    drvExamrecordDto.setExamid(obj.getString("id"));
                    drvExamrecordDto.setKtsx(ktsx);
                    drvExamrecordService.save(drvExamrecordDto);
                    ktsx = ktsx +1;
                }
            }
        }
        return responseDto;
    }

    /**
     * 查询上次的考试记录
     */
    @PostMapping("/findLastLog")
    public ResponseDto findLastRecord(@RequestBody Map<String, Object> map){
        ResponseDto responseDto = new ResponseDto();
        DrvExamlogDto paramDrvExamlogDto = new DrvExamlogDto();
        if(map.containsKey("xm")&&!StringUtils.isEmpty(map.get("xm"))){
            paramDrvExamlogDto.setXm(map.get("xm").toString());
        }
        if(map.containsKey("sfzmhm")&&!StringUtils.isEmpty(map.get("sfzmhm"))){
            paramDrvExamlogDto.setSfzmhm(map.get("sfzmhm").toString());
        }
        if(map.containsKey("cfbh")&&!StringUtils.isEmpty(map.get("cfbh"))){
            paramDrvExamlogDto.setCfbh(map.get("cfbh").toString());
        }
        List<DrvExamlogDto> drvExamlogDtos = drvExamlogService.findLog(paramDrvExamlogDto);
        if(!StringUtils.isEmpty(drvExamlogDtos)&&drvExamlogDtos.size()>0){
            DrvExamlogDto drvExamlogDto = drvExamlogDtos.get(0);
            Integer correctCount = 0;
            Integer errorCount = 0;
            List<DrvExamrecordDto> questionList = drvExamrecordService.findByLogId(drvExamlogDto.getId());
            List answerList = new ArrayList<>();
            List<String> tmIdList = new ArrayList<String>();
            if(!StringUtils.isEmpty(questionList)&&questionList.size()>0){
                for(int i=0;i<questionList.size();i++){
                    DrvExamrecordDto entity = questionList.get(i);
                    if(!StringUtils.isEmpty(entity.getXzda())){
                        answerList.add(entity);
                        tmIdList.add(entity.getExamid());
                        if(entity.getXzda().equals(entity.getZqda())){
                            correctCount = correctCount+1;
                        }else{
                            errorCount = errorCount+1;
                        }
                    }
                }
            }
            List questionListTemp = questionList.stream().sorted(Comparator.comparing(DrvExamrecordDto :: getKtsx)).collect(Collectors.toList());
            drvExamlogDto.setAnswerList(answerList);
            drvExamlogDto.setQuestionList(questionListTemp);
            drvExamlogDto.setTmIdList(tmIdList);
            drvExamlogDto.setCorrectCount(correctCount);
            drvExamlogDto.setErrorCount(errorCount);
            responseDto.setContent(drvExamlogDto);
        }
        return responseDto;
    }

    /**
     * 查询所有的考试记录
     */
    @PostMapping("/findLog")
    public ResponseDto findLog(@RequestBody Map<String, Object> map) {
        ResponseDto responseDto = new ResponseDto();
        DrvExamlogDto paramDrvExamlogDto = new DrvExamlogDto();
        if(map.containsKey("xm")&&!StringUtils.isEmpty(map.get("xm"))){
            paramDrvExamlogDto.setXm(map.get("xm").toString());
        }
        if(map.containsKey("sfzmhm")&&!StringUtils.isEmpty(map.get("sfzmhm"))){
            paramDrvExamlogDto.setSfzmhm(map.get("sfzmhm").toString());
        }
        if(map.containsKey("cfbh")&&!StringUtils.isEmpty(map.get("cfbh"))){
            paramDrvExamlogDto.setCfbh(map.get("cfbh").toString());
        }
        List<DrvExamlogDto> drvExamlogDtos = drvExamlogService.findLog(paramDrvExamlogDto);
        responseDto.setContent(drvExamlogDtos);
        return responseDto;
    }
}

package com.pd.server.main.service.dsjk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.Attr;
import com.pd.server.main.domain.AttrExample;
import com.pd.server.main.domain.DeptYwlx;
import com.pd.server.main.domain.DeptYwlxExample;
import com.pd.server.main.mapper.AttrMapper;
import com.pd.server.main.mapper.DeptYwlxMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BslcService extends AbstractScanRequest{

    private DeptYwlxMapper deptYwlxMapper;
    private AttrMapper attrMapper;

    public BslcService(){
        deptYwlxMapper = SpringUtil.getBean(DeptYwlxMapper.class);
        attrMapper = SpringUtil.getBean(AttrMapper.class);
    }

    /**
     * 办事流程
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(String jsonParam) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        String deptcode = jsonObject.getString("deptcode");
        String ywfl = jsonObject.getString("ywfl");
        String ywlx = jsonObject.getString("ywlx");
        if(StringUtils.isEmpty(deptcode)||StringUtils.isEmpty(ywfl)||StringUtils.isEmpty(ywlx)){
            data = "参数错误";
            return data;
        }
        DeptYwlxExample deptYwlxExample = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = deptYwlxExample.createCriteria();
        ca.andDeptcodeEqualTo(deptcode);
        ca.andYwflEqualTo(ywfl);
        ca.andYwlxEqualTo(ywlx);
        List<DeptYwlx> deptYwlxList = deptYwlxMapper.selectByExample(deptYwlxExample);
        AttrExample example = new AttrExample();
        AttrExample.Criteria caAttr = example.createCriteria();
        caAttr.andAttrcodeEqualTo("ngurl");
        List<Attr> attrs = attrMapper.selectByExample(example);
        String ngUrl = "";
        if(attrs.size()==1){
            ngUrl = attrs.get(0).getAttrkey();
        }else {
            data = "系统异常";
        }
        if(deptYwlxList.size() > 0){
            List<DeptYwlx> resultList = new ArrayList<>();
            DeptYwlx deptYwlx = deptYwlxList.get(0);
            if(!StringUtils.isEmpty(deptYwlx.getLctf())){
                deptYwlx.setLctf(ngUrl+deptYwlx.getLctf());
            }
            if(!StringUtils.isEmpty(deptYwlx.getLctfi())){
                deptYwlx.setLctfi(ngUrl+deptYwlx.getLctfi());
            }
            if(!StringUtils.isEmpty(deptYwlx.getLcth())){
                deptYwlx.setLcth(ngUrl+deptYwlx.getLcth());
            }
            if(!StringUtils.isEmpty(deptYwlx.getLcto())){
                deptYwlx.setLcto(ngUrl+deptYwlx.getLcto());
            }
            if(!StringUtils.isEmpty(deptYwlx.getLctt())){
                deptYwlx.setLctt(ngUrl+deptYwlx.getLctt());
            }
            resultList.add(deptYwlx);
            data = JSON.toJSONString(resultList);
        }
        return data;
    }
}

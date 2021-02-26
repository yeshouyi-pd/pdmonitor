package com.pd.server.main.service.dsjk;

import com.alibaba.fastjson.JSON;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.Codeset;
import com.pd.server.main.domain.CodesetExample;
import com.pd.server.main.domain.Dept;
import com.pd.server.main.domain.DeptExample;
import com.pd.server.main.dto.DeptDsjkDto;
import com.pd.server.main.mapper.CodesetMapper;
import com.pd.server.main.mapper.DeptMapper;
import com.pd.server.util.CopyUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BswdService extends AbstractScanRequest{

    private CodesetMapper codesetMapper;

    private DeptMapper deptMapper;

    public BswdService(){
        codesetMapper = SpringUtil.getBean(CodesetMapper.class);
        deptMapper = SpringUtil.getBean(DeptMapper.class);
    }

    /**
     * 办事网点
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(String jsonParam) throws Exception {
        //查询办事网点
        Map<String, List<DeptDsjkDto>> depttype = new HashMap<>();
        Map<String,String> map = getDepttype();
        for(String key : map.keySet()){
            List<Dept> deptList = getDepts(key);
            List<DeptDsjkDto> list = CopyUtil.copyList(deptList,DeptDsjkDto.class);
            depttype.put(map.get(key),list);
        }
        if(depttype.size() > 0){
            data = JSON.toJSONString(depttype);
        }
        return data;
    }

    public Map<String,String> getDepttype(){
        CodesetExample example = new CodesetExample();
        CodesetExample.Criteria ca = example.createCriteria();
        ca.andTypeEqualTo("0501");
        List<Codeset> codesetList = codesetMapper.selectByExample(example);
        Map<String,String> map = new HashMap<>();
        for(Codeset entity : codesetList){
            map.put(entity.getCode(),entity.getName());
        }
        return map;
    }

    public List<Dept> getDepts(String depttype){
        DeptExample example = new DeptExample();
        example.createCriteria().andDepttypeEqualTo(depttype);
        List<Dept> deptList = deptMapper.selectByExample(example);
        return deptList;
    }

}

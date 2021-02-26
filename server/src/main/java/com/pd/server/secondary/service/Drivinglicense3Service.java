package com.pd.server.secondary.service;

import com.pd.server.secondary.domain.Drivinglicense3;
import com.pd.server.secondary.domain.Drivinglicense3Example;
import com.pd.server.secondary.mapper.Drivinglicense3Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Drivinglicense3Service {

    @Resource
    private Drivinglicense3Mapper rivinglicense3Mapper;

    /**
     * 根据身份证明号码查询
     */
    public Drivinglicense3 findBySfzmhm(String sfzmhm){
        Drivinglicense3Example example = new Drivinglicense3Example();
        Drivinglicense3Example.Criteria ca = example.createCriteria();
        ca.andSfzmhmEqualTo(sfzmhm);
        List<Drivinglicense3> list = rivinglicense3Mapper.selectByExample(example);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }


}

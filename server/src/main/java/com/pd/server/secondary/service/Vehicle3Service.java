package com.pd.server.secondary.service;

import com.pd.server.secondary.domain.Vehicle3;
import com.pd.server.secondary.domain.Vehicle3Example;
import com.pd.server.secondary.mapper.Vehicle3Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Vehicle3Service {

    @Resource
    private Vehicle3Mapper vehicle3Mapper;

    /**
     *  根据号牌号码查询
     */
    public Vehicle3 findByHphm(String hphm){
        Vehicle3Example example = new Vehicle3Example();
        Vehicle3Example.Criteria ca = example.createCriteria();
        ca.andHphmEqualTo(hphm);
        List<Vehicle3> list = vehicle3Mapper.selectByExample(example);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

}

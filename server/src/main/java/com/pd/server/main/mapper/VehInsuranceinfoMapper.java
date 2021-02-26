package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehInsuranceinfo;
import com.pd.server.main.domain.VehInsuranceinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehInsuranceinfoMapper {
    long countByExample(VehInsuranceinfoExample example);

    int deleteByExample(VehInsuranceinfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehInsuranceinfo record);

    int insertSelective(VehInsuranceinfo record);

    List<VehInsuranceinfo> selectByExample(VehInsuranceinfoExample example);

    VehInsuranceinfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehInsuranceinfo record, @Param("example") VehInsuranceinfoExample example);

    int updateByExample(@Param("record") VehInsuranceinfo record, @Param("example") VehInsuranceinfoExample example);

    int updateByPrimaryKeySelective(VehInsuranceinfo record);

    int updateByPrimaryKey(VehInsuranceinfo record);
}
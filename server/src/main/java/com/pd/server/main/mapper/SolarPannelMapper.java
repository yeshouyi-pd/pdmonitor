package com.pd.server.main.mapper;

import com.pd.server.main.domain.SolarPannel;
import com.pd.server.main.domain.SolarPannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SolarPannelMapper {
    long countByExample(SolarPannelExample example);

    int deleteByExample(SolarPannelExample example);

    int deleteByPrimaryKey(String id);

    int insert(SolarPannel record);

    int insertSelective(SolarPannel record);

    List<SolarPannel> selectByExample(SolarPannelExample example);

    SolarPannel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SolarPannel record, @Param("example") SolarPannelExample example);

    int updateByExample(@Param("record") SolarPannel record, @Param("example") SolarPannelExample example);

    int updateByPrimaryKeySelective(SolarPannel record);

    int updateByPrimaryKey(SolarPannel record);
}
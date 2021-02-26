package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatVehicle;
import com.pd.server.main.domain.WebchatVehicleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatVehicleMapper {
    long countByExample(WebchatVehicleExample example);

    int deleteByExample(WebchatVehicleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WebchatVehicle record);

    int insertSelective(WebchatVehicle record);

    List<WebchatVehicle> selectByExample(WebchatVehicleExample example);

    WebchatVehicle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WebchatVehicle record, @Param("example") WebchatVehicleExample example);

    int updateByExample(@Param("record") WebchatVehicle record, @Param("example") WebchatVehicleExample example);

    int updateByPrimaryKeySelective(WebchatVehicle record);

    int updateByPrimaryKey(WebchatVehicle record);
}
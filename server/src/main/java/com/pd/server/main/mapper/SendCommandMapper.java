package com.pd.server.main.mapper;

import com.pd.server.main.domain.SendCommand;
import com.pd.server.main.domain.SendCommandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendCommandMapper {
    long countByExample(SendCommandExample example);

    int deleteByExample(SendCommandExample example);

    int deleteByPrimaryKey(String id);

    int insert(SendCommand record);

    int insertSelective(SendCommand record);

    List<SendCommand> selectByExample(SendCommandExample example);

    SendCommand selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SendCommand record, @Param("example") SendCommandExample example);

    int updateByExample(@Param("record") SendCommand record, @Param("example") SendCommandExample example);

    int updateByPrimaryKeySelective(SendCommand record);

    int updateByPrimaryKey(SendCommand record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.InterfaceLog;
import com.pd.server.main.domain.InterfaceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterfaceLogMapper {
    long countByExample(InterfaceLogExample example);

    int deleteByExample(InterfaceLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(InterfaceLog record);

    int insertSelective(InterfaceLog record);

    List<InterfaceLog> selectByExampleWithBLOBs(InterfaceLogExample example);

    List<InterfaceLog> selectByExample(InterfaceLogExample example);

    InterfaceLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InterfaceLog record, @Param("example") InterfaceLogExample example);

    int updateByExampleWithBLOBs(@Param("record") InterfaceLog record, @Param("example") InterfaceLogExample example);

    int updateByExample(@Param("record") InterfaceLog record, @Param("example") InterfaceLogExample example);

    int updateByPrimaryKeySelective(InterfaceLog record);

    int updateByPrimaryKeyWithBLOBs(InterfaceLog record);

    int updateByPrimaryKey(InterfaceLog record);
}
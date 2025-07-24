package com.pd.server.main.mapper;

import com.pd.server.main.domain.BeconFileTy;
import com.pd.server.main.domain.BeconFileTyExample;
import java.util.List;

import com.pd.server.main.dto.BeconFileTyDto;
import org.apache.ibatis.annotations.Param;

public interface BeconFileTyMapper {
    long countByExample(BeconFileTyExample example);

    int deleteByExample(BeconFileTyExample example);

    int deleteByPrimaryKey(String id);

    int insert(BeconFileTy record);

    int insertSelective(BeconFileTy record);

    List<BeconFileTy> selectByExample(BeconFileTyExample example);

    List<BeconFileTy> selectByExampleSpecial(BeconFileTyDto record);

    BeconFileTy selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BeconFileTy record, @Param("example") BeconFileTyExample example);

    int updateByExample(@Param("record") BeconFileTy record, @Param("example") BeconFileTyExample example);

    int updateByPrimaryKeySelective(BeconFileTy record);

    int updateByPrimaryKey(BeconFileTy record);
}
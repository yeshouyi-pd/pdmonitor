package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehScrew;
import com.pd.server.main.domain.VehScrewExample;
import java.util.List;

import com.pd.server.main.dto.VehScrewDto;
import org.apache.ibatis.annotations.Param;

public interface VehScrewMapper {
    long countByExample(VehScrewExample example);

    int deleteByExample(VehScrewExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehScrew record);

    int insertSelective(VehScrew record);

    List<VehScrew> selectByExample(VehScrewExample example);

    VehScrew selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehScrew record, @Param("example") VehScrewExample example);

    int updateByExample(@Param("record") VehScrew record, @Param("example") VehScrewExample example);

    int updateByPrimaryKeySelective(VehScrew record);

    int updateByPrimaryKey(VehScrew record);

    List<VehScrewDto> vehScrewReport(VehScrewExample vehScrewExample);

    List<VehScrewDto> getSllbReport(VehScrewExample vehScrewExample);
}
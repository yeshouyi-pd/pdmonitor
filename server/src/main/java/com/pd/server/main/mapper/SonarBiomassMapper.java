package com.pd.server.main.mapper;

import com.pd.server.main.domain.SonarBiomass;
import com.pd.server.main.domain.SonarBiomassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SonarBiomassMapper {
    long countByExample(SonarBiomassExample example);

    int deleteByExample(SonarBiomassExample example);

    int deleteByPrimaryKey(String id);

    int insert(SonarBiomass record);

    int insertSelective(SonarBiomass record);

    List<SonarBiomass> selectByExample(SonarBiomassExample example);

    SonarBiomass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SonarBiomass record, @Param("example") SonarBiomassExample example);

    int updateByExample(@Param("record") SonarBiomass record, @Param("example") SonarBiomassExample example);

    int updateByPrimaryKeySelective(SonarBiomass record);

    int updateByPrimaryKey(SonarBiomass record);
}
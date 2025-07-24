package com.pd.server.main.mapper;

import com.pd.server.main.domain.AngleFile;
import com.pd.server.main.domain.AngleFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AngleFileMapper {
    long countByExample(AngleFileExample example);

    int deleteByExample(AngleFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(AngleFile record);

    int insertSelective(AngleFile record);

    List<AngleFile> selectByExample(AngleFileExample example);

    AngleFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AngleFile record, @Param("example") AngleFileExample example);

    int updateByExample(@Param("record") AngleFile record, @Param("example") AngleFileExample example);

    int updateByPrimaryKeySelective(AngleFile record);

    int updateByPrimaryKey(AngleFile record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.SpaceFile;
import com.pd.server.main.domain.SpaceFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpaceFileMapper {
    long countByExample(SpaceFileExample example);

    int deleteByExample(SpaceFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpaceFile record);

    int insertSelective(SpaceFile record);

    List<SpaceFile> selectByExample(SpaceFileExample example);

    SpaceFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpaceFile record, @Param("example") SpaceFileExample example);

    int updateByExample(@Param("record") SpaceFile record, @Param("example") SpaceFileExample example);

    int updateByPrimaryKeySelective(SpaceFile record);

    int updateByPrimaryKey(SpaceFile record);
}
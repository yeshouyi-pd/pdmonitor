package com.pd.server.main.mapper;

import com.pd.server.main.domain.DcbData;
import com.pd.server.main.domain.DcbDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DcbDataMapper {
    long countByExample(DcbDataExample example);

    int deleteByExample(DcbDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DcbData record);

    int insertSelective(DcbData record);

    List<DcbData> selectByExample(DcbDataExample example);

    DcbData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DcbData record, @Param("example") DcbDataExample example);

    int updateByExample(@Param("record") DcbData record, @Param("example") DcbDataExample example);

    int updateByPrimaryKeySelective(DcbData record);

    int updateByPrimaryKey(DcbData record);
}
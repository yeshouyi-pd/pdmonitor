package com.pd.server.main.mapper;

import com.pd.server.main.domain.DlbData;
import com.pd.server.main.domain.DlbDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlbDataMapper {
    long countByExample(DlbDataExample example);

    int deleteByExample(DlbDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DlbData record);

    int insertSelective(DlbData record);

    List<DlbData> selectByExample(DlbDataExample example);

    DlbData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DlbData record, @Param("example") DlbDataExample example);

    int updateByExample(@Param("record") DlbData record, @Param("example") DlbDataExample example);

    int updateByPrimaryKeySelective(DlbData record);

    int updateByPrimaryKey(DlbData record);
}
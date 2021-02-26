package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehLicelost;
import com.pd.server.main.domain.VehLicelostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehLicelostMapper {
    long countByExample(VehLicelostExample example);

    int deleteByExample(VehLicelostExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehLicelost record);

    int insertSelective(VehLicelost record);

    List<VehLicelost> selectByExample(VehLicelostExample example);

    VehLicelost selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehLicelost record, @Param("example") VehLicelostExample example);

    int updateByExample(@Param("record") VehLicelost record, @Param("example") VehLicelostExample example);

    int updateByPrimaryKeySelective(VehLicelost record);

    int updateByPrimaryKey(VehLicelost record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehPass;
import com.pd.server.main.domain.VehPassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehPassMapper {
    long countByExample(VehPassExample example);

    int deleteByExample(VehPassExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehPass record);

    int insertSelective(VehPass record);

    List<VehPass> selectByExample(VehPassExample example);

    VehPass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehPass record, @Param("example") VehPassExample example);

    int updateByExample(@Param("record") VehPass record, @Param("example") VehPassExample example);

    int updateByPrimaryKeySelective(VehPass record);

    int updateByPrimaryKey(VehPass record);
}
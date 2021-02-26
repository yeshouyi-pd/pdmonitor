package com.pd.server.main.mapper;

import com.pd.server.main.domain.AddrInfo;
import com.pd.server.main.domain.AddrInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddrInfoMapper {
    long countByExample(AddrInfoExample example);

    int deleteByExample(AddrInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(AddrInfo record);

    int insertSelective(AddrInfo record);

    List<AddrInfo> selectByExample(AddrInfoExample example);

    AddrInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AddrInfo record, @Param("example") AddrInfoExample example);

    int updateByExample(@Param("record") AddrInfo record, @Param("example") AddrInfoExample example);

    int updateByPrimaryKeySelective(AddrInfo record);

    int updateByPrimaryKey(AddrInfo record);
}
package com.pd.server.main.mapper;

import com.pd.server.main.domain.WxPage;
import com.pd.server.main.domain.WxPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxPageMapper {
    long countByExample(WxPageExample example);

    int deleteByExample(WxPageExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxPage record);

    int insertSelective(WxPage record);

    List<WxPage> selectByExample(WxPageExample example);

    WxPage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxPage record, @Param("example") WxPageExample example);

    int updateByExample(@Param("record") WxPage record, @Param("example") WxPageExample example);

    int updateByPrimaryKeySelective(WxPage record);

    int updateByPrimaryKey(WxPage record);
}
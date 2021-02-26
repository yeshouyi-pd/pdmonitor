package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatNewinfo;
import com.pd.server.main.domain.WebchatNewinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatNewinfoMapper {
    long countByExample(WebchatNewinfoExample example);

    int deleteByExample(WebchatNewinfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatNewinfo record);

    int insertSelective(WebchatNewinfo record);

    List<WebchatNewinfo> selectByExampleWithBLOBs(WebchatNewinfoExample example);

    List<WebchatNewinfo> selectByExample(WebchatNewinfoExample example);

    WebchatNewinfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatNewinfo record, @Param("example") WebchatNewinfoExample example);

    int updateByExampleWithBLOBs(@Param("record") WebchatNewinfo record, @Param("example") WebchatNewinfoExample example);

    int updateByExample(@Param("record") WebchatNewinfo record, @Param("example") WebchatNewinfoExample example);

    int updateByPrimaryKeySelective(WebchatNewinfo record);

    int updateByPrimaryKeyWithBLOBs(WebchatNewinfo record);

    int updateByPrimaryKey(WebchatNewinfo record);

    List<WebchatNewinfo> selectByExampleNewsHead();

    List<WebchatNewinfo> selectByExampleIllegal();

}
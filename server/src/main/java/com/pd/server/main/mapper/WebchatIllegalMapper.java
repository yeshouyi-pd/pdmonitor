package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatIllegal;
import com.pd.server.main.domain.WebchatIllegalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatIllegalMapper {
    long countByExample(WebchatIllegalExample example);

    int deleteByExample(WebchatIllegalExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatIllegal record);

    int insertSelective(WebchatIllegal record);

    List<WebchatIllegal> selectByExample(WebchatIllegalExample example);

    WebchatIllegal selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatIllegal record, @Param("example") WebchatIllegalExample example);

    int updateByExample(@Param("record") WebchatIllegal record, @Param("example") WebchatIllegalExample example);

    int updateByPrimaryKeySelective(WebchatIllegal record);

    int updateByPrimaryKey(WebchatIllegal record);
}
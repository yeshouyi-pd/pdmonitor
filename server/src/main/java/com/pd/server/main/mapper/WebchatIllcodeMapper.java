package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatIllcode;
import com.pd.server.main.domain.WebchatIllcodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatIllcodeMapper {
    long countByExample(WebchatIllcodeExample example);

    int deleteByExample(WebchatIllcodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatIllcode record);

    int insertSelective(WebchatIllcode record);

    List<WebchatIllcode> selectByExample(WebchatIllcodeExample example);

    WebchatIllcode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatIllcode record, @Param("example") WebchatIllcodeExample example);

    int updateByExample(@Param("record") WebchatIllcode record, @Param("example") WebchatIllcodeExample example);

    int updateByPrimaryKeySelective(WebchatIllcode record);

    int updateByPrimaryKey(WebchatIllcode record);

    List<String> findAllWfdm();
}
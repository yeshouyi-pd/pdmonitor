package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatIllegalSave;
import com.pd.server.main.domain.WebchatIllegalSaveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatIllegalSaveMapper {
    long countByExample(WebchatIllegalSaveExample example);

    int deleteByExample(WebchatIllegalSaveExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatIllegalSave record);

    int insertSelective(WebchatIllegalSave record);

    List<WebchatIllegalSave> selectByExample(WebchatIllegalSaveExample example);

    WebchatIllegalSave selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebchatIllegalSave record, @Param("example") WebchatIllegalSaveExample example);

    int updateByExample(@Param("record") WebchatIllegalSave record, @Param("example") WebchatIllegalSaveExample example);

    int updateByPrimaryKeySelective(WebchatIllegalSave record);

    int updateByPrimaryKey(WebchatIllegalSave record);

    int updateById(String id);
}
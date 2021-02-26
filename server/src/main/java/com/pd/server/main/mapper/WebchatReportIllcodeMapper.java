package com.pd.server.main.mapper;

import com.pd.server.main.domain.WebchatReportIllcode;
import com.pd.server.main.domain.WebchatReportIllcodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebchatReportIllcodeMapper {
    long countByExample(WebchatReportIllcodeExample example);

    int deleteByExample(WebchatReportIllcodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebchatReportIllcode record);

    int insertSelective(WebchatReportIllcode record);

    List<WebchatReportIllcode> selectByExample(WebchatReportIllcodeExample example);

    WebchatReportIllcode selectByPrimaryKey(String id);

    WebchatReportIllcode selectByWfxwxx(String wfxwxx);

    int updateByExampleSelective(@Param("record") WebchatReportIllcode record, @Param("example") WebchatReportIllcodeExample example);

    int updateByExample(@Param("record") WebchatReportIllcode record, @Param("example") WebchatReportIllcodeExample example);

    int updateByPrimaryKeySelective(WebchatReportIllcode record);

    int updateByPrimaryKey(WebchatReportIllcode record);
}
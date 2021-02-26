package com.pd.server.main.mapper;

import com.pd.server.main.domain.ReportFacilityfail;
import com.pd.server.main.domain.ReportFacilityfailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportFacilityfailMapper {
    long countByExample(ReportFacilityfailExample example);

    int deleteByExample(ReportFacilityfailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportFacilityfail record);

    int insertSelective(ReportFacilityfail record);

    List<ReportFacilityfail> selectByExample(ReportFacilityfailExample example);

    ReportFacilityfail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportFacilityfail record, @Param("example") ReportFacilityfailExample example);

    int updateByExample(@Param("record") ReportFacilityfail record, @Param("example") ReportFacilityfailExample example);

    int updateByPrimaryKeySelective(ReportFacilityfail record);

    int updateByPrimaryKey(ReportFacilityfail record);
}
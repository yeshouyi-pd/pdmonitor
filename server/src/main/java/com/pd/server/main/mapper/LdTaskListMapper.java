package com.pd.server.main.mapper;

import com.pd.server.main.domain.LdTaskList;
import com.pd.server.main.domain.LdTaskListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LdTaskListMapper {
    long countByExample(LdTaskListExample example);

    int deleteByExample(LdTaskListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LdTaskList record);

    int insertSelective(LdTaskList record);

    List<LdTaskList> selectByExample(LdTaskListExample example);

    LdTaskList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LdTaskList record, @Param("example") LdTaskListExample example);

    int updateByExample(@Param("record") LdTaskList record, @Param("example") LdTaskListExample example);

    int updateByPrimaryKeySelective(LdTaskList record);

    int updateByPrimaryKey(LdTaskList record);
}
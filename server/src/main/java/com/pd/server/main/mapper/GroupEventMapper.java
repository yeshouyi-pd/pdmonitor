package com.pd.server.main.mapper;

import com.pd.server.main.domain.GroupEvent;
import com.pd.server.main.domain.GroupEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupEventMapper {
    long countByExample(GroupEventExample example);

    int deleteByExample(GroupEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(GroupEvent record);

    int insertSelective(GroupEvent record);

    List<GroupEvent> selectByExampleWithBLOBs(GroupEventExample example);

    List<GroupEvent> selectByExample(GroupEventExample example);

    GroupEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GroupEvent record, @Param("example") GroupEventExample example);

    int updateByExampleWithBLOBs(@Param("record") GroupEvent record, @Param("example") GroupEventExample example);

    int updateByExample(@Param("record") GroupEvent record, @Param("example") GroupEventExample example);

    int updateByPrimaryKeySelective(GroupEvent record);

    int updateByPrimaryKeyWithBLOBs(GroupEvent record);

    int updateByPrimaryKey(GroupEvent record);
}
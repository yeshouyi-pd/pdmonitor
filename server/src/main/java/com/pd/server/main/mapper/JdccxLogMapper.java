package com.pd.server.main.mapper;

import com.pd.server.main.domain.JdccxLog;
import com.pd.server.main.domain.JdccxLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JdccxLogMapper {
    long countByExample(JdccxLogExample example);

    int deleteByExample(JdccxLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(JdccxLog record);

    int insertSelective(JdccxLog record);

    List<JdccxLog> selectByExampleWithBLOBs(JdccxLogExample example);

    List<JdccxLog> selectByExample(JdccxLogExample example);

    JdccxLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JdccxLog record, @Param("example") JdccxLogExample example);

    int updateByExampleWithBLOBs(@Param("record") JdccxLog record, @Param("example") JdccxLogExample example);

    int updateByExample(@Param("record") JdccxLog record, @Param("example") JdccxLogExample example);

    int updateByPrimaryKeySelective(JdccxLog record);

    int updateByPrimaryKeyWithBLOBs(JdccxLog record);

    int updateByPrimaryKey(JdccxLog record);
}
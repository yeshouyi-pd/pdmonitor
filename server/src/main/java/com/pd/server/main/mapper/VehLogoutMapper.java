package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehLogout;
import com.pd.server.main.domain.VehLogoutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehLogoutMapper {
    long countByExample(VehLogoutExample example);

    int deleteByExample(VehLogoutExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehLogout record);

    int insertSelective(VehLogout record);

    List<VehLogout> selectByExample(VehLogoutExample example);

    VehLogout selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehLogout record, @Param("example") VehLogoutExample example);

    int updateByExample(@Param("record") VehLogout record, @Param("example") VehLogoutExample example);

    int updateByPrimaryKeySelective(VehLogout record);

    int updateByPrimaryKey(VehLogout record);
}
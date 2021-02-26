package com.pd.server.secondary.mapper;

import com.pd.server.secondary.domain.Drivinglicense3;
import com.pd.server.secondary.domain.Drivinglicense3Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Drivinglicense3Mapper {
    long countByExample(Drivinglicense3Example example);

    int deleteByExample(Drivinglicense3Example example);

    int deleteByPrimaryKey(String sfzmhm);

    int insert(Drivinglicense3 record);

    int insertSelective(Drivinglicense3 record);

    List<Drivinglicense3> selectByExample(Drivinglicense3Example example);

    Drivinglicense3 selectByPrimaryKey(String sfzmhm);

    int updateByExampleSelective(@Param("record") Drivinglicense3 record, @Param("example") Drivinglicense3Example example);

    int updateByExample(@Param("record") Drivinglicense3 record, @Param("example") Drivinglicense3Example example);

    int updateByPrimaryKeySelective(Drivinglicense3 record);

    int updateByPrimaryKey(Drivinglicense3 record);
}
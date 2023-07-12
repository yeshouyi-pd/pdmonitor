package com.pd.server.main.mapper;

import com.pd.server.main.domain.Attr;
import com.pd.server.main.domain.AttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttrMapper {
    long countByExample(AttrExample example);

    int deleteByExample(AttrExample example);

    int deleteByPrimaryKey(String id);

    int insert(Attr record);

    int insertSelective(Attr record);

    List<Attr> selectByExample(AttrExample example);

    Attr selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Attr record, @Param("example") AttrExample example);

    int updateByExample(@Param("record") Attr record, @Param("example") AttrExample example);

    int updateByPrimaryKeySelective(Attr record);

    int updateByPrimaryKey(Attr record);

    String selectByAttrKey(String attrcode);
}
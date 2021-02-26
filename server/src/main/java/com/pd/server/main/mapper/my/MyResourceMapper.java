package com.pd.server.main.mapper.my;

import com.pd.server.main.domain.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MyResourceMapper {


    List<Resource> selectAll(@Param("roleid") String roleid);

    String getUpRescode(@Param("id") String id);


}
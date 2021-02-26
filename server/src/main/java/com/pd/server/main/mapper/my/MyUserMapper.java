package com.pd.server.main.mapper.my;

import com.pd.server.main.dto.ResourceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyUserMapper {

    List<ResourceDto> findResources(@Param("rode") String rode);

}

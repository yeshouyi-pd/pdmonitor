package com.pd.server.main.mapper.my;

import com.pd.server.main.domain.AddrInfo;
import com.pd.server.main.domain.File;
import com.pd.server.main.dto.FileAndFileinfoDto;
import com.pd.server.main.dto.FileDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyFileMapper {

    List<FileAndFileinfoDto> query(@Param("record") FileDto record);

}
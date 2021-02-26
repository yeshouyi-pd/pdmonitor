package com.pd.server.main.mapper.my;

import com.pd.server.main.dto.KvIntDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MyDeptMapper {

    String getUpdeptcode(@Param("deptcode") String deptcode);

}
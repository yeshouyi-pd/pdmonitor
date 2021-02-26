package com.pd.server.main.mapper.my;

import com.pd.server.main.domain.Dept;

import com.pd.server.main.dto.DeptYysjDto;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.dto.WxYyDto;
import com.pd.server.main.dto.basewx.my.YwyyParamDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MyDeptMapper {

    /**
     * 根据微信页面选择的业务分类和业务类型查询
     *   能办理改业务的所有部门
     * @param ywyyParamDto
     * @return
     */
    List<Dept> getDeptByYwlx(YwyyParamDto ywyyParamDto);

    List<KvIntDto> getDeptYyMum();

    String getUpdeptcode(@Param("deptcode") String deptcode);

    List<KvIntDto> getYysjMum(@Param("deptcode") String deptcode);

    List<KvIntDto> getYysjByDayMum(DeptYysjDto DeptYysjDto);

    Integer getYysjByDayNowMum( WxYyDto wxYyDto);

    Integer getYysjByDayYysdMum( WxYyDto wxYyDto);

    Integer queryCanyyBydept( WxYyDto wxYyDto);

}
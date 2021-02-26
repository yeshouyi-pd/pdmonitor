package com.pd.server.main.mapper;

import com.pd.server.main.domain.WxYy;
import com.pd.server.main.domain.WxYyExample;
import java.util.List;

import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.dto.basewx.my.VehParkLotGuoupDto;
import com.pd.server.main.dto.basewx.my.WxyyGuoupDto;
import org.apache.ibatis.annotations.Param;

public interface WxYyMapper {
    long countByExample(WxYyExample example);

    int deleteByExample(WxYyExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxYy record);

    int insertSelective(WxYy record);

    List<WxYy> selectByExample(WxYyExample example);

    WxYy selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxYy record, @Param("example") WxYyExample example);

    int updateByExample(@Param("record") WxYy record, @Param("example") WxYyExample example);

    int updateByPrimaryKeySelective(WxYy record);

    int updateByPrimaryKey(WxYy record);

    List<WxyyGuoupDto> getStatisticsByDay(WxYyExample example);

    List<WxyyGuoupDto> getStatisticsByTodayAndDeptcode(WxYyExample example);

    List<WxyyGuoupDto> getYyslByExample(WxYyExample example);

    List<WxyyGuoupDto> getYyslByBeforeTen(WxYyExample example);
}
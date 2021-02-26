package com.pd.server.main.mapper;

import com.pd.server.main.domain.VehParkLot;
import com.pd.server.main.domain.VehParkLotExample;
import java.util.List;

import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.dto.basewx.my.VehParkLotGuoupDto;
import org.apache.ibatis.annotations.Param;

public interface VehParkLotMapper {
    long countByExample(VehParkLotExample example);

    int deleteByExample(VehParkLotExample example);

    int deleteByPrimaryKey(String id);

    int insert(VehParkLot record);

    int insertSelective(VehParkLot record);

    List<VehParkLot> selectByExample(VehParkLotExample example);

    VehParkLot selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") VehParkLot record, @Param("example") VehParkLotExample example);

    int updateByExample(@Param("record") VehParkLot record, @Param("example") VehParkLotExample example);

    int updateByPrimaryKeySelective(VehParkLot record);

    int updateByPrimaryKey(VehParkLot record);

    List<VehParkLotGuoupDto> getStatisticsByRcToday(VehParkLotDto vehParkLotDto);

    List<VehParkLotGuoupDto> getStatisticsByCcToday(VehParkLotDto vehParkLotDto);

    List<VehParkLotGuoupDto> getStatisticsByRcDay(VehParkLotDto vehParkLotDto);

    List<VehParkLotGuoupDto> getStatisticsByCcDay(VehParkLotDto vehParkLotDto);
}
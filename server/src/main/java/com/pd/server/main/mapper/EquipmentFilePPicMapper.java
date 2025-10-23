package com.pd.server.main.mapper;

import com.pd.server.main.domain.EquipmentFilePPic;
import com.pd.server.main.domain.EquipmentFilePPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentFilePPicMapper {
    long countByExample(EquipmentFilePPicExample example);

    int deleteByExample(EquipmentFilePPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EquipmentFilePPic record);

    int insertSelective(EquipmentFilePPic record);

    List<EquipmentFilePPic> selectByExample(EquipmentFilePPicExample example);

    EquipmentFilePPic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EquipmentFilePPic record, @Param("example") EquipmentFilePPicExample example);

    int updateByExample(@Param("record") EquipmentFilePPic record, @Param("example") EquipmentFilePPicExample example);

    int updateByPrimaryKeySelective(EquipmentFilePPic record);

    int updateByPrimaryKey(EquipmentFilePPic record);
}
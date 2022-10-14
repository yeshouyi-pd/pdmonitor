package com.pd.server.main.service;

import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquiplogExample;
import com.pd.server.main.dto.WaterEquiplogDto;
import com.pd.server.main.mapper.WaterEquiplogMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class WaterEquiplogService {

    @Resource
    private WaterEquiplogMapper waterEquiplogMapper;

    /**
    * 列表查询
    */
    public List<WaterEquiplog> list(WaterEquiplogExample waterEquiplogExample) {
        return waterEquiplogMapper.selectByExample(waterEquiplogExample);
    }

    /**
     * 根据sbbh查询最新一条数据
     * @param sbbh
     * @return
     */
    public WaterEquiplog findBySbbh(String sbbh){
        WaterEquiplogExample example = new WaterEquiplogExample();
        example.createCriteria().andSbbhEqualTo(sbbh).andCjsjEqualTo(DateTools.getFormatDate(new Date(),"yyyy-MM-dd"));
        example.setOrderByClause(" cjsj desc ");
        List<WaterEquiplog> waterEquiplogs = waterEquiplogMapper.selectByExample(example);
        if(waterEquiplogs.size() > 0){
            return waterEquiplogs.get(0);
        }
        return null;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WaterEquiplogDto waterEquiplogDto) {
        WaterEquiplog waterEquiplog = CopyUtil.copy(waterEquiplogDto, WaterEquiplog.class);
        if (StringUtils.isEmpty(waterEquiplogDto.getId())) {
            this.insert(waterEquiplog);
        } else {
            this.update(waterEquiplog);
        }
    }

    /**
    * 新增
    */
    private void insert(WaterEquiplog waterEquiplog) {
                Date now = new Date();
        waterEquiplog.setId(UuidUtil.getShortUuid());
        waterEquiplogMapper.insert(waterEquiplog);
    }

    /**
    * 更新
    */
    private void update(WaterEquiplog waterEquiplog) {
        waterEquiplogMapper.updateByPrimaryKey(waterEquiplog);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        waterEquiplogMapper.deleteByPrimaryKey(id);
    }

}
package com.pd.server.main.service;

import com.pd.server.main.domain.DrvQxks;
import com.pd.server.main.domain.DrvQxksExample;
import com.pd.server.main.dto.DrvQxksDto;
import com.pd.server.main.mapper.DrvQxksMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class DrvQxksService {

@Resource
private DrvQxksMapper drvQxksMapper;

/**
* 列表查询
*/
public List<DrvQxks> list(DrvQxksExample drvQxksExample) {
    List<DrvQxks> drvQxksList = drvQxksMapper.selectByExample(drvQxksExample);
    return drvQxksList;

    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DrvQxksDto drvQxksDto) {
    DrvQxks drvQxks = CopyUtil.copy(drvQxksDto, DrvQxks.class);
    if (StringUtils.isEmpty(drvQxksDto.getId())) {
    this.insert(drvQxks);
    } else {
    this.update(drvQxks);
    }
    }

    /**
    * 新增
    */
    private void insert(DrvQxks drvQxks) {
            Date now = new Date();
    drvQxks.setId(UuidUtil.getShortUuid());
    drvQxksMapper.insert(drvQxks);
    }

    /**
    * 更新
    */
    private void update(DrvQxks drvQxks) {
    drvQxksMapper.updateByPrimaryKey(drvQxks);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    drvQxksMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询
     * @param sfzmhm
     * @param yykssj
     * @return
     */
    public DrvQxksDto findBySfzmhmAndYykssj(String sfzmhm, Date yykssj){
        DrvQxksExample example = new DrvQxksExample();
        DrvQxksExample.Criteria criteria = example.createCriteria();
        criteria.andSfzmhmEqualTo(sfzmhm);
        criteria.andYykssjEqualTo(yykssj);
        List<DrvQxks> list = drvQxksMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            DrvQxks drvQxks = list.get(0);
            DrvQxksDto drvQxksDto = CopyUtil.copy(drvQxks, DrvQxksDto.class);
            return drvQxksDto;
        }
        return null;
    }

}

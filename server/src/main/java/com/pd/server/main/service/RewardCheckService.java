package com.pd.server.main.service;

import com.pd.server.main.domain.RewardCheck;
import com.pd.server.main.domain.RewardCheckExample;
import com.pd.server.main.dto.RewardCheckDto;
import com.pd.server.main.mapper.RewardCheckMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class RewardCheckService {

@Resource
private RewardCheckMapper rewardCheckMapper;

/**
* 列表查询
*/
public List<RewardCheck> list(RewardCheckExample webchatIllegalExample) {
    List<RewardCheck> webchatIllegalList = rewardCheckMapper.selectByExample(webchatIllegalExample);
    return webchatIllegalList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(RewardCheckDto rewardCheckDto) {
    RewardCheck rewardCheck = CopyUtil.copy(rewardCheckDto, RewardCheck.class);
    if (StringUtils.isEmpty(rewardCheckDto.getId())) {
    this.insert(rewardCheck);

    } else {
    this.update(rewardCheck);
    }
    }

    /**
    * 新增
    */
    private void insert(RewardCheck rewardCheck) {
            Date now = new Date();
    rewardCheck.setId(UuidUtil.getShortUuid());
    rewardCheckMapper.insert(rewardCheck);
    }

    /**
    * 更新
    */
    private void update(RewardCheck rewardCheck) {
    rewardCheckMapper.updateByPrimaryKey(rewardCheck);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    rewardCheckMapper.deleteByPrimaryKey(id);
    }
    }

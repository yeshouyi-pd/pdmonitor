package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatBlack;
import com.pd.server.main.domain.WebchatBlackExample;
import com.pd.server.main.dto.WebchatBlackDto;
import com.pd.server.main.mapper.WebchatBlackMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class WebchatBlackService {

@Resource
private WebchatBlackMapper webchatBlackMapper;

/**
* 列表查询
*/
public List list(WebchatBlackExample webchatBlackExample) {
    List<WebchatBlack> webchatBlackList = webchatBlackMapper.selectByExample(webchatBlackExample);
    return webchatBlackList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatBlackDto webchatBlackDto) {
    WebchatBlack webchatBlack = CopyUtil.copy(webchatBlackDto, WebchatBlack.class);
    if (StringUtils.isEmpty(webchatBlackDto.getId())) {
    this.insert(webchatBlack);
    } else {
    this.update(webchatBlack);
    }
    }

    /**
    * 新增
    */
    private void insert(WebchatBlack webchatBlack) {
            Date now = new Date();
    webchatBlack.setId(UuidUtil.getShortUuid());
    webchatBlackMapper.insert(webchatBlack);
    }

    /**
    * 更新
    */
    private void update(WebchatBlack webchatBlack) {
    webchatBlackMapper.updateByPrimaryKey(webchatBlack);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    webchatBlackMapper.deleteByPrimaryKey(id);
    }
    }

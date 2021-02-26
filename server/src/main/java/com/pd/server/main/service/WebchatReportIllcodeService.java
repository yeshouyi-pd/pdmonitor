package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatReportIllcode;
import com.pd.server.main.domain.WebchatReportIllcodeExample;
import com.pd.server.main.dto.WebchatReportIllcodeDto;
import com.pd.server.main.mapper.WebchatReportIllcodeMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class WebchatReportIllcodeService {

@Resource
private WebchatReportIllcodeMapper webchatReportIllcodeMapper;

/**
* 列表查询
*/
public List list(WebchatReportIllcodeExample webchatReportIllcodeExample) {
    List<WebchatReportIllcode> webchatReportIllcodeList = webchatReportIllcodeMapper.selectByExample(webchatReportIllcodeExample);
    return webchatReportIllcodeList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatReportIllcodeDto webchatReportIllcodeDto) {
    WebchatReportIllcode webchatReportIllcode = CopyUtil.copy(webchatReportIllcodeDto, WebchatReportIllcode.class);
    if (StringUtils.isEmpty(webchatReportIllcodeDto.getId())) {
    this.insert(webchatReportIllcode);
    } else {
    this.update(webchatReportIllcode);
    }
    }

    /**
    * 新增
    */
    private void insert(WebchatReportIllcode webchatReportIllcode) {
            Date now = new Date();
    webchatReportIllcode.setId(UuidUtil.getShortUuid());
    webchatReportIllcodeMapper.insert(webchatReportIllcode);
    }

    /**
    * 更新
    */
    private void update(WebchatReportIllcode webchatReportIllcode) {
    webchatReportIllcodeMapper.updateByPrimaryKey(webchatReportIllcode);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    webchatReportIllcodeMapper.deleteByPrimaryKey(id);
    }
    }

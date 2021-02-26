package com.pd.server.main.service;

import com.pd.server.main.domain.WxPage;
import com.pd.server.main.domain.WxPageExample;
import com.pd.server.main.mapper.WxPageMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxPageService {

    @Resource
    private WxPageMapper wxPageMapper;



    /**
    * 新增
    */
    private void insert(WxPage wxPage) {
        wxPage.setId(UuidUtil.getShortUuid());
        wxPageMapper.insert(wxPage);
    }

    /**
    * 更新
    */
    private void update(WxPage wxPage) {
        wxPageMapper.updateByPrimaryKeySelective(wxPage);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        wxPageMapper.deleteByPrimaryKey(id);
    }


}

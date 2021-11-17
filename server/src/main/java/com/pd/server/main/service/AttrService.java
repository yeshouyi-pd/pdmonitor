package com.pd.server.main.service;

import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.Attr;
import com.pd.server.main.domain.AttrExample;
import com.pd.server.main.dto.AttrDto;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.mapper.AttrMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttrService {

    @Resource
    private AttrMapper attrMapper;

    /**
     * 根据code查询key
     * @param attrcode
     * @return
     */
    public String findByAttrKey(String attrcode){
        AttrMapper attrMapper = SpringUtil.getBean(AttrMapper.class);
        AttrExample example = new AttrExample();
        example.createCriteria().andAttrcodeEqualTo(attrcode);
        List<Attr> list = attrMapper.selectByExample(example);
        if(list.size() > 0){
            return list.get(0).getAttrkey();
        }
        return null;
    }

    /**
     * 列表查询
     */
    public void list(AttrDto attrDto) {
        PageHelper.startPage(attrDto.getPage(), attrDto.getSize());
        AttrExample attrExample = new AttrExample();
        AttrExample.Criteria ca = attrExample.createCriteria();
        if(!StringUtils.isEmpty(attrDto.getAttrcode())){
            ca.andAttrcodeEqualTo(attrDto.getAttrcode());
        }
        if(!StringUtils.isEmpty(attrDto.getContent())){
            ca.andContentLike("%"+attrDto.getContent()+"%");
        }
        attrExample.setOrderByClause( " attrcode asc ");
        List<Attr> attrList = attrMapper.selectByExample(attrExample);
        PageInfo<Attr> pageInfo = new PageInfo<>(attrList);
        attrDto.setTotal(pageInfo.getTotal());
        List<AttrDto> attrDtoList = CopyUtil.copyList(attrList, AttrDto.class);
        attrDto.setList(attrDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(AttrDto attrDto) {
        Attr attr = CopyUtil.copy(attrDto, Attr.class);
        if (!StringUtils.isEmpty(attrDto.getId())) {
            this.update(attr);
        } else {
            AttrExample example = new AttrExample();
            AttrExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(attrDto.getAttrcode())){
                ca.andAttrcodeEqualTo(attrDto.getAttrcode());
            }
            List<Attr> list = attrMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(list)) {
                this.insert(attr);
            } else {
                throw new BusinessException(BusinessExceptionCode.ATTR_CODE_EXIST);
            }

        }
    }

    /**
     * 新增
     */
    private void insert(Attr attr) {
        attr.setId(UuidUtil.getShortUuid());
        attrMapper.insert(attr);
    }

    /**
     * 更新
     */
    private void update(Attr attr) {
        attrMapper.updateByPrimaryKey(attr);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        attrMapper.deleteByPrimaryKey(id);
    }
}

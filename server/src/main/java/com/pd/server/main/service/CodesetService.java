package com.pd.server.main.service;

import com.pd.server.main.domain.Codeset;
import com.pd.server.main.domain.CodesetExample;
import com.pd.server.main.dto.CodesetDto;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.mapper.CodesetMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CodesetService {

@Resource
private CodesetMapper codesetMapper;

/**
* 列表查询
*/
    public List list(CodesetExample codesetExample) {
        List<Codeset> codesetList = codesetMapper.selectByExample(codesetExample);
        return codesetList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(CodesetDto codesetDto) {
    Codeset codeset = CopyUtil.copy(codesetDto, Codeset.class);
    if (StringUtils.isEmpty(codesetDto.getId())) {
        CodesetExample example = new CodesetExample();
        CodesetExample.Criteria ca = example.createCriteria();
        ca.andCodeEqualTo(codesetDto.getCode());
        ca.andTypeEqualTo(codesetDto.getType());
          if(!CollectionUtils.isEmpty(codesetMapper.selectByExample(example))){
            throw  new BusinessException(BusinessExceptionCode.CODE_TYPE_EXIST);
          }
        this.insert(codeset);
    } else {
    this.update(codeset);
    }
    }

    /**
    * 新增
    */
    private void insert(Codeset codeset) {
    codeset.setId(UuidUtil.getShortUuid());
    codesetMapper.insert(codeset);
    }

    /**
    * 更新
    */
    private void update(Codeset codeset) {
    codesetMapper.updateByPrimaryKey(codeset);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    codesetMapper.deleteByPrimaryKey(id);
    }

    public List<CodesetDto> getAlltype() {
        CodesetExample codesetExample = new CodesetExample();
        CodesetExample.Criteria ca = codesetExample.createCriteria();
        ca.andTypeEqualTo("alltype");
        codesetExample.setOrderByClause("id asc");
        List<Codeset> codesetList = codesetMapper.selectByExample(codesetExample);
        List<CodesetDto> codesetDtoList = CopyUtil.copyList(codesetList, CodesetDto.class);
        return codesetDtoList;
    }

    public List<CodesetDto> getNewtype(){
        CodesetExample codesetExample = new CodesetExample();
        CodesetExample.Criteria ca = codesetExample.createCriteria();
        ca.andTypeEqualTo("newtype");
        List<Codeset> codesetList = codesetMapper.selectByExample(codesetExample);
        List<CodesetDto> codesetDtoList = CopyUtil.copyList(codesetList, CodesetDto.class);
        return codesetDtoList;
    }

}

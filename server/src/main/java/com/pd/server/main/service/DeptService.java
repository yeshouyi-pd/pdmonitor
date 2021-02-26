package com.pd.server.main.service;

import com.pd.server.config.CodeType;
import com.pd.server.main.domain.Dept;
import com.pd.server.main.domain.DeptExample;
import com.pd.server.main.dto.DeptDto;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.mapper.DeptMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeptService {

    @Resource
    private DeptMapper deptMapper;

    /**
     * 列表查询
     */
    public List<Dept> list(DeptExample deptExample) {

        List<Dept> deptList = deptMapper.selectByExample(deptExample);
        return deptList;

    }

    /**
     * 新增
     */
    private void insert(Dept dept) {
        dept.setId(UuidUtil.getShortUuid());
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria ca = deptExample.createCriteria();
        ca.andDeptcodeEqualTo(dept.getDeptcode());
        List<Dept> list  = deptMapper.selectByExample(deptExample);
        if (!CollectionUtils.isEmpty(list)) {
            throw new BusinessException(BusinessExceptionCode.DEPT_CODE_EXIST);
        }
        deptMapper.insert(dept);
    }

    /**
     * 更新
     */
    private void update(Dept dept) {
        deptMapper.updateByPrimaryKey(dept);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        Dept vo = deptMapper.selectByPrimaryKey(id);
        if( null != vo ){
            DeptExample deptExample = new DeptExample();
            DeptExample.Criteria ca = deptExample.createCriteria();
            ca.andUpcodeEqualTo(vo.getDeptcode());
            if(!CollectionUtils.isEmpty(deptMapper.selectByExample(deptExample))){
                throw new BusinessException(BusinessExceptionCode.UPCODE_EXIST);
            }
        }

        deptMapper.deleteByPrimaryKey(id);
    }


    /**
     * 按约定将列表转成树
     * 要求：deptcode要正序排列
     * @return
     */
    public List<DeptDto> loadTree(List<String> deptCode) {
        DeptExample example = new DeptExample();
        DeptExample.Criteria ca = example.createCriteria();
        ca.andDeptcodeIn(deptCode);
        example.setOrderByClause("deptcode asc");
        List<Dept> deptList = deptMapper.selectByExample(example);
        List<DeptDto> deptDtoList = CopyUtil.copyList(deptList, DeptDto.class);

        return deptDtoList;
    }

    public List<DeptDto> getAllDept() {
        DeptExample example = new DeptExample();
        example.setOrderByClause("deptcode asc");
        List<Dept> deptList = deptMapper.selectByExample(example);
        List<DeptDto> deptDtoList = CopyUtil.copyList(deptList, DeptDto.class);
        return deptDtoList;
    }

    public  Dept queryById(String id){
        return deptMapper.selectByPrimaryKey(id);

    }

    public Dept queryByDeptcode(String deptcode){
        Dept  dept  = new Dept();
        DeptExample example = new DeptExample();
        DeptExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(deptcode)){
            ca.andDeptcodeEqualTo(deptcode);
            List<Dept> list = deptMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(list)){
                dept = list.get(0);
            }

        }

        return dept;

    }

    /**
     * 根据坐标查询最近的车管所
     * @param jd
     * @param wd
     * @return
     */
    public List<Dept> findByJdAndWd(String jd, String wd){
        Map<String, String> map = new HashMap<String, String>();
        map.put("jd", jd);
        map.put("wd", wd);
        return deptMapper.findByJdAndWd(map);
    }

    public void save(DeptDto deptDto) {
        Dept dept = CopyUtil.copy(deptDto, Dept.class);
        if (StringUtils.isEmpty(deptDto.getId())) {
            dept.setUpdateman("");
            dept.setUpdatetime(null);
            this.insert(dept);
        } else {
            dept.setCreateman("");
            dept.setCreatetime(null);
            this.update(dept);
        }
    }
}

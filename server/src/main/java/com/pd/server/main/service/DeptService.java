package com.pd.server.main.service;

import com.pd.server.config.CodeType;
import com.pd.server.main.domain.Dept;
import com.pd.server.main.domain.DeptExample;
import com.pd.server.main.domain.DeptYwlx;
import com.pd.server.main.domain.DeptYwlxExample;
import com.pd.server.main.dto.DeptDto;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.mapper.DeptMapper;
import com.pd.server.main.mapper.DeptYwlxMapper;
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


    @Resource
    private DeptYwlxMapper deptYwlxMapper;

    /**
     * 添加部门关联的业务类型
     */
    public void addYwlxByDept(String deptcode,String ywfl,String ywlx){
        DeptYwlxExample example = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = example.createCriteria();
        ca.andDeptcodeEqualTo(deptcode);
        ca.andYwflEqualTo(ywfl);
        ca.andYwlxEqualTo(ywlx);
        Long count = deptYwlxMapper.countByExample(example);
        if(count > new Long(0)){//修改
            DeptYwlx vo = new DeptYwlx();
            vo.setZt("Y");//启用
            deptYwlxMapper.updateByExampleSelective(vo,example);

        }else{//添加
            DeptYwlx vo = new DeptYwlx();
            vo.setZt("Y");//启用
            vo.setId(UuidUtil.getShortUuid());
            vo.setYwfl(ywfl);
            vo.setDeptcode(deptcode);
            vo.setYwlx(ywlx);
            deptYwlxMapper.insert(vo);
        }

    }

    public void updateYwlxByDeptN(String deptcode){
        DeptYwlxExample example = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = example.createCriteria();
        ca.andDeptcodeEqualTo(deptcode);
        DeptYwlx vo = new DeptYwlx();
        vo.setZt("N");//禁用
        deptYwlxMapper.updateByExampleSelective(vo,example);

    }

    /**
     * 列表查询
     */
    public List<Dept> list(DeptExample deptExample) {

        List<Dept> deptList = deptMapper.selectByExample(deptExample);
        return deptList;

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Transactional
    public void save(DeptDto deptDto) {
        /**
         * 需要办理的业务
         * 注意：由于后期需要关联业务受理 随意不会直接全部删除后再添加
         * 所有的均不会删除 添加状态标记即可
         * 即使操作失误没有勾选 这样业务对应的流程信息也不会删除
         */
        this.updateYwlxByDeptN(deptDto.getDeptcode());
        List<String> checkjdcywVal =deptDto.getCheckjdcywVal();
        /**
         * 添加机动车可受理业务类型
         */
        if(!CollectionUtils.isEmpty(checkjdcywVal)){
            for(String ywlx :checkjdcywVal){
                if(!StringUtils.isEmpty(ywlx)){
                    this.addYwlxByDept(deptDto.getDeptcode(), CodeType.CODE_JDC_YWLX,ywlx);
                }
            }
        }
        List<String> checkjsrywVal =deptDto.getCheckjsrywVal();
        /**
         * 添加驾驶人可受理业务类型
         */
        if(!CollectionUtils.isEmpty(checkjsrywVal)){
            for(String ywlx :checkjsrywVal){
                if(!StringUtils.isEmpty(ywlx)){
                    this.addYwlxByDept(deptDto.getDeptcode(), CodeType.CODE_JSR_YWLX,ywlx);
                }
            }
        }
        List<String> checkfwywVal  =deptDto.getCheckfwywVal();
        /**
         * 添加违法可受理业务类型
         */
        if(!CollectionUtils.isEmpty(checkfwywVal)){
            for(String ywlx :checkfwywVal){
                if(!StringUtils.isEmpty(ywlx)){
                    this.addYwlxByDept(deptDto.getDeptcode(), CodeType.CODE_WF_YWLX,ywlx);
                }
            }
        }
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

}

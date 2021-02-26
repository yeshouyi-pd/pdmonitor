package com.pd.server.main.service;

import com.pd.server.config.CodeType;
import com.pd.server.main.domain.DeptYwlx;
import com.pd.server.main.domain.DeptYwlxExample;
import com.pd.server.main.dto.DeptYwlxDto;
import com.pd.server.main.dto.DeptYwlxPageDto;
import com.pd.server.main.mapper.DeptYwlxMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeptYwlxService {

    @Resource
    private DeptYwlxMapper deptYwlxMapper;
    /**
     * 列表查询
     */
    public List<DeptYwlxDto> listallbydept(DeptYwlxPageDto pageDto) {
        DeptYwlxExample deptYwlxExample = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = deptYwlxExample.createCriteria();
        ca.andDeptcodeEqualTo(pageDto.getDeptcode());
        deptYwlxExample.setOrderByClause("ywfl ");
        List<DeptYwlx> deptYwlxList = deptYwlxMapper.selectByExample(deptYwlxExample);
        List<DeptYwlxDto> deptYwlxDtoList = CopyUtil.copyList(deptYwlxList, DeptYwlxDto.class);
        return deptYwlxDtoList;
    }

    public List<DeptYwlx> listAllByDeptAndYw(DeptYwlxDto deptYwlxDto){
        DeptYwlxExample deptYwlxExample = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = deptYwlxExample.createCriteria();
        ca.andDeptcodeEqualTo(deptYwlxDto.getDeptcode());
        ca.andYwflEqualTo(deptYwlxDto.getYwfl());
        ca.andYwlxEqualTo(deptYwlxDto.getYwlx());
        List<DeptYwlx> deptYwlxList = deptYwlxMapper.selectByExample(deptYwlxExample);
        return deptYwlxList;
    }


    /**
     * 列表查询
     */
    public void list(DeptYwlxPageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DeptYwlxExample deptYwlxExample = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = deptYwlxExample.createCriteria();
        ca.andDeptcodeEqualTo(pageDto.getDeptcode());
        deptYwlxExample.setOrderByClause("ywfl ");
        List<DeptYwlx> deptYwlxList = deptYwlxMapper.selectByExample(deptYwlxExample);
        PageInfo<DeptYwlx> pageInfo = new PageInfo<>(deptYwlxList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DeptYwlxDto> deptYwlxDtoList = CopyUtil.copyList(deptYwlxList, DeptYwlxDto.class);
        pageDto.setList(deptYwlxDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(DeptYwlxDto deptYwlxDto) {
        DeptYwlx deptYwlx = CopyUtil.copy(deptYwlxDto, DeptYwlx.class);
        if (StringUtils.isEmpty(deptYwlxDto.getId())) {
            this.insert(deptYwlx);
        } else {
            this.update(deptYwlx);
        }
    }

    /**
     * 新增
     */
    private void insert(DeptYwlx deptYwlx) {
        deptYwlx.setId(UuidUtil.getShortUuid());
        deptYwlxMapper.insert(deptYwlx);
    }

    /**
     * 更新
     */
    private void update(DeptYwlx deptYwlx) {
        deptYwlxMapper.updateByPrimaryKey(deptYwlx);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        DeptYwlx deptYwlx = new DeptYwlx();
        deptYwlx.setId(id);
        deptYwlx.setZt("N");
        deptYwlxMapper.updateByPrimaryKey(deptYwlx);
    }

    /**
     * 根据用户部门获取当前用的的所有能办理的业务
     * @param deptcode
     * @return
     */
    public List<DeptYwlxDto> selectByDept(String deptcode) {
        DeptYwlxExample deptYwlxExample = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = deptYwlxExample.createCriteria();
        ca.andZtEqualTo("Y");//启用
        ca.andDeptcodeEqualTo(deptcode);
        deptYwlxExample.setOrderByClause(" ywfl ,ywlx asc");
        List<DeptYwlx>    list   = deptYwlxMapper.selectByExample(deptYwlxExample);
        List<DeptYwlxDto> deptYwlxDtoList = CopyUtil.copyList(list, DeptYwlxDto.class);
        return deptYwlxDtoList;
    }


    /**
     * 批量修改 添加
     * @param deptYwlxDto
     */
    public void saveall(DeptYwlxDto deptYwlxDto) {
        DeptYwlx deptYwlx = CopyUtil.copy(deptYwlxDto, DeptYwlx.class);
        //机动车
        if(!CollectionUtils.isEmpty(deptYwlxDto.getCheckjdcywVal())){
            deptYwlx.setYwfl(CodeType.CODE_JDC_YWLX);
            for(String key: deptYwlxDto.getCheckjdcywVal()){
                deptYwlx.setYwlx(key);
                this.dosaveall(deptYwlx);
            }

        }
        //驾驶人
        if(!CollectionUtils.isEmpty(deptYwlxDto.getCheckjsrywVal())){
            deptYwlx.setYwfl(CodeType.CODE_JSR_YWLX);
            for(String key: deptYwlxDto.getCheckjsrywVal()){
                deptYwlx.setYwlx(key);
                this.dosaveall(deptYwlx);
            }
        }
        //违法
        if(!CollectionUtils.isEmpty(deptYwlxDto.getCheckfwywVal())){
            deptYwlx.setYwfl(CodeType.CODE_WF_YWLX);
            for(String key: deptYwlxDto.getCheckfwywVal()){
                deptYwlx.setYwlx(key);
                this.dosaveall(deptYwlx);
            }
        }

    }


    /**
     * 保存，id有值时更新，无值时新增
     */
    public void dosaveall( DeptYwlx deptYwlx) {
            //只做修改
        DeptYwlxExample deptYwlxExample = new DeptYwlxExample();
        DeptYwlxExample.Criteria ca = deptYwlxExample.createCriteria();
        ca.andYwflEqualTo(deptYwlx.getYwfl());
        ca.andYwlxEqualTo(deptYwlx.getYwlx());
        ca.andDeptcodeEqualTo(deptYwlx.getDeptcode());
        deptYwlxMapper.updateByExampleSelective(deptYwlx,deptYwlxExample);


    }


    /**
     * 根据条件查询出唯一一条记录
     * @param deptYwlxExample
     * @return
     */
    public DeptYwlx queryOneByExample(DeptYwlxExample deptYwlxExample ){
        DeptYwlx  vo  = new DeptYwlx();
        List<DeptYwlx> list = deptYwlxMapper.selectByExample(deptYwlxExample);
        if(!CollectionUtils.isEmpty(list)){
            vo = list.get(0);
        }
        return  vo;

    }
}

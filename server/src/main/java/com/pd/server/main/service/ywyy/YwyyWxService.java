package com.pd.server.main.service.ywyy;

import com.pd.server.main.domain.Dept;
import com.pd.server.main.domain.WxYy;
import com.pd.server.main.domain.WxYyExample;
import com.pd.server.main.dto.DeptDto;
import com.pd.server.main.dto.WxYyDto;
import com.pd.server.main.dto.basewx.my.YwyyParamDto;
import com.pd.server.main.mapper.WxYyMapper;
import com.pd.server.main.mapper.my.MyDeptMapper;
import com.pd.server.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YwyyWxService {

    @Resource
    private MyDeptMapper myDeptMapper;


    @Resource
    private WxYyMapper wxYyMapper;



    /**
     * 根据微信页面选择的业务分类和业务类型查询
     * 能办理改业务的所有部门
     * @param ywyyParamDto
     * @return
     */
    public List<DeptDto> getDeptByYwlx(YwyyParamDto ywyyParamDto){
        List<Dept> deptList = myDeptMapper.getDeptByYwlx(ywyyParamDto);
        List<DeptDto> categoryDtoList = CopyUtil.copyList(deptList, DeptDto.class);
        return categoryDtoList;
    }


    /**
     * 保存预约信息
     * @param wxYyDto
     */
    public void savewwyy(WxYyDto wxYyDto) {
        WxYy vo = CopyUtil.copy(wxYyDto ,WxYy.class);
        wxYyMapper.insert(vo);
    }

    public WxYyDto queryById(String id) {
        WxYy  vo  = wxYyMapper.selectByPrimaryKey(id);
        WxYyDto info = CopyUtil.copy(vo ,WxYyDto.class);
        return  info;
    }


    public List<WxYyDto> selectByExample(WxYyExample example){
        List<WxYy>  list  =  wxYyMapper.selectByExample(example);
        List<WxYyDto> wxYyDtolist = CopyUtil.copyList(list, WxYyDto.class);
        return wxYyDtolist;

    }

    public int updateById(WxYy wxYy){
        return wxYyMapper.updateByPrimaryKeySelective(wxYy);
    }
}

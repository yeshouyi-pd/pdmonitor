package com.pd.server.main.service;

import com.pd.server.main.domain.DrvExam;
import com.pd.server.main.domain.DrvExamExample;
import com.pd.server.main.dto.DrvExamDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.mapper.DrvExamMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DrvExamService {

    @Resource
    private DrvExamMapper drvExamMapper;
    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    /**
    * 列表查询
    */
    public void list(DrvExamDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DrvExamExample drvExamExample = new DrvExamExample();
        DrvExamExample.Criteria ca =  drvExamExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getKskm())){
            ca.andKskmEqualTo(pageDto.getKskm());
        }
        if(!StringUtils.isEmpty(pageDto.getDtlb())){
            ca.andDtlbEqualTo(pageDto.getDtlb());
        }
        if(!StringUtils.isEmpty(pageDto.getTmbt())){
            ca.andTmbtLike("%"+pageDto.getTmbt()+"%");
        }
        if(!StringUtils.isEmpty(pageDto.getA())){
            ca.andALike("%"+pageDto.getA()+"%");
        }
        List<DrvExam> drvExamList = drvExamMapper.selectByExample(drvExamExample);
        PageInfo<DrvExam> pageInfo = new PageInfo<>(drvExamList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DrvExamDto> drvExamDtoList = CopyUtil.copyList(drvExamList, DrvExamDto.class);
        pageDto.setList(drvExamDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(DrvExamDto drvExamDto) {
        DrvExam drvExam = CopyUtil.copy(drvExamDto, DrvExam.class);
        if (StringUtils.isEmpty(drvExamDto.getId())) {
            this.insert(drvExam);
        } else {
            this.update(drvExam);
        }
        webchatFileinfoService.deleteByLsh(drvExam.getId());
        //保存图片
        if(!StringUtils.isEmpty(drvExamDto.getTp1())){
            List<String> tp1List = Arrays.asList(drvExamDto.getTp1().split(","));
            for(int i=0;i<tp1List.size();i++){
                WebchatFileinfoDto webchatFileinfoDto = new WebchatFileinfoDto();
                webchatFileinfoDto.setBz("tp1");
                webchatFileinfoDto.setLsh(drvExam.getId());
                webchatFileinfoDto.setCjsj(new Date());
                webchatFileinfoDto.setZplj(tp1List.get(i));
                webchatFileinfoService.save(webchatFileinfoDto);
            }
        }
        //保存图片
        if(!StringUtils.isEmpty(drvExamDto.getTp2())){
            List<String> tp2List = Arrays.asList(drvExamDto.getTp2().split(","));
            for(int i=0;i<tp2List.size();i++){
                WebchatFileinfoDto webchatFileinfoDto = new WebchatFileinfoDto();
                webchatFileinfoDto.setBz("tp2");
                webchatFileinfoDto.setLsh(drvExam.getId());
                webchatFileinfoDto.setCjsj(new Date());
                webchatFileinfoDto.setZplj(tp2List.get(i));
                webchatFileinfoService.save(webchatFileinfoDto);
            }
        }
    }

    /**
    * 新增
    */
    private void insert(DrvExam drvExam) {
        drvExam.setId(UuidUtil.getShortUuid());
        drvExamMapper.insert(drvExam);
    }

    /**
    * 更新
    */
    private void update(DrvExam drvExam) {
        drvExamMapper.updateByPrimaryKeySelective(drvExam);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        drvExamMapper.deleteByPrimaryKey(id);
    }

    /**
     * 随机查询出100条数据
     */
    public List<DrvExam> selectByLimit(){
        return drvExamMapper.selectByLimit();
    }
}

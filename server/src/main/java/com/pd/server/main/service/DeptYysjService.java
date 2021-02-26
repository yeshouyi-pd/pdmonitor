package com.pd.server.main.service;

import com.pd.server.main.domain.DeptYysj;
import com.pd.server.main.domain.DeptYysjExample;
import com.pd.server.main.dto.DeptYysjDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.mapper.DeptYysjMapper;
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
public class DeptYysjService {

    @Resource
    private DeptYysjMapper deptYysjMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DeptYysjExample deptYysjExample = new DeptYysjExample();
        List<DeptYysj> deptYysjList = deptYysjMapper.selectByExample(deptYysjExample);
        PageInfo<DeptYysj> pageInfo = new PageInfo<>(deptYysjList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DeptYysjDto> deptYysjDtoList = CopyUtil.copyList(deptYysjList, DeptYysjDto.class);
        pageDto.setList(deptYysjDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(DeptYysjDto deptYysjDto) {
        DeptYysj deptYysj = CopyUtil.copy(deptYysjDto, DeptYysj.class);

        if(!compTimeor(deptYysj.getStime(),deptYysj.getEtime())){
            throw  new BusinessException(BusinessExceptionCode.YYTIME_BEFOR_VAILD);
        }
        //首先比较两个时间的大小 关系
        //查出所有记录 比较开始时间断不在任意一个已存在的时段内
        //结束时间段也 不在任意一个时段内即可
        DeptYysjExample  example = new DeptYysjExample();
        DeptYysjExample.Criteria ca  = example.createCriteria();
        ca.andDeptcodeEqualTo(deptYysj.getDeptcode());
        List<DeptYysj> list = deptYysjMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            for(DeptYysj vo : list){
                //先排除相等记录 //新增的时候排除
                if(deptYysj.getStime().equals(vo.getStime())&&
                        deptYysj.getEtime().equals(vo.getEtime())
                        &&StringUtils.isEmpty(deptYysj.getId())){
                    //抛出异常
                    throw  new BusinessException(BusinessExceptionCode.YYTIME_EXIST);
                }
                Boolean flag = compTime(deptYysj.getStime(),deptYysj.getEtime(),vo.getStime(),vo.getEtime());

                if(!flag ){
                    throw  new BusinessException(BusinessExceptionCode.YYTIME_EXIST);

                }

            }
        }
        if (StringUtils.isEmpty(deptYysjDto.getId())) {
            this.insert(deptYysj);
        } else {
            this.update(deptYysj);
        }
    }

    /**
     * 新增
     */
    private void insert(DeptYysj deptYysj) {
        deptYysj.setId(UuidUtil.getShortUuid());
        deptYysjMapper.insert(deptYysj);
    }

    /**
     * 更新
     */
    private void update(DeptYysj deptYysj) {
        deptYysjMapper.updateByPrimaryKey(deptYysj);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        deptYysjMapper.deleteByPrimaryKey(id);
    }


    /**
     * 比较当前时间是否再时段内
     * @param nowstime
     * @param nowntime
     * @param ntime
     * @return
     */

    public static boolean compTime(String nowstime, String nowntime ,String stime, String ntime) {
        if (nowstime.indexOf(":") < 0 || nowntime.indexOf(":") < 0 || stime.indexOf(":") < 0|| ntime.indexOf(":") < 0) {
            return  false;
        } else {
            String[] nowstimes = nowstime.split(":");
            int nowstimeint = Integer.valueOf(nowstimes[0]) * 3600 + Integer.valueOf(nowstimes[1]) * 60;//当前开始时间
            String[] nowntimes = nowntime.split(":");
            int nowntimeint = Integer.valueOf(nowntimes[0]) * 3600 + Integer.valueOf(nowntimes[1]) * 60;//当前结束时间
            String[] stimes = stime.split(":");
            int stimeint = Integer.valueOf(stimes[0]) * 3600 + Integer.valueOf(stimes[1]) * 60;//数据库开始时间
            String[] ntimes = ntime.split(":");
            int ntimeint = Integer.valueOf(ntimes[0]) * 3600 + Integer.valueOf(ntimes[1]) * 60;//结束时间
            if (nowstimeint > stimeint && nowstimeint < ntimeint) {//开始大于开始时间 小于结束时间则表示在当前范围内
                return  false;
            }else{
                if (nowntimeint > stimeint && nowntimeint < ntimeint) {//开始大于开始时间 小于结束时间则表示在当前范围内
                    return  false;
                }else{
                    //判断数据库的开始时间时间也不在当前区间内
                    if (stimeint > nowstimeint && stimeint < nowntimeint) {//开始大于开始时间 小于结束时间则表示在当前范围内
                        return  false;
                    }else{
                        //判断数据库的结束时间时间也不在当前区间内
                        if (ntimeint > nowstimeint && ntimeint < nowntimeint) {//开始大于开始时间 小于结束时间则表示在当前范围内
                            return  false;
                        }else{

                            return  true;
                        }
                    }
                }

            }
        }

    }


    /**
     * 时间比较
     * @param stime
     * @param ntime
     * @return
     */
    public static boolean compTimeor(String stime, String ntime ){
        if ( stime.indexOf(":") < 0 || stime.indexOf(":") < 0) {
            return  false;
        } else {
              String[] stimes = stime.split(":");
            int stimeint = Integer.valueOf(stimes[0]) * 3600 + Integer.valueOf(stimes[1]) * 60;
            String[] ntimes = ntime.split(":");
            int ntimeint = Integer.valueOf(ntimes[0]) * 3600 + Integer.valueOf(ntimes[1]) * 60;
            if (stimeint < ntimeint ) {//小于开始时间 大于结束时间则表示不在 当前范围内
                return  true;
            }else{
                return  false;
            }


        }

    }

    public List<DeptYysjDto> queryList(String deptocde) {
        DeptYysjExample  example = new DeptYysjExample();
        DeptYysjExample.Criteria ca  = example.createCriteria();
        ca.andDeptcodeEqualTo(deptocde);
        example.setOrderByClause("stime asc");
        List<DeptYysj> list = deptYysjMapper.selectByExample(example);
        List<DeptYysjDto> deptYysjDtoList = CopyUtil.copyList(list, DeptYysjDto.class);
        return deptYysjDtoList;
    }



}

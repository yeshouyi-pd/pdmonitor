package com.pd.server.main.service;

import com.pd.server.main.domain.SysLog;
import com.pd.server.main.domain.SysLogExample;
import com.pd.server.main.dto.SysLogDto;
import com.pd.server.main.mapper.SysLogMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;




    /**
     * @param czr 操作人
     * @param czip 操作ip
     * @param czcd 操作菜单
     * @param czsm 操作说明
     * @param czjg 操作结果 | 1 成功 0 失败
     * @param czsbyy 操作失败原因
     * @param cctj 操作条件
     * @param qqsj 请求数据
     * @param ffsj 返回数据
     * rzlx 日志类型 | 1 系统日志 2 微信日志  3接口日志
     */
    public  void addLog(String czr ,String czip,String czcd,String czsm,
                              String czjg ,String czsbyy,String cctj,String qqsj,
                              String ffsj,String rzlx){
        SysLog sysLog = new SysLog();
        sysLog.setId(UuidUtil.getShortUuid());
        sysLog.setCzsj(new Date());
        sysLog.setCzr(czr);
        sysLog.setCzip(czip);
        sysLog.setCzcd(czcd);
        sysLog.setCzsm(czsm);
        sysLog.setCzjg(czjg);
        sysLog.setCzsbyy(czsbyy);
        sysLog.setCztj(cctj);
        sysLog.setQqsj(qqsj);
        sysLog.setFfsj(ffsj);
        sysLog.setRzlx(rzlx);
        sysLogMapper.insert(sysLog);

    }


    /**
     * 列表查询
     */
    public List<SysLog> list(SysLogExample sysLogExample) {
        List<SysLog> sysLogList = sysLogMapper.selectByExample(sysLogExample);
        return   sysLogList;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(SysLogDto sysLogDto) {
        SysLog sysLog = CopyUtil.copy(sysLogDto, SysLog.class);
        if (StringUtils.isEmpty(sysLogDto.getId())) {
            this.insert(sysLog);
        } else {
            this.update(sysLog);
        }
    }

    /**
     * 新增
     */
    private void insert(SysLog sysLog) {
        sysLog.setId(UuidUtil.getShortUuid());
        sysLogMapper.insert(sysLog);
    }

    /**
     * 更新
     */
    private void update(SysLog sysLog) {
        sysLogMapper.updateByPrimaryKey(sysLog);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        sysLogMapper.deleteByPrimaryKey(id);
    }
}

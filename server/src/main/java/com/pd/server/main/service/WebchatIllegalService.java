package com.pd.server.main.service;


import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WebchatIllegalDto;
import com.pd.server.main.domain.WebchatIllegal;
import com.pd.server.main.domain.WebchatIllegalExample;
import com.pd.server.main.domain.WebchatReportIllcode;
import com.pd.server.main.mapper.WebchatIllegalMapper;
import com.pd.server.main.mapper.WebchatReportIllcodeMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
        import java.util.Date;

@Service
public class WebchatIllegalService {

@Resource
private WebchatIllegalMapper webchatIllegalMapper;

@Resource
private WebchatReportIllcodeMapper webchatReportIllcodeMapper;

/**
* 列表查询
*/
public List list(WebchatIllegalExample webchatIllegalExample) {
    List<WebchatIllegal> webchatIllegalList = webchatIllegalMapper.selectByExample(webchatIllegalExample);
    return webchatIllegalList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatIllegalDto webchatIllegalDto, LoginUserDto loginUserDto) throws Exception {
        Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    WebchatIllegal webchatIllegal = CopyUtil.copy(webchatIllegalDto, WebchatIllegal.class);
    if (!StringUtils.isEmpty(webchatIllegalDto.getId())) {
        if (Integer.valueOf(webchatIllegalDto.getZt()).intValue() == 1){
            webchatIllegal.setZt("2");
        }
        webchatIllegal.setShr(loginUserDto.getLoginName());
        webchatIllegal.setSysOrgCode(loginUserDto.getDeptcode());
        webchatIllegal.setShsj(sbf.parse(sbf.format(d)));
        webchatIllegal.setSfzmhm(DesEncrypt.DesJiaMi(webchatIllegalDto.getSfzmhm()));
        webchatIllegal.setSjh(DesEncrypt.DesJiaMi(webchatIllegalDto.getSjh()));
        webchatIllegal.setUpdateTime(sbf.parse(sbf.format(d)));
        webchatIllegal.setUpdateBy(loginUserDto.getLoginName());


    this.update(webchatIllegal);
    }
    }


    /**
    * 新增
    */
    private void insert(WebchatIllegal webchatIllegal) {
            Date now = new Date();
    webchatIllegal.setId(UuidUtil.getShortUuid());
    webchatIllegalMapper.insert(webchatIllegal);
    }

    /**
    * 更新
    */
    private void update(WebchatIllegal webchatIllegal) {
    webchatIllegalMapper.updateByPrimaryKey(webchatIllegal);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    webchatIllegalMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取需要奖励的奖励金额
     */
    public WebchatReportIllcode getJljeByCode(String wfxwxx) {

        WebchatReportIllcode webchatFileinfoList = webchatReportIllcodeMapper.selectByWfxwxx(wfxwxx);
        return webchatFileinfoList;
    }
    }

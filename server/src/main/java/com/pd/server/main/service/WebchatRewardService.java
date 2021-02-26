package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatIllegalSave;
import com.pd.server.main.domain.WebchatReward;
import com.pd.server.main.domain.WebchatRewardExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WebchatRewardDto;
import com.pd.server.main.mapper.WebchatIllegalSaveMapper;
import com.pd.server.main.mapper.WebchatRewardMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
        import java.util.Date;

@Service
public class WebchatRewardService {

@Resource
private WebchatRewardMapper webchatRewardMapper;
@Resource
private WebchatIllegalSaveMapper webchatIllegalSaveMapper;

/**
* 列表查询
*/
public List<WebchatReward> list(WebchatRewardExample webchatIllegalExample) {
    List<WebchatReward> webchatIllegalList = webchatRewardMapper.selectByExample(webchatIllegalExample);
    return webchatIllegalList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(WebchatRewardDto webchatRewardDto, LoginUserDto loginUserDto) throws Exception {
        Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     WebchatIllegalSave record = new WebchatIllegalSave();
        record.setId(webchatRewardDto.getId());

        WebchatReward webchatReward = CopyUtil.copy(webchatRewardDto, WebchatReward.class);
        if(Integer.valueOf(webchatRewardDto.getZt()).intValue() == 2){
            webchatReward.setZt("4");
        }
        webchatReward.setUpdateTime(sbf.parse(sbf.format(d)));
        webchatReward.setFfzt("0");
        webchatReward.setSfzmhm(DesEncrypt.DesJiaMi(webchatRewardDto.getSfzmhm()));
        webchatReward.setSjh(DesEncrypt.DesJiaMi(webchatRewardDto.getSjh()));
        webchatReward.setUpdateBy(loginUserDto.getLoginName());
        webchatReward.setShr(loginUserDto.getLoginName());
        webchatReward.setShsj(sbf.parse(sbf.format(d)));

        this.insert(webchatReward);

        webchatIllegalSaveMapper.updateById(record.getId());
    }


    /**
    * 新增
    */
    private void insert(WebchatReward webchatReward) {
            Date now = new Date();
    webchatReward.setId(UuidUtil.getShortUuid());
    webchatRewardMapper.insert(webchatReward);
    }

    /**
    * 更新
    */
    private void update(WebchatReward webchatReward) {
    webchatRewardMapper.updateByPrimaryKey(webchatReward);
    }

    /**
     * 更新
     */
    public void updateById(String  id, LoginUserDto loginUserDto) {
        WebchatReward webchatReward = new WebchatReward();
        webchatReward.setId(id);
        webchatReward.setFfzt("1");
        webchatReward.setFfr(loginUserDto.getLoginName());
        webchatReward.setFfsj(new Date());
        webchatReward.setUpdateBy(loginUserDto.getLoginName());
        webchatReward.setUpdateTime(new Date());
        webchatRewardMapper.updateById(webchatReward);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    webchatRewardMapper.deleteByPrimaryKey(id);
    }
    }

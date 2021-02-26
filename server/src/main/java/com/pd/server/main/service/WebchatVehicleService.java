package com.pd.server.main.service;

import com.pd.server.main.domain.WebchatVehicle;
import com.pd.server.main.domain.WebchatVehicleExample;
import com.pd.server.main.dto.WebchatVehicleDto;
import com.pd.server.main.mapper.WebchatVehicleMapper;
import com.pd.server.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
@Service
public class WebchatVehicleService {

    @Resource
    private WebchatVehicleMapper webchatVehicleMapper;
    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(WebchatVehicleDto wxVehicleDto) {
        WebchatVehicle wxVehicle = CopyUtil.copy(wxVehicleDto, WebchatVehicle.class);
        if (StringUtils.isEmpty(wxVehicleDto.getId())) {
            this.insert(wxVehicle);
        } else {
            this.update(wxVehicle);
        }
    }

    /**
     * 新增
     */
    private void insert(WebchatVehicle wxVehicle) {
        webchatVehicleMapper.insert(wxVehicle);
    }

    /**
     * 更新
     */
    private void update(WebchatVehicle wxVehicle) {
        webchatVehicleMapper.updateByPrimaryKeySelective(wxVehicle);
    }

    /**
     * 删除
     */
    public void delete(int id) {
        webchatVehicleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 身份证号码查询
     */
    public List<WebchatVehicle> findBySfzmhm(String sfzmhm) {
        WebchatVehicleExample example = new WebchatVehicleExample();
        WebchatVehicleExample.Criteria ca = example.createCriteria();
        ca.andSfzmhmEqualTo(sfzmhm);
        List<WebchatVehicle> list = webchatVehicleMapper.selectByExample(example);
        return list;
    }

    /**
     * 号牌号码查询
     */
    public WebchatVehicle findByHphm(String hphm) {
        WebchatVehicleExample example = new WebchatVehicleExample();
        WebchatVehicleExample.Criteria ca = example.createCriteria();
        ca.andHphmEqualTo(hphm);
        List<WebchatVehicle> list = webchatVehicleMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            return  list.get(0);
        }
        return null;
    }

}

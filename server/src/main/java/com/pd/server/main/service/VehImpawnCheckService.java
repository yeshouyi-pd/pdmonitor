package com.pd.server.main.service;

import com.pd.server.main.domain.VehImpawnCheck;
import com.pd.server.main.domain.VehImpawnCheckExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehImpawnCheckDto;
import com.pd.server.main.mapper.VehImpawnCheckMapper;
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
public class VehImpawnCheckService {

@Resource
private VehImpawnCheckMapper vehImpawnCheckMapper;

    /**
     * 列表查询
     */
    public List<VehImpawnCheck> list(VehImpawnCheckExample vehImpawnCheckExample) {
        List<VehImpawnCheck> vehImpawnCheckList = vehImpawnCheckMapper.selectByExample(vehImpawnCheckExample);

        return vehImpawnCheckList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehImpawnCheckDto vehImpawnCheckDto, LoginUserDto loginUserDto) throws Exception {
    Date d = new Date();
    SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    VehImpawnCheck vehImpawnCheck = CopyUtil.copy(vehImpawnCheckDto, VehImpawnCheck.class);
    if (!StringUtils.isEmpty(vehImpawnCheckDto.getId())) {
        if(Integer.valueOf(vehImpawnCheckDto.getZt()).intValue() == 1){
            vehImpawnCheck.setZt("2");
        }
        vehImpawnCheck.setGlbm(loginUserDto.getDeptcode());
        vehImpawnCheck.setShr(loginUserDto.getLoginName());
        vehImpawnCheck.setShsj(sbf.parse(sbf.format(d)));
        vehImpawnCheck.setSfzmhm(DesEncrypt.DesJiaMi(vehImpawnCheckDto.getSfzmhm()));
        vehImpawnCheck.setLxdh(DesEncrypt.DesJiaMi(vehImpawnCheckDto.getLxdh()));
        vehImpawnCheck.setDyqrsfzmhm(DesEncrypt.DesJiaMi(vehImpawnCheckDto.getDyqrsfzmhm()));
        vehImpawnCheck.setSjhm(DesEncrypt.DesJiaMi(vehImpawnCheckDto.getSjhm()));
        this.update(vehImpawnCheck);
    }
    }

    /**
    * 新增
    */
    private void insert(VehImpawnCheck vehImpawnCheck) {
            Date now = new Date();
    vehImpawnCheck.setId(UuidUtil.getShortUuid());
    vehImpawnCheckMapper.insert(vehImpawnCheck);
    }

    /**
    * 更新
    */
    private void update(VehImpawnCheck vehImpawnCheck) {
    vehImpawnCheckMapper.updateByPrimaryKey(vehImpawnCheck);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehImpawnCheckMapper.deleteByPrimaryKey(id);
    }
    }

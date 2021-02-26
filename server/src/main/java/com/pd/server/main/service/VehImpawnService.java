package com.pd.server.main.service;

import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.VehImpawn;
import com.pd.server.main.domain.VehImpawnExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehImpawnDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.mapper.VehImpawnMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.UuidUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
        import java.util.Date;
import java.util.Map;

@Service
public class VehImpawnService {

@Resource
private RedisTemplate redisTemplate;

@Resource
private VehImpawnMapper vehImpawnMapper;
    @Resource
    private WebchatFileinfoService webchatFileinfoService;

/**
* 列表查询
*/
public List<VehImpawn> list(VehImpawnExample vehImpawnExample) {
    List<VehImpawn> vehLogoutList = vehImpawnMapper.selectByExample(vehImpawnExample);

    return vehLogoutList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(VehImpawnDto vehImpawnDto, LoginUserDto loginUserDto) throws Exception {
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        VehImpawn vehImpawn = CopyUtil.copy(vehImpawnDto, VehImpawn.class);

        vehImpawn.setUpdateTime(sbf.parse(sbf.format(d)));
        vehImpawn.setUpdateBy(loginUserDto.getLoginName());
        vehImpawn.setJbr(loginUserDto.getLoginName());
        vehImpawn.setSfzmhm(DesEncrypt.DesJiaMi(vehImpawnDto.getSfzmhm()));
        vehImpawn.setLxdh(DesEncrypt.DesJiaMi(vehImpawnDto.getLxdh()));
        vehImpawn.setDyqrsfzmhm(DesEncrypt.DesJiaMi(vehImpawnDto.getDyqrsfzmhm()));
        vehImpawn.setSjhm(DesEncrypt.DesJiaMi(vehImpawnDto.getSjhm()));


        map = allmap.get(CodeType.CODE_XX_DYQR);
//        vehImpawn.setDyqrlx(vehImpawnDto.getDyqrlx());
//        vehImpawn.setDyqrdm(vehImpawnDto.getDyqrlx());

        if (StringUtils.isEmpty(vehImpawnDto.getId())) {
            vehImpawn.setCreateBy(loginUserDto.getLoginName());
            vehImpawn.setSysOrgCode(loginUserDto.getDeptcode());
            vehImpawn.setCreateTime(sbf.parse(sbf.format(d)));
            vehImpawn.setLsh(UuidUtil.getUuid());
            vehImpawn.setZt("1");
            this.insert(vehImpawn);
        } else {
            this.update(vehImpawn);
        }
        if("1".equals(vehImpawnDto.getZt())){
            if(!StringUtils.isEmpty(vehImpawnDto.getId())){
                webchatFileinfoService.deleteByLsh(vehImpawnDto.getLsh());
            }
            if(!StringUtils.isEmpty(vehImpawnDto.getTpListStr())&&!vehImpawnDto.getTpListStr().isEmpty()){
                for(String tplj : vehImpawnDto.getTpListStr()){
                    WebchatFileinfoDto fileinfo = new WebchatFileinfoDto();
                    fileinfo.setLsh(vehImpawnDto.getLsh());
                    fileinfo.setZplj(tplj);
                    fileinfo.setCjsj(new Date());
                    fileinfo.setZpzl("pic");
                    fileinfo.setZplx("1");
                    webchatFileinfoService.save(fileinfo);
                }
            }
        }
    }

    /**
    * 新增
    */
    private void insert(VehImpawn vehImpawn) {
            Date now = new Date();
    vehImpawn.setId(UuidUtil.getShortUuid());
    vehImpawnMapper.insert(vehImpawn);
    }

    /**
    * 更新
    */
    private void update(VehImpawn vehImpawn) {
    vehImpawnMapper.updateByPrimaryKey(vehImpawn);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    vehImpawnMapper.deleteByPrimaryKey(id);
    }
    }

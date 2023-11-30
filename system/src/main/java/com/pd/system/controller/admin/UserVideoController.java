package com.pd.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.RedisCode;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.domain.User;
import com.pd.server.main.domain.UserExample;
import com.pd.server.main.domain.UserVideo;
import com.pd.server.main.domain.UserVideoExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.SysLogService;
import com.pd.server.main.service.UserVideoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.BaseController;
import com.pd.system.controller.conf.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/userVideo")
public class UserVideoController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserVideoController.class);
    public static final String BUSINESS_NAME = "视频用户";

    @Resource
    private UserVideoService userVideoService;
    @Resource
    private SysLogService sysLogService;
    @Resource
    public RedisTemplate redisTemplate;

    @PostMapping("/changePwd")
    public ResponseDto changePwd(@RequestBody UserVideoDto userVideoDto) {
        ResponseDto responseDto = new ResponseDto();
        if(!StringUtils.isEmpty(userVideoDto.getOldPwd())&&!StringUtils.isEmpty(userVideoDto.getNewPwd())){
            if(userVideoDto.getPassword().equals(DigestUtils.md5DigestAsHex(userVideoDto.getOldPwd().getBytes()))){
                userVideoDto.setPassword(DigestUtils.md5DigestAsHex(userVideoDto.getNewPwd().getBytes()));
                userVideoService.updatePwd(userVideoDto);
                responseDto.setMessage("修改成功");
            }else {
                responseDto.setSuccess(false);
                responseDto.setMessage("原密码输入错误，请重新输入");
            }
        }else {
            responseDto.setSuccess(false);
            responseDto.setMessage("参数错误");
        }
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody UserVideoDto userVideoDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        PageHelper.startPage(userVideoDto.getPage(), userVideoDto.getSize());
        UserVideoExample userVideoExample = new UserVideoExample();
        UserVideoExample.Criteria ca = userVideoExample.createCriteria();
        if(null != userVideoDto){
            if(!StringUtils.isEmpty(userVideoDto.getLoginName())){
                ca.andLoginNameEqualTo(userVideoDto.getLoginName());
            }
            if(!StringUtils.isEmpty(userVideoDto.getName())){
                ca.andNameLike("%"+userVideoDto.getName()+"%");
            }
            if(!StringUtils.isEmpty(userVideoDto.getZt())){
                ca.andZtEqualTo(userVideoDto.getZt());
            }
            if(!StringUtils.isEmpty(userVideoDto.getRode())){
                ca.andRodeEqualTo(userVideoDto.getRode());
            }
        }
        if(!StringUtils.isEmpty(userVideoDto.getXmbm())&&"002".equals(userVideoDto.getXmbm())){
            ca.andRodeEqualTo("00000001");
        }
        if(!"00000000".equals(loginUserDto.getRode())){
            ca.andRodeNotEqualTo("00000000");
            if(!StringUtils.isEmpty(userVideoDto.getXmbm())&&"zhuhai".equals(userVideoDto.getXmbm())){
                ca.andRodeNotEqualTo("00000001");
            }
        }
        List<UserVideo> userList = userVideoService.list(userVideoExample);
        PageInfo<UserVideo> pageInfo = new PageInfo<>(userList);
        userVideoDto.setTotal(pageInfo.getTotal());
        List<UserVideoDto> userDtoList = CopyUtil.copyList(userList, UserVideoDto.class);
        userVideoDto.setList(userDtoList);
        responseDto.setContent(userVideoDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserVideoDto userVideoDto) {
        userVideoDto.setPassword(DigestUtils.md5DigestAsHex(userVideoDto.getPassword().getBytes()));
        // 保存校验
                ValidatorUtil.require(userVideoDto.getLoginName(), "登陆名");
                ValidatorUtil.length(userVideoDto.getLoginName(), "登陆名", 1, 50);
                ValidatorUtil.length(userVideoDto.getName(), "昵称", 1, 50);
                ValidatorUtil.require(userVideoDto.getPassword(), "密码");

        ResponseDto responseDto = new ResponseDto();
        userVideoService.save(userVideoDto);
        RedisConfig.init_uservideo();//刷新用户缓存
        responseDto.setContent(userVideoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        userVideoService.delete(id);
        RedisConfig.init_uservideo();//刷新用户缓存
        return responseDto;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserVideoDto userVideoDto, HttpServletRequest request) {
        LOG.info("用户登录开始");
        ResponseDto responseDto = new ResponseDto();
        //String imageCode = (String) request.getSession().getAttribute(userDto.getImageCodeToken());

        String imageCode = (String) redisTemplate.opsForValue().get(userVideoDto.getImageCodeToken());
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        String xmbh = userVideoDto.getXmbm();
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            sysLogService.addLog(userVideoDto.getLoginName() ,request.getRemoteAddr(),"用户登录","登录",
                    "1" ,"", JSONObject.toJSONString(userVideoDto),"",
                    "用户登录失败，验证码已过期","1",xmbh);
            return responseDto;
        }
        /**
         * 判断最大在线数
         */

        if (!imageCode.toLowerCase().equals(userVideoDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            sysLogService.addLog(userVideoDto.getLoginName() ,request.getRemoteAddr(),"用户登录","登录",
                    "1" ,"", JSONObject.toJSONString(userVideoDto),"",
                    "验证码不对","1",xmbh);
            return responseDto;
        } else {
            // 验证通过后，移除验证码
            // request.getSession().removeAttribute(userDto.getImageCodeToken());
            redisTemplate.delete(userVideoDto.getImageCodeToken());
        }



        userVideoDto.setPassword(DigestUtils.md5DigestAsHex(userVideoDto.getPassword().getBytes()));

        LoginUserDto loginUserDto = userVideoService.login(userVideoDto);
        String token = UuidUtil.getShortUuid();
        loginUserDto.setToken(token);


        /**
         * 登录成功后根据用户名获取就tonken
         * 如果tonken 存在则移除原来的tonken 值（单一登录）
         */
        Object userobject =  redisTemplate.opsForValue().get(RedisCode.ALLVIDEOUSER);
        Map<String,String> usermap = new HashMap<String,String>();
        if(null != userobject && userobject instanceof Map<?,?>){
            usermap = (Map<String, String>) userobject;
            String oldtoken = usermap.get(userVideoDto.getLoginName());
            if(!StringUtils.isEmpty(oldtoken)){//当前用户存在其他登录
                redisTemplate.delete(oldtoken);//移除原来用户的tonken
            }
        }
        usermap.put(userVideoDto.getLoginName(),token);
        redisTemplate.opsForValue().set(RedisCode.ALLVIDEOUSER, usermap);

        Map<String ,String> mapdept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        loginUserDto.setDeptmap(mapdept);
        Map<String ,String> mapuser = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.USERVIDEOCODENAME);
        loginUserDto.setUsermap(mapuser);
        Map<String, List<String>> mapxmbhsbsns = (Map<String, List<String>>) redisTemplate.opsForValue().get(RedisCode.PROJECTSBSNS);
        loginUserDto.setXmbhsbsns(mapxmbhsbsns);
        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);

        //request.getSession().setAttribute(Constants.LOGIN_USER,loginUserDto);
        responseDto.setContent(loginUserDto);
        sysLogService.addLog(userVideoDto.getLoginName() ,request.getRemoteAddr(),"用户登录","登录",
                "0" ,"", JSONObject.toJSONString(userVideoDto),"",
                "","1",xmbh);
        return responseDto;
    }

    /**
     * 退出
     */
    @GetMapping("/logout/{token}")
    public ResponseDto logout(@PathVariable String token ) {
        ResponseDto responseDto = new ResponseDto();
        //request.getSession().setAttribute(Constants.LOGIN_USER,null);
        redisTemplate.delete(token);
        LOG.info("从redis中删除token:{}", token);
        return responseDto;
    }

}

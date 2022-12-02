package com.pd.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.User;
import com.pd.server.main.domain.UserExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.UserDto;
import com.pd.server.main.service.UserService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.BaseController;
import com.pd.system.controller.conf.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
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
@RequestMapping("/admin/user")
public class UserController extends BaseController {

private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
public static final String BUSINESS_NAME = "用户";

@Resource
private UserService userService;

@Resource
public RedisTemplate redisTemplate;

    @PostMapping("/changePwd")
    public ResponseDto changePwd(@RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        if(!StringUtils.isEmpty(userDto.getOldPwd())&&!StringUtils.isEmpty(userDto.getNewPwd())){
            if(userDto.getPassword().equals(DigestUtils.md5DigestAsHex(userDto.getOldPwd().getBytes()))){
                userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getNewPwd().getBytes()));
                userService.updatePwd(userDto);
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
public ResponseDto list(@RequestBody UserDto userDto) {
   ResponseDto responseDto = new ResponseDto();
   LoginUserDto loginUserDto = getRequestHeader();
    PageHelper.startPage(userDto.getPage(), userDto.getSize());
    UserExample userExample = new UserExample();
    UserExample.Criteria ca = userExample.createCriteria();
    if(null != userDto){
        if(!StringUtils.isEmpty(userDto.getLoginName())){
            ca.andLoginNameEqualTo(userDto.getLoginName());
        }
        if(!StringUtils.isEmpty(userDto.getName())){
            ca.andNameLike("%"+userDto.getName()+"%");
        }
        if(!StringUtils.isEmpty(userDto.getZt())){
            ca.andZtEqualTo(userDto.getZt());
        }
        if(!StringUtils.isEmpty(userDto.getRode())){
            ca.andRodeEqualTo(userDto.getRode());
        }
    }
    if(!"00000000".equals(loginUserDto.getRode())){
        ca.andRodeNotEqualTo("00000000");
    }
    List<User> userList = userService.list(userExample);
    PageInfo<User> pageInfo = new PageInfo<>(userList);
    userDto.setTotal(pageInfo.getTotal());
    List<UserDto> userDtoList = CopyUtil.copyList(userList, UserDto.class);
    userDto.setList(userDtoList);
   responseDto.setContent(userDto);
   return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody UserDto userDto) {
    userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));

    // 保存校验
            ValidatorUtil.require(userDto.getLoginName(), "登陆名");
            ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
            ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
            ValidatorUtil.require(userDto.getPassword(), "密码");

ResponseDto responseDto = new ResponseDto();
    userService.save(userDto);
    RedisConfig.init_user();//刷新用户缓存
responseDto.setContent(userDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
userService.delete(id);
    RedisConfig.init_user();//刷新用户缓存
return responseDto;
}

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserDto userDto, HttpServletRequest request) {
        LOG.info("用户登录开始");
        ResponseDto responseDto = new ResponseDto();
          //String imageCode = (String) request.getSession().getAttribute(userDto.getImageCodeToken());

        String imageCode = (String) redisTemplate.opsForValue().get(userDto.getImageCodeToken());
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        /**
         * 判断最大在线数
         */

        if (!imageCode.toLowerCase().equals(userDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
          // request.getSession().removeAttribute(userDto.getImageCodeToken());
            redisTemplate.delete(userDto.getImageCodeToken());
        }



        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));

        LoginUserDto loginUserDto = userService.login(userDto);
        String token = UuidUtil.getShortUuid();
        loginUserDto.setToken(token);


        /**
         * 登录成功后根据用户名获取就tonken
         * 如果tonken 存在则移除原来的tonken 值（单一登录）
         */
        Object userobject =  redisTemplate.opsForValue().get(RedisCode.AllUSER);
        Map<String,String> usermap = new HashMap<String,String>();
        if(null != userobject && userobject instanceof Map<?,?>){
            usermap = (Map<String, String>) userobject;
            String oldtoken = usermap.get(userDto.getLoginName());
            if(!StringUtils.isEmpty(oldtoken)){//当前用户存在其他登录
                redisTemplate.delete(oldtoken);//移除原来用户的tonken
            }
        }
        usermap.put(userDto.getLoginName(),token);
        redisTemplate.opsForValue().set(RedisCode.AllUSER, usermap);

        Map<String ,String> mapdept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        loginUserDto.setDeptmap(mapdept);
        Map<String ,String> mapuser = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.USERCODENAME);
        loginUserDto.setUsermap(mapuser);
        Map<String, List<String>> mapxmbhsbsns = (Map<String, List<String>>) redisTemplate.opsForValue().get(RedisCode.PROJECTSBSNS);
        loginUserDto.setXmbhsbsns(mapxmbhsbsns);
        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);

         //request.getSession().setAttribute(Constants.LOGIN_USER,loginUserDto);
        responseDto.setContent(loginUserDto);
        return responseDto;
    }

    /**
     * 登录对外接口
     */
    @PostMapping("/loginDw")
    public ResponseDto loginDw(@RequestBody UserDto userDto, HttpServletRequest request) {
        LOG.info("用户登录开始");
        ResponseDto responseDto = new ResponseDto();
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        LoginUserDto loginUserDto = userService.login(userDto);
        String token = UuidUtil.getShortUuid();
        loginUserDto.setToken(token);
        /**
         * 登录成功后根据用户名获取就tonken
         * 如果tonken 存在则移除原来的tonken 值（单一登录）
         */
        Object userobject =  redisTemplate.opsForValue().get(RedisCode.AllUSER);
        Map<String,String> usermap = new HashMap<String,String>();
        if(null != userobject && userobject instanceof Map<?,?>){
            usermap = (Map<String, String>) userobject;
            String oldtoken = usermap.get(userDto.getLoginName());
            if(!StringUtils.isEmpty(oldtoken)){//当前用户存在其他登录
                redisTemplate.delete(oldtoken);//移除原来用户的tonken
            }
        }
        usermap.put(userDto.getLoginName(),token);
        redisTemplate.opsForValue().set(RedisCode.AllUSER, usermap);

        Map<String ,String> mapdept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        loginUserDto.setDeptmap(mapdept);
        Map<String ,String> mapuser = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.USERCODENAME);
        loginUserDto.setUsermap(mapuser);
        Map<String, List<String>> mapxmbhsbsns = (Map<String, List<String>>) redisTemplate.opsForValue().get(RedisCode.PROJECTSBSNS);
        loginUserDto.setXmbhsbsns(mapxmbhsbsns);
        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);

        //request.getSession().setAttribute(Constants.LOGIN_USER,loginUserDto);
        responseDto.setContent(loginUserDto);
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

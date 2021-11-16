package com.pd.server.main.service;

import com.alibaba.fastjson.JSON;
import com.pd.server.main.domain.User;
import com.pd.server.main.domain.UserExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResourceDto;
import com.pd.server.main.dto.UserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.mapper.UserMapper;
import com.pd.server.main.mapper.my.MyUserMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

@Resource
private UserMapper userMapper;

    @Resource
    private MyUserMapper myUserMapper;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    /**
     * 列表查询
     */
    public void list(UserDto userDto) {
        PageHelper.startPage(userDto.getPage(), userDto.getSize());
        UserExample userExample = new UserExample();
        if(null != userDto){
            UserExample.Criteria ca = userExample.createCriteria();
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
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        userDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = CopyUtil.copyList(userList, UserDto.class);
        userDto.setList(userDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(UserDto userDto) {
        User user = CopyUtil.copy(userDto, User.class);
        if (StringUtils.isEmpty(userDto.getId())) {
            this.insert(user);
        } else {
            this.update(user);
        }
    }

    /**
     * 更新
     */
    public void updatePwd(UserDto userDto) {
        User user = CopyUtil.copy(userDto, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
    * 新增
    */
    private void insert(User user) {
        user.setId(UuidUtil.getShortUuid());
        User userDb = this.selectByLoginName(user.getLoginName());
        if (userDb != null) {
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);
    }

    /**
    * 更新
    */
    private void update(User user) {
        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据登录名查询用户信息
     * @param loginName
     * @return
     */
    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public LoginUserDto login(UserDto userDto) {
        {
            User user = selectByLoginName(userDto.getLoginName());
            if (user == null) {
                LOG.info("用户名不存在, {}", userDto.getLoginName());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            } else {
                if (user.getPassword().equals(userDto.getPassword())) {
                    // 登录成功
                    LoginUserDto loginUserDto = CopyUtil.copy(user, LoginUserDto.class);
                    // 为登录用户读取权限
                    setAuth(loginUserDto ,user.getRode());
                    return loginUserDto;
                } else {
                    LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", userDto.getPassword(), user.getPassword());
                    throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
                }
            }
        }
    }

    /**
     * 为登录用户读取权限
     */
    private void setAuth(LoginUserDto loginUserDto,String rode) {
        List<ResourceDto> resourceDtoList = myUserMapper.findResources(rode);
        loginUserDto.setResources(resourceDtoList);

        // 整理所有有权限的请求，用于接口拦截
        HashSet<String> requestSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(resourceDtoList)) {
            for (int i = 0, l = resourceDtoList.size(); i < l; i++) {
                ResourceDto resourceDto = resourceDtoList.get(i);
                String arrayString = resourceDto.getRequest();
                List<String> requestList = JSON.parseArray(arrayString, String.class);
                if (!CollectionUtils.isEmpty(requestList)) {
                    requestSet.addAll(requestList);
                }
            }
        }
        LOG.info("有权限的请求：{}", requestSet);
        loginUserDto.setRequests(requestSet);
    }
}
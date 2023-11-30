package com.pd.server.main.service;

import com.alibaba.fastjson.JSON;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.domain.User;
import com.pd.server.main.domain.UserExample;
import com.pd.server.main.domain.UserVideo;
import com.pd.server.main.domain.UserVideoExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.mapper.UserVideoMapper;
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
public class UserVideoService {

    @Resource
    private UserVideoMapper userVideoMapper;
    @Resource
    private MyUserMapper myUserMapper;

    private static final Logger LOG = LoggerFactory.getLogger(UserVideoService.class);

    /**
    * 列表查询
    */
    public List<UserVideo> list(UserVideoExample example) {
        return userVideoMapper.selectByExample(example);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(UserVideoDto userVideoDto) {
        UserVideo userVideo = CopyUtil.copy(userVideoDto, UserVideo.class);
        if (StringUtils.isEmpty(userVideoDto.getId())) {
            this.insert(userVideo);
        } else {
            this.update(userVideo);
        }
    }

    public void updatePwd(UserVideoDto userVideoDto){
        UserVideo userVideo = CopyUtil.copy(userVideoDto, UserVideo.class);
        userVideoMapper.updateByPrimaryKeySelective(userVideo);
    }

    /**
    * 新增
    */
    private void insert(UserVideo userVideo) {
        userVideo.setId(UuidUtil.getShortUuid());
        userVideoMapper.insert(userVideo);
    }

    /**
    * 更新
    */
    private void update(UserVideo userVideo) {
        userVideoMapper.updateByPrimaryKey(userVideo);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        userVideoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据登录名查询用户信息
     * @param loginName
     * @return
     */
    public UserVideo selectByLoginName(String loginName) {
        UserVideoExample userVideoExample = new UserVideoExample();
        userVideoExample.createCriteria().andLoginNameEqualTo(loginName);
        List<UserVideo> userList = userVideoMapper.selectByExample(userVideoExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public LoginUserDto login(UserVideoDto userDto) {
        {
            UserVideo user = selectByLoginName(userDto.getLoginName());
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

    public List<String> glyCode(){
        return userVideoMapper.glyCode();
    }
}
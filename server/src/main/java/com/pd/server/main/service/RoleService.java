package com.pd.server.main.service;

import com.pd.server.main.domain.Role;
import com.pd.server.main.domain.RoleExample;
import com.pd.server.main.domain.RoleResource;
import com.pd.server.main.domain.RoleResourceExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.RoleDto;
import com.pd.server.main.mapper.RoleMapper;
import com.pd.server.main.mapper.RoleResourceMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleResourceMapper roleResourceMapper;


    /**
     * 列表查询
     */
    public List<Role> list(RoleExample roleExample) {
        return roleMapper.selectByExample(roleExample);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(RoleDto roleDto) {
        Role role = CopyUtil.copy(roleDto, Role.class);
        if (StringUtils.isEmpty(roleDto.getId())) {
            this.insert(role);
        } else {
            this.update(role);
        }
    }

    /**
     * 新增
     */
    private void insert(Role role) {
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }

    /**
     * 更新
     */
    private void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        roleMapper.deleteByPrimaryKey(id);
    }



    /**
     * 按角色保存资源
     */
    public void saveResource(RoleDto roleDto) {
        String roleId = roleDto.getId();
        List<String> resourceIds = roleDto.getResourceIds();
        // 清空库中所有的当前角色下的记录
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceMapper.deleteByExample(example);

        // 保存角色资源
        for (int i = 0; i < resourceIds.size(); i++) {
            RoleResource roleResource = new RoleResource();
            roleResource.setId(UuidUtil.getShortUuid());
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceIds.get(i));
            roleResourceMapper.insert(roleResource);
        }
    }

    /**
     * 按角色加载资源
     * @param roleId
     */
    public List<String> listResource(String roleId) {
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleResource> roleResourceList = roleResourceMapper.selectByExample(example);
        List<String> resourceIdList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleResourceList)){
            for(RoleResource vo : roleResourceList){
                resourceIdList.add(vo.getResourceId());
            }
        }

        return resourceIdList;
    }

    public List<RoleDto> allroles(LoginUserDto user) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria ca = roleExample.createCriteria();
        if(!"00000000".equals(user.getRode())){
            ca.andIdNotEqualTo("00000000");
        }
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        return CopyUtil.copyList(roleList, RoleDto.class);
    }
}

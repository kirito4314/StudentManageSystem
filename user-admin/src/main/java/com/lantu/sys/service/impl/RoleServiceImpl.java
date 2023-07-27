package com.lantu.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantu.sys.entity.Role;
import com.lantu.sys.entity.RoleMenu;
import com.lantu.sys.mapper.RoleMapper;
import com.lantu.sys.mapper.RoleMenuMapper;
import com.lantu.sys.service.IRoleService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laocai
 * @since 2023-07-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Transactional
    @Override
    public void addRole(Role role) {
        this.baseMapper.insert(role);
        if (null != role.getMenuIdList()) {
            for (Integer menuId : role.getMenuIdList())
                roleMenuMapper.insert(new RoleMenu(null,role.getRoleId(),menuId));
        }
    }

    @Override
    public Role getRoleById(Integer id){
        Role role = this.baseMapper.selectById(id);
        List<Integer> menuIdList = roleMenuMapper.getMenuIdListByRoleId(id);
        role.setMenuIdList(menuIdList);
        return role;
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        //修改角色表
        this.baseMapper.updateById(role);
        //删除原有权限
        LambdaQueryWrapper<RoleMenu> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId,role.getRoleId());
        roleMenuMapper.delete(wrapper);
        //新增权限
        if (null != role.getMenuIdList()) {
            for (Integer menuId : role.getMenuIdList())
                roleMenuMapper.insert(new RoleMenu(null,role.getRoleId(),menuId));
        }

    }

    @Override
    @Transactional
    public void deleteRoleById(Integer id) {
        this.baseMapper.deleteById(id);
        //删除权限
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getMenuId,id);
        roleMenuMapper.delete(wrapper);
    }
}

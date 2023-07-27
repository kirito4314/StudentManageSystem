package com.lantu.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.sys.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laocai
 * @since 2023-07-24
 */
public interface IRoleService extends IService<Role> {

    void addRole(Role role);

    Role getRoleById(Integer id);

    void updateRole(Role role);

    void deleteRoleById(Integer id);
}

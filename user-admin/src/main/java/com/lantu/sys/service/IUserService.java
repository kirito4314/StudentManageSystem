package com.lantu.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lantu.sys.entity.User;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laocai
 * @since 2023-07-24
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    void addUser(User user);

    User getUserById(Integer id);

    void updateUser(User user);

    void deleteById(Integer id);
}

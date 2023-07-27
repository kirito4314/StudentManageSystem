package com.lantu.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantu.sys.entity.Menu;
import com.lantu.sys.entity.User;
import com.lantu.sys.entity.UserRole;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.mapper.UserRoleMapper;
import com.lantu.sys.service.IMenuService;
import com.lantu.sys.service.IUserService;
import com.lantu.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laocai
 * @since 2023-07-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IMenuService menuService;

    @Override
    public Map<String,Object> login(User user){
        //根据用户名和密码查询
        LambdaQueryWrapper<User> wapper = new LambdaQueryWrapper<>();
        wapper.eq(User::getUsername,user.getUsername());
        User loginUser = this.baseMapper.selectOne(wapper);
        //查询不为空，并判断密码是否匹配，生成token，发送给前端，并将用户信息存入Redis
        if (loginUser != null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())){
            //暂时使用UUID，后续升级jwt
            //String key = "user:"+ UUID.randomUUID();

            //存入Redis
            loginUser.setPassword(null);
            //redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
            String token = jwtUtil.createToken(loginUser);

            //返回数据
            Map<String,Object> data = new HashMap<>();
            data.put("token",token);
            return data;
        }
        return null;
    }

//    @Override
//    public Map<String,Object> login(User user){
//        //根据用户名和密码查询
//        LambdaQueryWrapper<User> wapper = new LambdaQueryWrapper<>();
//        wapper.eq(User::getUsername,user.getUsername());
//        wapper.eq(User::getPassword,user.getPassword());
//        User loginUser = this.baseMapper.selectOne(wapper);
//        //查询不为空，生成token，发送给前端，并将用户信息存入Redis
//        if (loginUser != null){
//            //暂时使用UUID，后续升级jwt
//            String key = "user:"+ UUID.randomUUID();
//
//            //存入Redis
//            loginUser.setPassword(null);
//            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
//            //返回数据
//            Map<String,Object> data = new HashMap<>();
//            data.put("token",key);
//            return data;
//        }
//        return null;
//    }

    @Override
    public Map<String,Object> getUserInfo(String token){
        //从Redis中根据Token获取用户信息
        //Object obj = redisTemplate.opsForValue().get(token);
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (loginUser != null){
            //User loginUser = JSON.parseObject(JSON.toJSONString(obj),User.class);
            Map<String,Object> data = new HashMap<>();
            data.put("name",loginUser.getUsername());
            data.put("avatar",loginUser.getAvatar());

            //角色
            List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
            data.put("roles",roleList);

            //权限列表
            List<Menu> menuList = menuService.getMenuListByUserId(loginUser.getId());
            data.put("menuList",menuList);

            return data;
        }else {
            return null;
        }
    }

    @Override
    public void logout(String token) {
        //redisTemplate.delete(token);
    }

    @Override
    public void addUser(User user) {
        //用户表
        this.save(user);
        //用户角色表
        List<Integer> roleList = user.getRoleIdList();
        if (roleList != null){
            for(Integer roleId : roleList) {
                userRoleMapper.insert(new UserRole(null, user.getId(), roleId));
            }
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user  = this.getById(id);

        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);

        List<Integer> roleIdList =
                userRoleList.stream().map(userRole -> {
                    return userRole.getRoleId();
                }).collect(Collectors.toList());
        user.setRoleIdList(roleIdList);

        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.updateById(user);

        //删除原有角色
        LambdaQueryWrapper<UserRole > wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId , user.getId());
        userRoleMapper.delete(wrapper);

        //设置新角色
        List<Integer> roleList = user.getRoleIdList();
        if(roleList != null){
            for(Integer roleId : roleList){
                userRoleMapper.insert(new UserRole(null, user.getId(), roleId));
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        this.removeById(id);

        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        userRoleMapper.delete(wrapper);
    }

}

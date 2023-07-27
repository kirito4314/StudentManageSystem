package com.lantu;

import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class XAdminApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Test
    void testMapper() {
//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
        Object obj = redisTemplate.opsForValue().get("user:ec6829ca-6759-4cfb-ab07-626ea049bcd5");
        System.out.println(obj);
    }

}

package com.lantu.config;

import com.lantu.interceptor.JwtValidateinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConf  implements WebMvcConfigurer {

    @Autowired
    private JwtValidateinterceptor jwtValidateinterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtValidateinterceptor);
        registration.addPathPatterns("/**")
                .excludePathPatterns(
                        "/sys/user/login",
                        "/sys/user/info",
                        "/sys/user/logout",
                        "/error",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**"
                );
    }
}

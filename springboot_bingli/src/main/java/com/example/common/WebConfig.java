package com.example.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //指定controller统一的接口前缀
        configurer.addPathPrefix("/api", clazz -> clazz.isAnnotationPresent(RestController.class));
    }

    //添加自定义拦截器JwtInterceptor的生效规则
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/api/admin/login")
                .excludePathPatterns("/api/captcha")
                .excludePathPatterns("/api/admin/captcha")
                .excludePathPatterns("/api/register")
                .excludePathPatterns("/api/user/register");
    }

}

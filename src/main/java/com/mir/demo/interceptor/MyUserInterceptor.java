package com.mir.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class MyUserInterceptor extends WebMvcConfigurerAdapter {

    @Autowired
    private UserConfigInterceptor userConfig;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(userConfig).addPathPatterns("/**");
    }
}

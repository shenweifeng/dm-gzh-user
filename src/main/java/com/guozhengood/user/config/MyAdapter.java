package com.guozhengood.user.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MyAdapter implements WebMvcConfigurer {

    /**
     * 默认首页跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("/login.html");
       // registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}

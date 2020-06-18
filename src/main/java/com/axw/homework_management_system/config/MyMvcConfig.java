package com.axw.homework_management_system.config;

import com.axw.homework_management_system.component.StudentHandlerInterceptor;
import com.axw.homework_management_system.component.TeacherHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/register").setViewName("register");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StudentHandlerInterceptor()).addPathPatterns("/student/**");
        registry.addInterceptor(new TeacherHandlerInterceptor()).addPathPatterns("/teacher/**");
    }
}

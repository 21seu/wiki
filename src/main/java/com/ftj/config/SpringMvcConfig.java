//package com.ftj.config;
//
//import com.ftj.interceptor.LogInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
///**
// * Created by fengtj on 2021/8/29 18:55
// */
//@Configuration
//public class SpringMvcConfig implements WebMvcConfigurer {
//
//    @Resource
//    LogInterceptor logInterceptor;
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login");
//    }
//}

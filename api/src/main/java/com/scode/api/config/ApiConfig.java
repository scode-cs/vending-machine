package com.scode.api.config;

import com.scode.api.intercepter.ApiIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ApiConfig implements WebMvcConfigurer {

    @Autowired
    private ApiIntercepter apiIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiIntercepter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api").allowedOrigins("*");

//        registry.addMapping("/**").allowedMethods("*")
//                .allowedHeaders("*").allowedOrigins("*")
//                .allowCredentials(true);
    }

}

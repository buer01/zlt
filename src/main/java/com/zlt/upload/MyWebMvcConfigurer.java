package com.zlt.upload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/uploaded/**").addResourceLocations("classpath:/uploaded/");
        super.addResourceHandlers(registry);
        //"/Users/zhangyunlong/Desktop/java_Maven/diyici/src/main/resources/uploaded/"
    }

}
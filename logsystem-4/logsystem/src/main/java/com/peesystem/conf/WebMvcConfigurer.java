package com.peesystem.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class WebMvcConfigurer extends  WebMvcConfigurerAdapter {
	@Autowired
	private  MyInterceptor myInterceptor;
    //增加拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(myInterceptor)    //指定拦截器类
                .addPathPatterns("/web/manage/*");        //指定url
    }
}

package com.evan.wj.config;

import com.evan.wj.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//在拦截器LoginInterceptor 中配置的路径，触发的时机是在拦截器生效之后，即访问一个uRL,会首先通过Configurer判断是否需要需要拦截，如果需要才触发拦截器LoginInterceptor
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor getLoginIntercepter(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry redistry){
        //对除了 /index.html的所有路径应用拦截器
        redistry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }
}

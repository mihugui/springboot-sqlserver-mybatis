package com.ibms.app.config;

import com.ibms.app.config.filters.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNmae FilterConfig
 * @Description TODO
 * @Author PWT
 * @Date 2018/8/14 17:45
 * @Version 1.0
 **/

@Configuration
public class FilterConfig implements HandlerInterceptor {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        System.out.println("加载jwt拦截器");
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        //添加不需要拦截的url
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/**");
        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
        return registrationBean;
    }
}

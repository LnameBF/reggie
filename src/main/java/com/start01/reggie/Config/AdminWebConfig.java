package com.start01.reggie.Config;

import com.start01.reggie.Interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 * 已经弃用，使用过滤器
 */
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/backend/index.html");
//                .excludePathPatterns("/backend/page/login/login.html","/backend/js/**","/backend/api/**"
//                ,"/backend/images/**","/backend/plugin/**","/backend/style/**");
    }
}

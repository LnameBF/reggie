package com.start01.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.start01.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否登录
 */
//@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
@Slf4j
public class LoginFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        log.info("拦截成功,路径是{}",request.getRequestURL());
        //1.配置什么路径不需要拦截
        String[] url=new String[]{
            "/employee/login",
            "/employee/logout",
            "/backend/**",
            "/front/**"
        };
        //2.判断此次请求是否需要处理
        Boolean check = check(url, request.getRequestURI());
        if(check){
            log.info("不需要拦截");
            filterChain.doFilter(request,response);
            return;
        }
        //3.如果已经登录
        if(request.getSession().getAttribute("employee")!=null){
            log.info("已经登录");
            filterChain.doFilter(request,response);
            return;
        }
        //4.如果需要拦截
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 检查url是否需要过滤
     * @param urls
     * @param RequestUrl
     * @return
     */
    public Boolean check(String[] urls,String RequestUrl){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, RequestUrl);
            //如果匹配
            if(match){
                return true;
            }
        }
        return  false;
    }
}

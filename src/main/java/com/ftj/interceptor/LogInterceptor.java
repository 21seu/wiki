package com.ftj.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fengtj on 2021/8/29 18:49
 *
 * @description 拦截器：常用于登录校验，权限校验，日志打印
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //打印请求信息
        LOG.info("---------- LogInterceptor 开始 ----------");
        LOG.info("请求地址：{} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("远程地址：{}", request.getRemoteAddr());
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("requestStartTime");
        LOG.info("-----------LogFilter 结束  耗时：{}ms---------", System.currentTimeMillis() - startTime);
    }
}

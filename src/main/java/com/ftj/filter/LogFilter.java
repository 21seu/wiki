//package com.ftj.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by fengtj on 2021/8/29 18:41
// */
//@Component
//public class LogFilter implements Filter {
//
//    private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        LOG.info("-----------LogFilter 开始---------");
//        LOG.info("请求地址：{} {}", req.getRequestURL().toString(), req.getMethod());
//        LOG.info("远程地址：{}", req.getRemoteAddr());
//
//        long startTime = System.currentTimeMillis();
//        filterChain.doFilter(request, response);
//        LOG.info("-----------LogFilter 结束  耗时：{}ms---------", System.currentTimeMillis() - startTime);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}

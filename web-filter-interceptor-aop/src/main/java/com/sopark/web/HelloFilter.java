package com.sopark.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Slf4j
public class HelloFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init!!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter start - doFilter!!");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("filter end - doFilter!!");
    }

    @Override
    public void destroy() {
        log.info("filter destroy!!");
    }
}

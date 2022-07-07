package com.example.demo.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用于解决body体需要读取两次的情况
 * @author Administrator
 */
//@WebFilter(filterName = "httpServletRequestWrapperFilter", urlPatterns = "/*")
public class HttpServletRequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}

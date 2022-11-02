package com.github.bjlhx15.boot2async;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class HelloRequsetFilter extends OncePerRequestFilter {
    @Override
    protected void initFilterBean() throws ServletException {
        System.out.println("Filter初始化……");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filter Chain :" + Thread.currentThread().getName() + "----->" + request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}

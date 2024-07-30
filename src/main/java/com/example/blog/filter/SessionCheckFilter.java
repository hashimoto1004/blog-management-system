package com.example.blog.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import com.example.blog.entity.User;

@WebFilter("/*")
public class SessionCheckFilter implements Filter {

    @Value("${env:local}") // デフォルト値を "local" に設定
    private String environment;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // if ("local".equals(environment)) {
        if (session == null || session.getAttribute("loginUser") == null) {
            session = httpRequest.getSession(true);
            User mockUser = new User();
            mockUser.setId(1);
            mockUser.setUsername("hashimoto");
            session.setAttribute("loginUser", mockUser);
        }
        // }

        String loginURI = httpRequest.getContextPath() + "/";

        boolean loggedIn = (session != null && session.getAttribute("loginUser") != null);
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean loginPage = httpRequest.getRequestURI().contains("login");
        boolean registerPage = httpRequest.getRequestURI().contains("register");

        if (loggedIn || loginRequest || loginPage || registerPage) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}

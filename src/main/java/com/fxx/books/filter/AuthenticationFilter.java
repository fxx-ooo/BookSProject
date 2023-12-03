package com.fxx.books.filter;

import com.fxx.books.utils.Constant;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截所有的请求，判断是否当前是否为登录的状态，如果是就访问，如果不是就跳转到登录页面
 */
@WebFilter(filterName = "authenticationFilter",urlPatterns = "/*")//url在这里是拦截所有的事务
public class AuthenticationFilter implements javax.servlet.Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //获取请求拦截的地址：
        String requestURI = request.getRequestURI();
        if (requestURI.contains("login.jsp")||requestURI.contains("loginServlet")){
            //访问登录页面直接放过
            filterChain.doFilter(request,response);
        }else {
            //其他情况我们就需要判断当前是否是登录的状态
            HttpSession session = request.getSession();
            Object attribute = session.getAttribute(Constant.SESSION_LOGIN_USER);
            if (attribute!=null){
                //说明我们已经登录过了，直接放过
                filterChain.doFilter(request,response);
            }else {
                //没有登录，需要跳转回登录页面
                session.setAttribute("msg","请先登录!!!");
                response.sendRedirect("/login.jsp");
            }
        }


    }
}

package com.pn.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();

        if(path.contains("/style")||path.contains("/zTree")||path.contains("/login.jsp")||path.contains("/userLogin")){
            chain.doFilter(req,resp);
        }else{
            HttpSession session = req.getSession();

            Object loginname = session.getAttribute("loginname");

            if(loginname!=null){
                chain.doFilter(req,resp);
            }else{
                req.setAttribute("msg","您尚未登录，请先登录");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }


    }
}

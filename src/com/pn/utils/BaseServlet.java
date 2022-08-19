package com.pn.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/*
  最终要处理一类业务的所有请求
 */
public class BaseServlet extends HttpServlet {

    //真正的请求处理方法 --- 请求直接执行的方法
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //处理乱码
        if(request.getMethod().equals("POST")){
            request.setCharacterEncoding("UTF-8");
        }
        response.setContentType("text/html;charset=UTF-8");

        //接收参数 --- 处理请求的方法名
        String methodName = request.getParameter("methodName");
        if(methodName==null||methodName.isEmpty()){
            throw new RuntimeException("没传参数methodName");
        }

        //当前Servlet的运行时类
        Class clz = this.getClass();

        Method method = null;
        try {
            //当前Servlet中的参数名称的请求处理方法
            method = clz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {//方法不存在
            throw new RuntimeException("请求处理方法"+methodName+"没有");
        }

        //执行方法
        try {
            String result = (String) method.invoke(this, request, response);
            if(result!=null&&!result.isEmpty()){//如果请求处理方法返回值字符串不为空,则解析处理
                /*
                 返回值字符串的格式： 前缀:路径
                 1)前缀是f,做转发,路径就是转发路径;
                 2)前缀是s,做重定向,路径就是重定向路径
                 3)不带前缀,直接是路径,那么默认做转发
                 */
                if(result.contains(":")){//说明有前缀
                    int index = result.indexOf(":");//:的下标
                    String prefix = result.substring(0, index);//前缀
                    String path = result.substring(index + 1);//路径
                    if(prefix.equals("f")){//前缀f
                        //转发
                        request.getRequestDispatcher("/"+path).forward(request, response);
                    }else if(prefix.equals("s")){//前缀s
                        //重定向
                        response.sendRedirect(request.getContextPath()+"/"+path);
                    }
                    //未知前缀,啥都不做
                }else{//说明没有前缀
                    //转发
                    request.getRequestDispatcher("/"+result).forward(request, response);
                }
            }
            //如果请求处理方法返回值字符串为空,则啥都不做,也就是在具体的请求处理方法中操作
        } catch (ReflectiveOperationException e) {//方法执行(内部)异常
            throw new RuntimeException(methodName+"方法内部有异常");
        }
    }
}

package com.pn.controller;

import com.pn.entitys.Menu;
import com.pn.service.MenuService;
import com.pn.utils.BaseServlet;
import com.pn.utils.JacksonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends BaseServlet {

    private MenuService menuService = new MenuService();

    public void queryMenus(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Menu> menuList = menuService.queryMenuService();

        //将list<Menu>转成json数组

        String jsonStr = JacksonUtil.objToJson(menuList);

        //将json响应给ajax引擎

        resp.setContentType("application/json;charset=utf-8");

        resp.getWriter().write(jsonStr);

    }

    //查询指定用户的角色的菜单的请求处理方法
    public void queryPostMenus(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //从session获取当前的登录名
        HttpSession session = req.getSession();
        String loginname = (String) session.getAttribute("loginname");

        //查询所有菜单
        List<Menu> menuList = menuService.QueryPostMenuService(loginname);

        //将List<Menu>转成json数组
        String jsonStr = JacksonUtil.objToJson(menuList);

        //将json数组响应给ajax引擎
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }
}

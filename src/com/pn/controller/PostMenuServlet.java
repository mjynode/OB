package com.pn.controller;

import com.pn.entitys.PostMenu;
import com.pn.service.PostMenuService;
import com.pn.service.PostService;
import com.pn.utils.BaseServlet;
import com.pn.utils.JacksonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/postMenu")
public class PostMenuServlet extends BaseServlet {

    private PostMenuService pms = new PostMenuService();


    //保存岗位id和给其分配的菜单id的请求的处理 --- 授权
    public void savePostMenu(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收请求数据
        String ids = req.getParameter("ids");

        //执行业务方法
        pms.addPostMenus(ids);

        //向ajax引擎响应ok
        resp.getWriter().write("ok");
    }

    //根据岗位id查询其分配的所有菜单id的请求的处理
    public void queryPostMenu(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收岗位id
        int pid = Integer.parseInt(req.getParameter("pid"));

        //执行业务方法
        List<PostMenu> postMenus = pms.queryPostMenuService(pid);

        //向ajax引擎响应json数组
        String jsonStr = JacksonUtil.objToJson(postMenus);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

}

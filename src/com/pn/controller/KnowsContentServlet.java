package com.pn.controller;

import com.pn.entitys.KnowsContent;
import com.pn.service.KnowsContentService;
import com.pn.utils.BaseServlet;
import com.pn.utils.JacksonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet("/content")
public class KnowsContentServlet extends BaseServlet {

    private KnowsContentService kcs = new KnowsContentService();

    public void isHasContent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收typeid
        int typeid = Integer.parseInt(req.getParameter("typeid"));

        //执行业务方法
        List<KnowsContent> contents = kcs.contentByTypeidService(typeid);

        //将List<KnowsContent>转成json数组
        String jsonStr = JacksonUtil.objToJson(contents);

        //响应json
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

    //添加版本内容的请求处理方法
    public void addContent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //接收请求数据
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int typeid = Integer.parseInt(req.getParameter("typeid"));

        Timestamp lasttime = new Timestamp(new Date().getTime());

        //创建KnowsContent对象封装数据
        KnowsContent kc = new KnowsContent();
        kc.setTitle(title);
        kc.setContent(content);
        kc.setLasttime(lasttime);
        kc.setTypeid(typeid);

        try {
            //执行业务
            kcs.addContentService(kc);
            resp.getWriter().write("1");
        }catch(Exception e){
            resp.getWriter().write("0");
        }
    }

    //删除版本内容的请求处理方法
    public void deleteContent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收内容id
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            //执行业务
            kcs.deleteContentService(id);
            resp.getWriter().write("1");
        }catch(Exception e){
            resp.getWriter().write("0");
        }
    }
}

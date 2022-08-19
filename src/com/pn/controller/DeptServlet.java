package com.pn.controller;

import com.pn.entitys.Dept;
import com.pn.service.DeptService;
import com.pn.utils.BaseServlet;
import com.pn.utils.JacksonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dept")
public class DeptServlet extends BaseServlet {
    private DeptService deptService = new DeptService();

    public void queryAllDept(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Dept> deptList = deptService.queryAllDeptService();

        req.setAttribute("deptList",deptList);
        req.getRequestDispatcher("/WEB-INF/jsp/deptList.jsp").forward(req,resp);
    }

    public void isParentDept(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        if(deptService.idParentDeptService(id)){//是上级部门
            resp.getWriter().write("0");//不能删
            return;
        }

        resp.getWriter().write("1");//能删

    }

    public void deleteDept(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try{
            deptService.deleteDeptService(id);

            resp.getWriter().write("1");
        }catch(Exception e){
            resp.getWriter().write("0");
        }


        /*
        全局刷新--同步
        queryAllDept(req,resp);
         */
    }
    public void queryAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Dept> depts = deptService.queryAllService();

        String jsonStr = JacksonUtil.objToJson(depts);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);

    }

    public void addDept(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println(1);
        int pid = Integer.parseInt(req.getParameter("pid"));
        String dname = req.getParameter("dname");
        String ddesc = req.getParameter("ddesc");

        System.out.println(pid+"  "+dname+"  "+ddesc);
        System.out.println(2);
        Dept dept= new Dept();

        dept.setPid(pid);
        dept.setDname(dname);
        dept.setDdesc(ddesc);

        deptService.addDeptService(dept);
        System.out.println(3);
        queryAllDept(req,resp);
    }


}

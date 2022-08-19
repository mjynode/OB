package com.pn.controller;

import com.pn.entitys.KnowsType;
import com.pn.service.KnowsTypeService;
import com.pn.utils.BaseServlet;
import com.pn.utils.JacksonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/type")
public class KnowsTypeServlet extends BaseServlet {

    private KnowsTypeService kts = new KnowsTypeService();

    //加载分类树的请求处理方法
    public void queryAllType(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //执行业务方法
        List<KnowsType> types = kts.allKnowsTypeService();

        //将List<KnowsType>转成json数组
        String jsonStr = JacksonUtil.objToJson(types);

        //向ajax引擎(ztree)响应json数组
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(jsonStr);
    }

    //添加分类的请求处理方法
    public void addType(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收请求数据并封装到实体对象
        String typenamae = req.getParameter("tname");//新增的分类名称
        int pid = Integer.parseInt(req.getParameter("pid"));//新增分类的上级分类的id

        KnowsType kt = new KnowsType();
        kt.setTypename(typenamae);
        kt.setPtypeid(pid);

        //执行业务方法
        KnowsType knowsType = kts.addTypeService(kt);

        //将KnowsType对象转成json
        String jsonStr = JacksonUtil.objToJson(knowsType);

        //向ajax引擎响应json数组
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(jsonStr);
    }

    //判断指定分类是否是上级分类的请求处理方法
    public void queryType(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收分类id
        int pid = Integer.parseInt(req.getParameter("pid"));

        //执行业务,判断其是否是上级分类
        if(kts.isUpType(pid)){//true 上级分类
            resp.getWriter().write("1");
            return;
        }
        //不是上级分类
        resp.getWriter().write("0");
    }

    //删除分类的请求处理方法
    public void deleteType(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收分类id
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            //执行业务
            kts.deleteTypeService(id);
            resp.getWriter().write("1");
        }catch(Exception e){
            resp.getWriter().write("0");
        }
    }
}

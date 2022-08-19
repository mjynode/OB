package com.pn.controller;

import com.pn.Exception.UserException;
import com.pn.entitys.Dept;
import com.pn.entitys.Post;
import com.pn.entitys.User;
import com.pn.service.DeptService;
import com.pn.service.PostService;
import com.pn.service.UserService;
import com.pn.utils.BaseServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet({"/user","/userLogin"})
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    private DeptService deptService = new DeptService();

    private PostService postService = new PostService();

    //查询所有用户包括所在部门所有岗位的请求处理方法
    public void queryAllUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //执行业务方法
        List<User> userList = userService.queryAllUserService();

        //将List<User>保存到request域,然后转发到userList.jsp展示用户信息
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(req, resp);
    }

    /*
      处理到达saveUser.jsp页面的请求的方法:
      需要查询出所有部门信息和岗位信息,给添加用户的表单的部门和岗位下拉框组装数据,
      然后到达saveUser.jsp;
     */
    public void toSaveUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //查询所有部门 --- 给添加用户的表单的部门下拉框组装数据
        List<Dept> depts = deptService.queryAllService();
        //查询所有岗位 --- 给添加用户的表单的岗位下拉框组装数据
        List<Post> posts = postService.queryAllPostService();

        //将List<Dept>和List<Post>放入request域,转发到saveUser.jsp
        req.setAttribute("depts", depts);
        req.setAttribute("posts", posts);

        req.getRequestDispatcher("/WEB-INF/jsp/saveUser.jsp").forward(req, resp);
    }

    //处理添加用户的请求方法
    public void addUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收表单数据并封装到User对象中
        String loginname = req.getParameter("loginname");
        String realname = req.getParameter("realname");
        String email = req.getParameter("email");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        int did = Integer.parseInt(req.getParameter("did"));
        User user = new User(null, loginname, "123456", realname, email, sex, phone, did);

        //所有岗位id
        String[] pids = req.getParameterValues("pids");

        //执行业务方法
        userService.addUserService(user, pids);

        //重新查询所有用户
        queryAllUser(req, resp);
    }

    //校验登录名是否已存在的请求处理方法
    public void checkName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //接收登录名
        String loginname = req.getParameter("loginname");

        //执行业务方法
        if(userService.isExistLoginname(loginname)){//已存在
            resp.getWriter().write("1");
            return;
        }
        //不已存在
        resp.getWriter().write("0");
    }

    //删除用户的请求处理方法
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收用户id
        int uid = Integer.parseInt(req.getParameter("uid"));

        //执行业务方法
        userService.deleteUserService(uid);

        //重新查询所有用户
        queryAllUser(req, resp);
    }

    //查询被修改用户原信息的请求处理方法
    public void toUpdateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收用户id
        int uid = Integer.parseInt(req.getParameter("uid"));

        //执行业务方法
        Map<String, Object> userMap = userService.queryUserInfo(uid);

        //查询所有部门 --- 给修改用户的表单的部门下拉框组装数据
        List<Dept> depts = deptService.queryAllService();
        //查询所有岗位 --- 给修改用户的表单的岗位下拉框组装数据
        List<Post> posts = postService.queryAllPostService();

        //将List<Dept>和List<Post>放入request域,转发到updateUser.jsp
        req.setAttribute("depts", depts);
        req.setAttribute("posts", posts);

        //将Map<String, Object>存储到request域,并转到updateUser.jsp
        req.setAttribute("userMap", userMap);
        req.getRequestDispatcher("/WEB-INF/jsp/updateUser.jsp").forward(req, resp);
    }

    //修改用户的请求处理方法
    public void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //接收表单数据并封装到User对象中
        int id = Integer.parseInt(req.getParameter("id"));
        String password = req.getParameter("password");
        //String loginname = req.getParameter("loginname");
        String realname = req.getParameter("realname");
        String email = req.getParameter("email");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        int did = Integer.parseInt(req.getParameter("did"));
        User user = new User(id, null, password, realname, email, sex, phone, did);

        //所有岗位id
        String[] pids = req.getParameterValues("pids");

        //执行业务
        userService.updateUserService(user, pids);

        //重新查询所有用户
        queryAllUser(req, resp);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String loginname = req.getParameter("loginname");
        String password = req.getParameter("password");
        try{
            userService.loginService(loginname,password);

            HttpSession session = req.getSession();
            session.setAttribute("loginname",loginname);

            resp.getWriter().write("1");
        }catch(UserException e){
            resp.getWriter().write("0");
        }

    }

    }

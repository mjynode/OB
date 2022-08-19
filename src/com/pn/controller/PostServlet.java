package com.pn.controller;

import com.pn.entitys.Post;
import com.pn.service.PostService;
import com.pn.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/post")
public class PostServlet extends BaseServlet {
    private PostService postService = new PostService();


    public void queryAllPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Post> postList = postService.queryAllPostService();

        req.setAttribute("postList", postList);
        req.getRequestDispatcher("/WEB-INF/jsp/postList.jsp").forward(req, resp);
    }

    public void deletePost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        postService.deletePostService(id);

        resp.sendRedirect(req.getContextPath() + "/post?methodName=queryAllPost");
    }

    public void addPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pname = req.getParameter("pname");
        String pdesc = req.getParameter("pdesc");

        Post post = new Post();

        post.setPname(pname);
        post.setPdesc(pdesc);

        postService.addPostService(post);

        queryAllPost(req,resp);


    }

    public void toUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));


        Post post = postService.queryPostService(id);
        req.setAttribute("post",post);
        req.getRequestDispatcher("/WEB-INF/jsp/updatePost.jsp").forward(req,resp);

    }

    public void updatePost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("pid"));
        String pname = req.getParameter("pname");
        String pdesc = req.getParameter("pdesc");

        Post post = new Post();
        post.setPid(pid);
        post.setPname(pname);
        post.setPdesc(pdesc);

        postService.updatePostService(post);

        queryAllPost(req,resp);
    }
}

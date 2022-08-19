package com.pn.controller;

import com.pn.utils.BaseDao;
import com.pn.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page")
public class PageServlet extends BaseServlet {

    public void toPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pageName = req.getParameter("pageName");
        System.out.println(pageName);

        req.getRequestDispatcher("/WEB-INF/jsp/"+pageName+".jsp").forward(req,resp);
    }
}

package com.pn.filter;

import com.pn.utils.JdbcUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class TransactionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        //拿到当前线程的连接
        Connection con = JdbcUtil.getConnection();

        try {
            //1.开启事务
            con.setAutoCommit(false);

            //2.放行
            chain.doFilter(req, resp);

            //3.提交事务
            con.commit();
        } catch (Exception e) {
           //3.回滚事务
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            //4.关连接
            JdbcUtil.closeConnection();
        }
    }
}

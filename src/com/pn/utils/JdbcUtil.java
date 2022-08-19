package com.pn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
  Jdbc工具
  1.提供静态封装方法获取当前线程绑定的连接
  2.提供静态封装方法关闭当前线程绑定的连接
 */
public class JdbcUtil {

    /*
      创建ThreadLocal容器,泛型是Connection,目的就是给每个线程提供
      一个Connection
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //最初且只加载一次驱动类
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接的静态封装方法
    public static Connection getConnection(){
        //取当前线程绑定的Connection
        Connection con = threadLocal.get();
        if(con==null){//线程一开始在ThreadLocal中没有绑定Connection对象
            try {
                //新建连接
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oa?useOldAliasMetadataBehavior=true", "root", "1234");
                //将新建的连接在ThreadLocal中绑定给当前线程
//                con = DriverManager.getConnection("jdbc:mysql://192.168.213.130:3306/oa?useOldAliasMetadataBehavior=true", "root", "1234");

                threadLocal.set(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    //关闭连接的静态封装方法
    public static void closeConnection(){
        //取当前线程绑定的Connection
        Connection con = threadLocal.get();
        if(con!=null){//说明当前线程已经绑定了连接了,而且拿到了
            try {
                con.close();//关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocal.remove();//将当前线程绑定的连接的映射关系从threadLocal中移除
        }
    }

}

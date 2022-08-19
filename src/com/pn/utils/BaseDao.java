package com.pn.utils;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
  BaseDao还是作为所有Dao的父类被继承,来给所有dao提供查询和更新的封装方法,
  无非就是查询和更新的封装方法中获取连接调用的是JdbcUtil.getConnection(),
  获取的是当前线程的连接。
 */
public class BaseDao {

    //更新的封装方法
    public int update(String sql, Object...param){
        //拿当前线程绑定的连接
        Connection con = JdbcUtil.getConnection();
        PreparedStatement pt = null;
        try {
            pt = con.prepareStatement(sql);

            if(param!=null&&param.length>0){
                for (int i = 0; i < param.length; i++) {
                    pt.setObject(i+1, param[i]);
                }
            }

            return pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            if(pt!=null){
                try {
                    pt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //查询的封装方法
    public <T> List<T> query(String sql, Class<T> clz, Object...param){
        //拿当前线程绑定的连接
        Connection con = JdbcUtil.getConnection();
        PreparedStatement pt = null;
        ResultSet rs = null;
        List<T> beanList = new ArrayList<>();
        try {
            pt = con.prepareStatement(sql);

            if(param!=null&&param.length>0){
                for (int i = 0; i < param.length; i++) {
                    pt.setObject(i+1, param[i]);
                }
            }

            rs = pt.executeQuery();

            if(rs==null)
                return null;

            ResultSetMetaData metaData = rs.getMetaData();//结果集元数据(列的信息)
            int columnCount = metaData.getColumnCount();//拿列数

            while(rs.next()){
                //创建个实体对象
                T bean = clz.getConstructor().newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    //当前行第i列的值
                    Object columnValue = rs.getObject(i);

                    //当前列对应的java类型
                    String columnJavaType = metaData.getColumnClassName(i);

                    //当前列的列名
                    String columnName = metaData.getColumnName(i);
                    //通过列名得到这个列在实体类中对应的set方法名
                    String setName = "set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1);

                    //执行当前列对应的实体类的属性的set方法,把当前列的值赋值给实体类对应的属性
                    Method setMethod = clz.getMethod(setName, Class.forName(columnJavaType));
                    setMethod.invoke(bean,columnValue);
                }
                beanList.add(bean);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            if(pt!=null){
                try {
                    pt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return beanList;
    }
}

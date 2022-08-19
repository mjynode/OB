package com.pn.dao.impl;

import com.pn.dao.UserDao;
import com.pn.entitys.User;
import com.pn.utils.BaseDao;

import java.util.List;

//用户dao接口的实现
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public List<User> queryAllUser() {
        String sql = "select u.id,u.loginname,u.password,u.realname,u.email," +
                "u.sex,u.phone,u.did,d.dname,group_concat(p.pname) pnames " +
                "from t_users u,t_dept d,t_user_post up,t_posts p " +
                "where u.did = d.id and u.id = up.uid and up.pid = p.pid " +
                "group by u.id";
        return query(sql, User.class);
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into t_users values(default, ?,?,?,?,?,?,?)";
        Object[] params = {user.getLoginname(), user.getPassword(),
                user.getRealname(), user.getEmail(),
                user.getSex(), user.getPhone(), user.getDid()};
        update(sql, params);
    }

    @Override
    public User getUserByLoginName(String loginname) {
        String sql = "select * from t_users where loginname = ?";
        List<User> users = query(sql, User.class, loginname);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer uid) {
        String sql = "delete from t_users where id = ?";
        update(sql, uid);
    }

    @Override
    public User queryUserById(Integer id) {
        String sql = "select * from t_users where id = ?";
        List<User> users = query(sql, User.class, id);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update t_users set password=?, realname=?, email=?, sex=?, phone=?, did=? where id = ?";
        Object[] params = {user.getPassword(),user.getRealname(),user.getEmail(),
                user.getSex(),user.getPhone(),user.getDid(),user.getId()};
        update(sql, params);
    }
}

package com.pn.dao;

import com.pn.entitys.User;

import java.util.List;

//用户dao接口
public interface UserDao {

    //查询所有用户包括所在部门所有岗位
    public List<User> queryAllUser();

    //添加用户的方法
    public void addUser(User user);

    //根据登录名查询用户的方法
    public User getUserByLoginName(String loginname);

    //根据用户id删除用户信息的方法
    public void deleteUser(Integer uid);

    //根据用户id查询用户信息的方法
    public User queryUserById(Integer id);

    //根据用户id修改其它信息的方法
    public void updateUser(User user);
}

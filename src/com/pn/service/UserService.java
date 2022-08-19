package com.pn.service;

import com.pn.Exception.UserException;
import com.pn.dao.UserDao;
import com.pn.dao.UserPostDao;
import com.pn.dao.impl.UserDaoImpl;
import com.pn.dao.impl.UserPostDaoImpl;
import com.pn.entitys.User;
import com.pn.entitys.UserPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//用户业务类
public class UserService {

    private UserDao userDao = new UserDaoImpl();

    private UserPostDao upd = new UserPostDaoImpl();

    //查询所有用户包括所在部门所有岗位的业务方法
    public List<User> queryAllUserService(){
        return userDao.queryAllUser();
    }

    //添加用户的业务方法
    public void addUserService(User user, String[] ids){
        //添加用户
        userDao.addUser(user);

        //根据登录名查询用户的uid
        User u = userDao.getUserByLoginName(user.getLoginname());
        Integer uid = u.getId();

        //添加用户岗位关系
        UserPost up = new UserPost();
        up.setUid(uid);

        for (String id : ids) {
            int pid = Integer.parseInt(id);
            up.setPid(pid);
            upd.addUserPost(up);
        }
    }

    //判断登录名是否已存在的业务方法
    public boolean isExistLoginname(String loginname){
        User user = userDao.getUserByLoginName(loginname);
        return user!=null;
    }

    //根据用户id删除用户的业务方法
    public void deleteUserService(Integer uid){
        userDao.deleteUser(uid);
        upd.deleteUserPost(uid);
    }

    //根据用户id查询用户及所有用户岗位关系的业务方法
    public Map<String, Object> queryUserInfo(Integer uid){
        User user = userDao.queryUserById(uid);
        List<UserPost> userPosts = upd.queryUserPostByUid(uid);

        Map<String,Object> map = new HashMap<>();
        map.put("user", user);
        map.put("userPosts", userPosts);
        return map;
    }

    //修改用户的业务方法
    public void updateUserService(User user, String[] ids){

        //修改用户表的用户信息
        userDao.updateUser(user);

        //删除该用户之前所有的用户岗位关系
        upd.deleteUserPost(user.getId());

        //重新添加用户岗位关系
        UserPost up = new UserPost();
        up.setUid(user.getId());
        for (String id : ids) {
            int pid = Integer.parseInt(id);
            up.setPid(pid);
            upd.addUserPost(up);
        }
    }

    public void loginService(String loginname,String password) throws UserException {
        User user = userDao.getUserByLoginName(loginname);

        if(user==null){
            throw new UserException("用户名不存在");
        }
        if(!user.getPassword().equals(password)){
            throw new UserException("密码错误");
        }
    }
}

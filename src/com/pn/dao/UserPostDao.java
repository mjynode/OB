package com.pn.dao;

import com.pn.entitys.UserPost;

import java.util.List;

//操作t_user_post表的dao接口
public interface UserPostDao {

    //添加 用户岗位关系 的方法
    public void addUserPost(UserPost up);

    //根据用户id删除用户岗位关系
    public void deleteUserPost(Integer uid);

    //根据用户id查询用户岗位关系的方法
    public List<UserPost> queryUserPostByUid(Integer uid);
}

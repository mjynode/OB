package com.pn.dao.impl;

import com.pn.dao.UserPostDao;
import com.pn.entitys.UserPost;
import com.pn.utils.BaseDao;

import java.util.List;

public class UserPostDaoImpl extends BaseDao implements UserPostDao {

    @Override
    public void addUserPost(UserPost up) {
        String sql = "insert into t_user_post values(?,?)";
        update(sql, up.getUid(), up.getPid());
    }

    @Override
    public void deleteUserPost(Integer uid) {
        String sql = "delete from t_user_post where uid = ?";
        update(sql, uid);
    }

    @Override
    public List<UserPost> queryUserPostByUid(Integer uid) {
        String sql = "select * from t_user_post where uid = ?";
        return query(sql, UserPost.class, uid);
    }
}

package com.pn.dao.impl;

import com.pn.dao.PostMenuDao;
import com.pn.entitys.PostMenu;
import com.pn.utils.BaseDao;

import java.util.List;

//PostMenuDao接口的实现
public class PostMenuDaoImpl extends BaseDao implements PostMenuDao {

    @Override
    public void addPostMenu(PostMenu pm) {
        String sql = "insert into t_post_menu values(?, ?)";
        update(sql, pm.getPid(), pm.getMid());
    }

    @Override
    public List<PostMenu> queryPostMenu(Integer pid) {
        String sql = "select * from t_post_menu where pid = ?";
        return query(sql, PostMenu.class, pid);
    }

    @Override
    public void deletePostMenu(Integer pid) {
        String sql = "delete from t_post_menu where pid = ?";
        update(sql, pid);
    }
}

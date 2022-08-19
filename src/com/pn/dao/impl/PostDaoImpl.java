package com.pn.dao.impl;

import com.pn.dao.PostDao;
import com.pn.entitys.Post;
import com.pn.service.PostService;
import com.pn.utils.BaseDao;

import java.util.List;

public class PostDaoImpl extends BaseDao implements PostDao {

    @Override
    public List<Post> queryAllPost() {
        String sql = "select * from t_posts";
        return query(sql, Post.class);
    }

    @Override
    public void deletePost(Integer id) {
        String sql = "delete from t_posts where pid = ?";

        update(sql, id);
    }

    @Override
    public void addPost(Post post) {
        String sql = "insert into t_posts values(default,?,?)";

        update(sql, post.getPname(), post.getPdesc());
    }


    public Post queryPost(Integer id) {

        String sql = "select * from t_posts where pid = ?";
        List<Post> posts = query(sql, Post.class, id);
        if (posts != null && posts.size() == 1) {
            return posts.get(0);
        }
        return null;
    }

    public void updatePost(Post post) {
        String sql = "update t_posts set pname = ?,pdesc=? where pid = ?";

    }
}

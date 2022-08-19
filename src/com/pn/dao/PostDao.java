package com.pn.dao;

import com.pn.entitys.Post;

import java.sql.PseudoColumnUsage;
import java.util.List;

public interface PostDao {
    public List<Post> queryAllPost();

    public void deletePost(Integer id);

    public void addPost(Post post);

    public Post queryPost(Integer id);

    public void updatePost(Post post);
}

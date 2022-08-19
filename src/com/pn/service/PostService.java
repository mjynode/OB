package com.pn.service;

import com.pn.dao.PostDao;
import com.pn.dao.impl.PostDaoImpl;
import com.pn.entitys.Post;

import java.util.List;

public class PostService {
    private PostDao postDao = new PostDaoImpl();

    public List<Post> queryAllPostService(){
        return postDao.queryAllPost();
    }

    public void deletePostService(Integer id){
        postDao.deletePost(id);
    }
    public void addPostService(Post post){
        postDao.addPost(post);
    }

    public Post queryPostService(Integer id){
        return postDao.queryPost(id);
    }

    public void updatePostService(Post post){
        postDao.updatePost(post);
    }
}

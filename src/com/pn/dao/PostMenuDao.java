package com.pn.dao;

import com.pn.entitys.PostMenu;

import java.util.List;

//操作t_post_menu表的dao接口
public interface PostMenuDao {

    //添加数据的方法
    public void addPostMenu(PostMenu pm);

    //根据岗位id查询其对应的所有的岗位菜单关系
    public List<PostMenu> queryPostMenu(Integer pid);

    //根据岗位id删除之前所有的岗位菜单关系 --- 清空之前授权
    public void deletePostMenu(Integer pid);
}

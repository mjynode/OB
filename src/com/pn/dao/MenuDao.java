package com.pn.dao;

import com.pn.entitys.Menu;

import java.util.List;

public interface MenuDao {
    public List<Menu> queryAllMenu();

    //查询指定用户的角色的菜单的方法
    public List<Menu> queryPostMenu(String loginname);

}

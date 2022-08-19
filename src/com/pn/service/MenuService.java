package com.pn.service;

import com.pn.dao.MenuDao;
import com.pn.dao.impl.MenuDaoImpl;
import com.pn.entitys.Menu;

import java.util.List;

public class MenuService {
    private MenuDao menuDao = new MenuDaoImpl();

    public List<Menu> queryMenuService(){
        return menuDao.queryAllMenu();
    }

    //查询指定用户的角色的菜单的业务方法
    public List<Menu> QueryPostMenuService(String loginname){
        return menuDao.queryPostMenu(loginname);
    }
}

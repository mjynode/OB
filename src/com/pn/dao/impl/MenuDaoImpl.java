package com.pn.dao.impl;

import com.pn.dao.MenuDao;
import com.pn.entitys.Menu;
import com.pn.utils.BaseDao;

import java.util.List;

public class MenuDaoImpl extends BaseDao implements MenuDao {
    @Override
    public List<Menu> queryAllMenu() {
        String sql = "select * from t_menus";
        return query(sql,Menu.class);
    }

    @Override
    public List<Menu> queryPostMenu(String loginname) {
        String sql = "select distinct m.* from " +
                "t_users u,t_user_post up,t_post_menu pm,t_menus m " +
                "where u.id=up.uid and up.pid=pm.pid and pm.mid=m.id " +
                "and loginname = ?";
        return query(sql, Menu.class, loginname);
    }
}


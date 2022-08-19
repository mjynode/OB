package com.pn.service;

import com.pn.dao.PostMenuDao;
import com.pn.dao.impl.PostMenuDaoImpl;
import com.pn.entitys.PostMenu;

import java.util.List;

//岗位菜单业务类
public class PostMenuService {

    private PostMenuDao pmd = new PostMenuDaoImpl();

    //授权业务方法
    public void addPostMenus(String ids){
        String[] idArray = ids.split(",");

        //岗位id
        int pid = Integer.parseInt(idArray[0]);
        //根据岗位id删除之前所有的岗位菜单关系 --- 清空之前的授权
        pmd.deletePostMenu(pid);

        //重新保存岗位菜单关系 --- 重新授权
        PostMenu pm = new PostMenu();
        pm.setPid(pid);

        for(int i=0;i<idArray.length-1;i++){
            pm.setMid(Integer.parseInt(idArray[i+1]));
            pmd.addPostMenu(pm);
        }
    }

    //根据岗位id查询其对应的所有的岗位菜单关系的业务方法
    public List<PostMenu> queryPostMenuService(Integer pid){
        return pmd.queryPostMenu(pid);
    }
}

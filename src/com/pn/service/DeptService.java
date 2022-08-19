package com.pn.service;

import com.pn.dao.DeptDao;
import com.pn.dao.impl.DeptDaoImpl;
import com.pn.entitys.Dept;

import java.util.List;

public class DeptService {
    private DeptDao deptDao = new DeptDaoImpl();

    public List<Dept> queryAllDeptService(){
        return deptDao.queryAllDept();
    }

    //判断部门id是否是上级部门

    public boolean idParentDeptService(Integer id){
        List<Dept> depts = deptDao.queryDeptByPid(id);
        return depts.size()>0;
    }

    //根据id删除部门
    public void deleteDeptService(Integer id){
        deptDao.deleteDept(id);
    }

    //查询所有部门的业务
    public List<Dept> queryAllService(){
        return deptDao.queryAll();
    }

    //添加部门的方法
    public void addDeptService(Dept dept){
        deptDao.addDept(dept);
    }
}

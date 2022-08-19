package com.pn.dao;

import com.pn.entitys.Dept;

import java.util.List;

public interface DeptDao {

    public List<Dept> queryAllDept();

    //根据Id查询该部门的下级部门
    public List<Dept> queryDeptByPid(Integer pid);


    //根据Id删除部门
    public void deleteDept(Integer id);

    //查询所有部门

    public List<Dept> queryAll();

    //添加部门

    public void addDept(Dept dept);
}

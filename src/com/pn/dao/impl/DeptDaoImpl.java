package com.pn.dao.impl;

import com.pn.dao.DeptDao;
import com.pn.entitys.Dept;
import com.pn.utils.BaseDao;

import java.util.List;

public class DeptDaoImpl extends BaseDao implements DeptDao {
    @Override
    public List<Dept> queryAllDept() {
        String sql = "select dn.id,dn.dname,dn.ddesc,up.id pid,up.dname pdname "+
                "from t_dept dn left join t_dept up "+
                "on dn.pid = up.id";
        return query(sql,Dept.class);
    }

    @Override
    public List<Dept> queryDeptByPid(Integer pid) {
        String sql = "select * from t_dept where pid = ?";
        return query(sql,Dept.class,pid);
    }

    @Override
    public void deleteDept(Integer id) {
        String sql = "delete from t_dept where id = ? ";
        update(sql,id);

    }

    public List<Dept> queryAll(){
        String sql = "select * from t_dept";
        return query(sql,Dept.class);
    }

    public void addDept(Dept dept){
        String sql = "insert into t_dept values(default,?,?,?)";
        update(sql,dept.getPid(),dept.getDname(),dept.getDdesc());
    }
}

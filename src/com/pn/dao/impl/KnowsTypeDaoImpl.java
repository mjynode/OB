package com.pn.dao.impl;

import com.pn.dao.KnowsTypeDao;
import com.pn.entitys.KnowsType;
import com.pn.utils.BaseDao;

import java.util.List;

public class KnowsTypeDaoImpl extends BaseDao implements KnowsTypeDao {

    @Override
    public List<KnowsType> queryAllType() {
       String sql = "select * from t_knows_type";
       return query(sql, KnowsType.class);
    }

    @Override
    public void addType(KnowsType type) {
        String sql = "insert into t_knows_type values(default, ?, ?)";
        update(sql, type.getTypename(), type.getPtypeid());
    }

    @Override
    public KnowsType getTypeByNamePid(String typename, Integer pid) {
        String sql = "select * from t_knows_type where typename = ? and ptypeid = ?";
        List<KnowsType> types = query(sql, KnowsType.class, typename, pid);
        if(types.size()>0){
            return types.get(0);
        }
        return null;
    }

    @Override
    public List<KnowsType> queryTypeByPid(Integer pid) {
        String sql = "select * from t_knows_type where ptypeid = ?";
        return query(sql, KnowsType.class, pid);
    }

    @Override
    public void deleteType(Integer id) {
        String sql = "delete from t_knows_type where typeid = ?";
        update(sql, id);
    }
}

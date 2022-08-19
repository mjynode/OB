package com.pn.dao.impl;

import com.pn.dao.KnowsContentDao;
import com.pn.entitys.KnowsContent;
import com.pn.utils.BaseDao;

import java.util.List;

public class KnowsContentDaoImpl extends BaseDao implements KnowsContentDao {

    @Override
    public List<KnowsContent> queryContentByTypeid(Integer typeid) {
        String sql = "select * from t_knows_content where typeid = ?";
        return query(sql, KnowsContent.class, typeid);
    }

    @Override
    public void addKnowsContent(KnowsContent kc) {
        String sql = "insert into t_knows_content values(default, ?, ?, ?, ?, ?)";
        Object[] params = {kc.getTitle(), kc.getContent(), kc.getVersionid(),
                           kc.getLasttime(), kc.getTypeid()};
        update(sql, params);
    }

    @Override
    public KnowsContent getMaxVid(Integer typeid) {
        String sql = "select max(versionid) versionid from t_knows_content where typeid = ?";
        List<KnowsContent> contents = query(sql, KnowsContent.class, typeid);
        if(contents.size()>0){
            return contents.get(0);
        }
        return null;
    }

    @Override
    public void deleteContent(Integer id) {
        String sql = "delete from t_knows_content where id = ?";
        update(sql, id);
    }
}

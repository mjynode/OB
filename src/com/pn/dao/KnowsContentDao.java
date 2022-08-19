package com.pn.dao;

import com.pn.entitys.KnowsContent;

import java.util.List;

public interface KnowsContentDao {

    //根据分类id查询该分类下的所有版本内容
    public List<KnowsContent> queryContentByTypeid(Integer typeid);

    //添加版本内容的方法
    public void addKnowsContent(KnowsContent kc);

    //根据分类id查询该分类下的内容的最大版本号的方法
    public KnowsContent getMaxVid(Integer typeid);

    //根据内容id删除分类内容的方法
    public void deleteContent(Integer id);
}

package com.pn.dao;

import com.pn.entitys.KnowsType;

import java.util.List;

//操作t_knows_type的dao接口
public interface KnowsTypeDao {

    //查询所有分类的方法
    public List<KnowsType> queryAllType();

    //添加分类的方法
    public void addType(KnowsType type);

    //根据分类名称和所属上级分类id查询分类的方法
    public KnowsType getTypeByNamePid(String typename, Integer pid);

    //根据pid查询所有分类
    public List<KnowsType> queryTypeByPid(Integer pid);

    //根据id删除分类
    public void deleteType(Integer id);
}

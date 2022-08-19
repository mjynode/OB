package com.pn.service;

import com.pn.dao.KnowsTypeDao;
import com.pn.dao.impl.KnowsTypeDaoImpl;
import com.pn.entitys.KnowsType;

import java.util.List;

public class KnowsTypeService {

    private KnowsTypeDao ktd = new KnowsTypeDaoImpl();

    //查询所有分类的业务方法
    public List<KnowsType> allKnowsTypeService(){
        return ktd.queryAllType();
    }

    //添加分类的业务方法
    public KnowsType addTypeService(KnowsType type){
        //添加分类
        ktd.addType(type);
        //再根据分类名称和上级分类id把该分类再查出来
        return ktd.getTypeByNamePid(type.getTypename(), type.getPtypeid());
    }

    //判断指定分类是否是上级分类的业务方法
    public boolean isUpType(Integer id){
        List<KnowsType> types = ktd.queryTypeByPid(id);
        return types.size()>0;
    }

    //根据id删除分类的业务方法
    public void deleteTypeService(Integer id){
        ktd.deleteType(id);
    }
}

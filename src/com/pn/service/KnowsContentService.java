package com.pn.service;

import com.pn.dao.KnowsContentDao;
import com.pn.dao.impl.KnowsContentDaoImpl;
import com.pn.entitys.KnowsContent;

import java.util.List;

public class KnowsContentService {

    private KnowsContentDao ksd = new KnowsContentDaoImpl();

    //根据分类id查询该分类下的所有版本内容的业务方法
    public List<KnowsContent> contentByTypeidService(Integer typeid){
        return ksd.queryContentByTypeid(typeid);
    }

    //添加版本内容的业务方法
    public void addContentService(KnowsContent kc){
        //拿该分类下内容的最大的版本id
        Integer typeid = kc.getTypeid();
        KnowsContent content = ksd.getMaxVid(typeid);
        Integer maxVid = content.getVersionid();

        if(maxVid==null){//分类下还没有版本内容
            maxVid = 0;
        }
        //新的版本号
        maxVid+=1;
        kc.setVersionid(maxVid);

        //添加
        ksd.addKnowsContent(kc);
    }

    //删除版本内容的业务方法
    public void deleteContentService(Integer id){
        ksd.deleteContent(id);
    }
}

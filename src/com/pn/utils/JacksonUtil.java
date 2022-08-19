package com.pn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.List;
import java.util.Map;

public class JacksonUtil {

    //json转换器 --- 作为工具类的静态成员变量
    private static ObjectMapper mapper = new ObjectMapper();

    //将实体对象、Map、BeanList转成json字符串的封装方法
    public static String objToJson(Object obj){
        String jsonStr = null;
        try {
            //转换
            jsonStr = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    //================================================================================

    //json字符串转实体对象
    public static <T> T jsonToObj(String jsonStr, Class<T> type) {
        /*
          转换:
          json字符串转单个实体对象调用的是转换器的readValue()方法,参数一是json
          字符串,参数二是json字符串要转换的实体对象的类型,返回值就是转换后的实体
          对象;
         */
        T t = null;
        try {
            t = mapper.readValue(jsonStr, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }

    //json字符串转Map
    public static Map<String,Object> jsonToMap(String jsonStr) {

        //拿到类型工厂类的对象 --- 指定类型的
        TypeFactory typeFactory = mapper.getTypeFactory();
        //指定的一个键是String类型值是Object类型的Map类型
        MapType mapType = typeFactory.constructMapType(Map.class, String.class, Object.class);

        //转换
        Map<String,Object> map = null;
        try {
            map = mapper.readValue(jsonStr, mapType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return map;
    }

    //json字符串转BeanList
    public static <T> List<T> jsonToBeanList(String jsonStr, Class<T> type) {

        //拿到类型工厂类对象 --- 指定类型的
        TypeFactory typeFactory = mapper.getTypeFactory();
        //指定一个泛型T类型的List类型
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, type);

        //转换
        List<T> beanList = null;
        try {
            beanList = mapper.readValue(jsonStr, collectionType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return beanList;
    }
}

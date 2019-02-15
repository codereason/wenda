package com.learn.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hy on 2019/2/15.
 */
public class ViewObject {
    private Map<String,Object> objs = new HashMap<>() ;
    public void set(String key,Object value){
        objs.put(key,value);

    }

    public Object get(String key){
        return objs.get(key);
    }
}

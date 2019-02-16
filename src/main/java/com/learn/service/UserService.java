package com.learn.service;

import com.learn.dao.UserDAO;
import com.learn.model.User;
import com.learn.utils.WendaUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import java.util.Random;
import java.util.UUID;


/**
 * Created by hy on 2019/2/15.
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getUser(int id) {
        return userDAO.selectById(id);
    }

    public Map<String, String> register(String username, String password) {
        Map<String, String> hashMap = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            hashMap.put("msg", "用户名不能为空");
            return hashMap;
        }
        if (StringUtils.isBlank(password)) {
            hashMap.put("msg", "密码不能为空");
            return hashMap;

        }

        User user = userDAO.selectByName(username);
        if(user!=null){
            //用户如果存在
            hashMap.put("msg", "用户名已经被注册");
            return hashMap;
        }
        //经过上面判断之后，正常的注册流程
        user = new User();
        user.setName(username);
        //用户名密码加salt
        user.setSalt(UUID.randomUUID().toString().substring(0,5));

        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));

        user.setPassword(WendaUtils.MD5(password+user.getSalt()));
        userDAO.addUser(user);

        return  hashMap;

    }


}

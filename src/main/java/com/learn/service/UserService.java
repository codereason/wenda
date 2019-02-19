package com.learn.service;

import com.learn.dao.LoginTicketDAO;
import com.learn.dao.UserDAO;
import com.learn.model.LoginTicket;
import com.learn.model.User;
import com.learn.utils.WendaUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by hy on 2019/2/15.
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    public User getUser(int id) {
        return userDAO.selectById(id);
    }
    public User selectByName(String name) {
        return userDAO.selectByName(name);
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
        if (user != null) {
            //用户如果存在
            hashMap.put("msg", "用户名已经被注册");
            return hashMap;
        }
        //经过上面判断之后，正常的注册流程
        user = new User();
        user.setName(username);
        //用户名密码加salt
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));

        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));

        user.setPassword(WendaUtils.MD5(password + user.getSalt()));
        userDAO.addUser(user);

        String ticket = addLoginTicket(user.getId());
        hashMap.put("ticket", ticket);

        return hashMap;

    }

    public Map<String, String> login(String username, String password) {
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
        if (user == null) {
            //用户不存在
            hashMap.put("msg", "用户名不存在！");
            return hashMap;
        }
        if (!WendaUtils.MD5(password + user.getSalt()).equals(user.getPassword())) {
            //登陆c错误
            hashMap.put("msg", "密码错误");
            return hashMap;

        }
        String ticket = addLoginTicket(user.getId());
        hashMap.put("ticket", ticket);
        return hashMap;

    }

    public String addLoginTicket(int userId) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date now = new Date();
        now.setTime(3600 * 24 * 100 + now.getTime());
        loginTicket.setExpired(now);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(loginTicket);
        return loginTicket.getTicket();
    }

    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);

    }

}

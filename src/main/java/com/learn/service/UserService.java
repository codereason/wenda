package com.learn.service;

import com.learn.dao.UserDAO;
import com.learn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hy on 2019/2/15.
 */
@Service
public class UserService {
    @Autowired
    UserDAO userdao;

    public User getUser(int id) {
        return userdao.selectById(id);
    }
}

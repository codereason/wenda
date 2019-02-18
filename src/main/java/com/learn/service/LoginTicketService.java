package com.learn.service;

import com.learn.dao.LoginTicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hy on 2019/2/17.
 */
@Service
public class LoginTicketService {
    @Autowired
    LoginTicketDAO loginTicketDAO;


}

package com.learn.service;

import org.springframework.stereotype.Service;

/**
 * Created by hy on 2019/2/13.
 */
@Service
public class WendaService {
    public String getMessage(int userId) {
        return "Hello Message: " + String.valueOf(userId);
    }
}

package com.learn.controller;

import com.learn.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by hy on 2019/2/19.
 */
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

}

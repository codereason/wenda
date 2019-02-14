package com.learn.controller;

import com.learn.aspect.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by hy on 2019/2/14.
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @RequestMapping(path = {"/", "/index"})
    public String index(HttpSession session) {

        return "index";
    }
}

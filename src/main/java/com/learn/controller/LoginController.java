package com.learn.controller;

import com.learn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by hy on 2019/2/16.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(path = {"/reg/"}, method = RequestMethod.POST)
    public String reg(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password) {

        Map<String, String> map = userService.register(username, password);
        try {
            if (map.containsKey("msg")) {
                //把出的问题信息返给前端
                model.addAttribute("msg", map.get("msg"));
                return "login";

            }
            return "redirect:/";
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return "login";
        }

    }

    @RequestMapping(path = {"/reglogin"}, method = RequestMethod.GET)
    public String reg(Model model) {


        return "login";
    }
}


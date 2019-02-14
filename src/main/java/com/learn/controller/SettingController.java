package com.learn.controller;
import com.learn.service.WendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by hy on 2019/2/13.
 */
@Controller
public class SettingController {
    @Autowired
    WendaService wendaService;

    @RequestMapping(path = {"/setting", })
    @ResponseBody
    public String index(HttpSession session) {
        return "Hello Wenda"+ wendaService.getMessage(1);
    }



}

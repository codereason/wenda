package com.learn.controller;

import com.learn.model.User;
import com.learn.service.WendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hy on 2019/2/13.
 */
//@Controller
public class IndexController {
    @Autowired
    WendaService wendaService;

    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index(HttpSession session) {
        return "Hello Wenda"+session.getAttribute("msg");
    }

    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam(value = "type",defaultValue = "1") int type,
                          @RequestParam(value = "key",defaultValue = "zz",required = false) String key) {
        return String.format("Profile Page Of %s / %d type: %d key: %s", groupId, userId, type, key);
    }
    @RequestMapping(path = {"/vm",},method = {RequestMethod.GET})

    public String template(Model model) {
        model.addAttribute("value1","vvvv1");
        model.addAttribute("user",new User("Lee"));

        return "home";
    }

    @RequestMapping(path = {"/request",},method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model, HttpServletRequest request,
                           HttpServletResponse response,
                           HttpSession session){
        StringBuilder sb = new StringBuilder();
        sb.append(request.getMethod()+"<br>");
        sb.append(request.getQueryString()+"<br>");
        sb.append(request.getPathInfo()+"<br>");
        sb.append(request.getRequestURI()+"<br>");
        return sb.toString();


    }
    @RequestMapping(path = {"/redirect/{code}",},method = {RequestMethod.GET})

    public String redirect(@PathVariable("code") int code,
                           HttpSession session){
        session.setAttribute("msg","jump from redirect");

        return "redirect:/";
    }


}

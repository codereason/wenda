package com.learn.controller;

import com.learn.aspect.LogAspect;
import com.learn.model.Question;
import com.learn.model.ViewObject;
import com.learn.service.QuestionService;
import com.learn.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hy on 2019/2/14.
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @RequestMapping(path = {"/user/{userId}",},method = RequestMethod.GET)
    public String userIndex(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("vos",getQuestions(userId,0,10));

        return "index";
    }

    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model,
                        @RequestParam(value = "pop", defaultValue = "0") int pop) {
        model.addAttribute("vos", getQuestions(0, 0, 10));
        return "index";
    }

    private List<ViewObject> getQuestions(int userId,int offset,int limit){
        List<Question> questionList = questionService.getLatestQuestions(userId,offset,limit);
        List<ViewObject> vos = new ArrayList<>() ;
        for (Question qs:questionList){
            ViewObject viewObject = new ViewObject();
            viewObject.set("question",qs);
            viewObject.set("user",userService.getUser(qs.getUserId()));
            vos.add(viewObject);

        }
        return vos;
    }

}

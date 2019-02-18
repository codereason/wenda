package com.learn.controller;

import com.learn.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by hy on 2019/2/17.
 */
@Controller
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    @RequestMapping(path = {"/question/add"}, method = RequestMethod.POST)
    public String reg(Model model, @RequestParam("title") String title,
                      @RequestParam("content") String content) {
        try {
            questionService.

        } catch (Exception e) {
            logger.error("增加题目失败" + e.getMessage());

        }
        return "{'code':0,'msg':'hhh'}";

    }
}

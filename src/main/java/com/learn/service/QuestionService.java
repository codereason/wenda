package com.learn.service;

import com.learn.dao.QuestionDAO;
import com.learn.model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hy on 2019/2/14.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLatestQuestions(userId, offset, limit);

    }

    public int addQuestion(Question question){
        questionDAO.addQuestion(question);
        return questionDAO.addQuestion(question)>0?question.getId():0;

    }
}

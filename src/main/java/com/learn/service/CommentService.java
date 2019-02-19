package com.learn.service;

import com.learn.dao.CommentDao;
import com.learn.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hy on 2019/2/19.
 */
@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;

    public List<Comment> getCommentsByEntity(int entity_id,int entity_type){
        return commentDao.selectCommentsByEntity(entity_id,entity_type);

    }
}

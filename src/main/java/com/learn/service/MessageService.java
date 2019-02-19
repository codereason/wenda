package com.learn.service;

import com.learn.dao.MessageDAO;
import com.learn.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hy on 2019/2/19.
 */
@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;

    @Autowired
    SensitiveService sensitiveService;

    public int addMessage(Message message) {
        message.setContent(sensitiveService.filter(message.getContent()));

        return messageDAO.addMessage(message) > 0 ? message.getId() : 0;
    }

    List<Message> getConversationDetail(String conversationId,
                                        int offset,
                                        int limit) {
        return messageDAO.getConversationDetail(conversationId,offset,limit);
    }
}

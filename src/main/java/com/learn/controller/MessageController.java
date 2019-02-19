package com.learn.controller;

import com.learn.model.HostHolder;
import com.learn.model.Message;
import com.learn.model.User;
import com.learn.model.ViewObject;
import com.learn.service.MessageService;
import com.learn.service.UserService;
import com.learn.utils.WendaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hy on 2019/2/19.
 */
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    @RequestMapping(path = {"/msg/addMessage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,
                             @RequestParam("content") String content) {
        try {
            if (hostHolder.getUser() == null) {
                return WendaUtils.getJSONString(999, "用户未登录");


            }
            User user = userService.selectByName(toName);
            if (user == null) {
                return WendaUtils.getJSONString(1, "用户不存在");

            }
            Message message = new Message();
            message.setCreatedDate(new Date());
            message.setFromId(hostHolder.getUser().getId());
            message.setToId(user.getId());
            message.setContent(content);
            messageService.addMessage(message);
            return WendaUtils.getJSONString(0);
        } catch (Exception e) {
            logger.error("消息发送失败" + e.getMessage());
            return WendaUtils.getJSONString(1, "发送消息失败");
        }
    }

    @RequestMapping(path = {"/msg/list"}, method = {RequestMethod.GET})
    public String getConversationList(Model model) {
        if (hostHolder.getUser() == null) {
            return "redirect:/reglogin";
        }
        int localHostId = hostHolder.getUser().getId();
        List<Message> conversationList = messageService.getConversationList(localHostId, 0, 10);

        List<ViewObject> conversations = new ArrayList<>();
        for (Message message : conversationList) {
            ViewObject viewObject = new ViewObject();
            viewObject.set("message", message);
            int targetId = message.getFromId() == localHostId ? message.getToId() : message.getFromId();
            User user = userService.getUser(targetId);
            viewObject.set("user",user);
            int unread = messageService.getConvesationUnreadCount(localHostId,message.getConversationId());
            viewObject.set("unread",unread);
            conversations.add(viewObject);
        }
        model.addAttribute("conversations",conversations);
        return "letter";
    }

    @RequestMapping(path = {"/msg/detail"}, method = {RequestMethod.GET})
    public String getConversationDetail(Model model,
                                        @RequestParam("conversationId") String conversationId) {
        try {
            List<Message> messageList = messageService.getConversationDetail(conversationId, 0, 10);
            List<ViewObject> messages = new ArrayList<>();
            for (Message message : messageList) {
                ViewObject viewObject = new ViewObject();
                viewObject.set("message", message);
                viewObject.set("user", userService.getUser(message.getFromId()));
                messages.add(viewObject);
            }
            model.addAttribute("messages", messages);
        } catch (Exception e) {
            logger.error("获取对话失败" + e.getMessage());
        }
        return "letterDetail";
    }

}

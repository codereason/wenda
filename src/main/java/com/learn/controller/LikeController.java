package com.learn.controller;

import com.learn.model.HostHolder;
import com.learn.service.LikeService;
import com.learn.utils.WendaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hy on 2019/2/20.
 */
@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/like"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addComment(@RequestParam("commentId") int commentId){
        if (hostHolder.getUser()==null){
            return WendaUtils.getJSONString(999);
        }
        long likeCount = likeService.like(hostHolder.getUser().getId(),WendaUtils.ENTITY_COMMENT,commentId);
        return WendaUtils.getJSONString(0,String.valueOf(likeCount));
    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("commentId") int commentId) {
        if (hostHolder.getUser() == null) {
            return WendaUtils.getJSONString(999);
        }

        long likeCount = likeService.disLike(hostHolder.getUser().getId(), WendaUtils.ENTITY_COMMENT, commentId);
        return WendaUtils.getJSONString(0, String.valueOf(likeCount));
    }
}

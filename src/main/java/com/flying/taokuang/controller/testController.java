package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Comment;
import com.flying.taokuang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author NNShadow
 * @date 2019/12/30 15:58
 */
@RestController
public class testController {
//    @Autowired
//    private CommentService commentService;
//
//    @RequestMapping("/test")
//    public String test(@RequestParam(value = "contentId") int contentId) {
//        List<Comment> comments = commentService.selectByContentId(contentId);
//        for (int i = 0; i < comments.size(); i++) {
//            System.out.println(comments.get(i).toString());
//        }
//        return commentService.selectByContentId(contentId).toString();
    }
}

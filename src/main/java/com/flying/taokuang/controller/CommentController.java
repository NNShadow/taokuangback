package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Comment;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.CommentService;
import com.flying.taokuang.service.UserService;
import com.flying.taokuang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author NNShadow
 * @date 2019/8/27 21:08
 */
@RequestMapping("/comment")
@RestController
public class CommentController {
    private final static String encry = "salt";

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    /**
     * 添加商品评论
     * @param comment
     * @param token
     * @return
     */
    @RequestMapping("/add")
    public String addComment(Comment comment, @RequestParam(value = "token", required = false) String token){
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            return "token错误";
        }

        if (!StringUtils.isBlank(comment.getContent()) &&
                !StringUtils.isBlank(comment.getContentCommenter()) &&
                comment.getContentGoodsId() != 0){
            //获取评论者id，修改评论者
            String userId = (String) JwtUtil.getClamis(token, encry).get("id");
            User user = userService.selectByStudentId(userId);
            comment.setContentCommenter(user.getUsername());

            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());
            commentService.insert(comment);
            return "评论成功";
        }
        return "缺少信息";
    }

    /**
     * 根据商品查找评论
     * @param comment
     * @param token
     * @return
     */
    @RequestMapping("/select")
    public String selectComment(Comment comment, @RequestParam(value = "token", required = false) String token){
        List<Comment> commentList = commentService.selectByContentGoodsId(comment.getContentGoodsId());
        if (commentList != null){
            return null;
        }
        return commentList.toString();
    }
}

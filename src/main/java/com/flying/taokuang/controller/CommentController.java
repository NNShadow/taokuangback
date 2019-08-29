package com.flying.taokuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.dataobject.Comment;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.CommentService;
import com.flying.taokuang.service.UserService;
import com.flying.taokuang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String addComment(Comment comment, @RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }

        if (!StringUtils.isBlank(comment.getContent()) &&
                !StringUtils.isBlank(comment.getContentCommenter()) &&
                comment.getContentGoodsId() != 0){
            //获取评论者id，修改评论者
            int userId = (int) JwtUtil.getClamis(token, encry).get("userId");
            User user = userService.selectById(userId);
            comment.setContentCommenter(user.getUsername());

            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());
            commentService.insert(comment);
            result.put("msg", "评论成功");
            result.put("success", true);
            return result.toJSONString();
        }
        result.put("msg", "缺少信息");
        result.put("success", false);
        return result.toJSONString();
    }

    /**
     * 根据商品查找评论
     * @param comment
     * @param token
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String selectComment(Comment comment, @RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        List<Comment> commentList = commentService.selectByContentGoodsId(comment.getContentGoodsId());
        if (commentList != null){
            result.put("msg", "评论内容");
            result.put("success", true);
            result.put("commentList", commentList);
            return result.toJSONString();
        }
        result.put("msg", "无内容");
        result.put("success", true);
        result.put("commentList", null);
        return result.toJSONString();
    }
}

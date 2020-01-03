package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Comment;
import com.flying.taokuang.dataobject.Result;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.CommentService;
import com.flying.taokuang.service.UserService;
import com.flying.taokuang.utils.JwtUtil;
import com.flying.taokuang.utils.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private ResponseData responseData;

    /**
     * 添加商品评论
     *
     * @param comment 实例化（评论）
     * @param token
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result addComment(Comment comment, @RequestParam(value = "token", required = false) String token) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }

        if (!StringUtils.isBlank(comment.getText()) &&
                comment.getContentId() != 0) {
            //获取评论者id，修改评论者
            int userId = (int) JwtUtil.getClamis(token, encry).get("userId");
            User user = userService.selectByUserId(userId);
            comment.setContentCommenterName(user.getUsername());
            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());
            commentService.insert(comment);
            return responseData.write("评论成功", 200, new HashMap<>());
        }
        return responseData.write("缺少信息", 404, new HashMap<>());
    }

    /**
     * 根据商品查找评论
     *
     * @param contentId 商品id
     * @param token
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result selectComment(@RequestParam(value = "contentId") int contentId,
                                @RequestParam(value = "token", required = false) String token) {
        if (contentId <= 0) {
            return responseData.write("商品id错误", 404, new HashMap<>());
        }
        List<Comment> commentList = commentService.selectByContentId(contentId);
        if (commentList != null) {
            return responseData.write("评论内容", 200, commentList);
        }
        return responseData.write("无内容", 200, new HashMap<>());
    }
}

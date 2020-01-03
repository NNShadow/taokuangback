package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Comment;
import com.flying.taokuang.dataobject.Content;
import com.flying.taokuang.dataobject.Result;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.CommentService;
import com.flying.taokuang.service.ContentService;
import com.flying.taokuang.service.UserService;
import com.flying.taokuang.utils.JwtUtil;
import com.flying.taokuang.utils.MD5Util;
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
 * @date 2019/8/27 20:36
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final static String encry = "salt";

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ResponseData responseData;

    /**
     * 用户注册
     *
     * @param user 实例化（用户）
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result signUp(User user) {
        user.setAgree(0);
        user.setMobilePhoneNumberVerified(0);
        user.setEmailVerified(0);
        user.setAuthority("user");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());

        if (judgeUserNotFinish(user)) {
            return responseData.write("有信息空缺", 404, new HashMap<>());
        }
        if (userService.insert(user) != 0) {
            return responseData.write("成功", 200, new HashMap<>());
        } else {
            return responseData.write("失败", 404, new HashMap<>());
        }
    }

    /**
     * 用户登陆
     *
     * @param user 实例化（用户）
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result login(User user) {
        HashMap map = new HashMap();
        String msg = "";
        if (!StringUtils.isBlank(user.getUsername()) && !StringUtils.isBlank(user.getPassword())) {
            User userSearch = userService.selectByUsername(user.getUsername());
            if (userSearch.getPassword().equals(MD5Util.md5(user.getPassword()))) {
                user.setUserId(userSearch.getUserId());
                //生成token返回
                String token = JwtUtil.getToken(user, "salt", 60 * 24 * 30);
                map.put("token", token);
                return responseData.write("生成token", 200, map);
            }
            msg = "密码错误";
        }
        if (msg.equals("")) {
            msg = "缺少信息";
        }
        map.put("token", "");
        return responseData.write(msg, 404, new HashMap<>());
    }

    /**
     * 修改用户信息
     *
     * @param user 实例化（用户）
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result modify(User user,
                         @RequestParam(value = "token", required = false) String token,
                         @RequestParam(value = "oldPassword", required = false) String oldPassword) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }

        if (judgeUserNotFinish(user) || StringUtils.isBlank(oldPassword)) {
            return responseData.write("有信息空缺", 404, new HashMap<>());
        }

        int userId = (int) JwtUtil.getClamis(token, encry).get("userId");
        int id = userService.selectByUserId(userId).getUserId();
        user.setUserId(userId);
        if (userService.update(user, oldPassword, id) != 0) {
            //修改每个商品对应的用户名
            List<Content> contentList = contentService.selectByUserId(id);
            contentList.stream().forEach(content -> {
                content.setUserId(user.getUserId());
                contentService.update(content);
            });
            //修改每个评论对应的用户名
            List<Comment> commentList = commentService.selectByContentCommenterId(id);
            commentList.stream().forEach(comment -> {
                comment.setContentCommenterName(user.getUsername());
                commentService.update(comment);
            });
            //修改商品对应的购买者名字
            List<Content> contentBuyerList = contentService.selectByBuyerId(id);
            contentBuyerList.stream().forEach(content -> {
                content.setBuyerId(user.getUserId());
                contentService.update(content);
            });
            return responseData.write("修改成功", 200, new HashMap<>());
        } else {
            return responseData.write("修改失败", 404, new HashMap<>());
        }
    }

    public boolean judgeUserNotFinish(User user) {
        if (!StringUtils.isBlank(user.getPassword()) && !StringUtils.isBlank(user.getStudentId()) && !StringUtils.isBlank(user.getUsername())) {
            return false;
        }
        return true;
    }
}

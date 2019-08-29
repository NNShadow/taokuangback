package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Content;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.ContentService;
import com.flying.taokuang.service.UserService;
import com.flying.taokuang.utils.JwtUtil;
import com.flying.taokuang.utils.PasswordEncryUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/signup")
    public String signUp(User user){
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        if (judgeUserNotFinish(user)){
            return "有信息空缺";
        }
        if (userService.insert(user) != 0){
            return "成功";
        }else {
            return "失败";
        }
    }

    /**
     * 用户登陆
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/login")
    public String login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!StringUtils.isBlank(user.getUsername()) && !StringUtils.isBlank(user.getPassword())){
            User userSearch = userService.selectByUsername(user.getUsername());
            if (userSearch.getPassword().equals(PasswordEncryUtil.encodeByMd5(user.getPassword()))){
                //生成token返回
                return JwtUtil.getToken(user, "salt", 60 * 30);
            }
        }
        return "登陆错误";
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/modify")
    public String modify(User user, @RequestParam(value = "token", required = false) String token){
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            return "token错误";
        }

        user.setUpdatedDate(new Date());
        if (judgeUserNotFinish(user)){
            return "有信息空缺";
        }
        String userNewName = user.getUsername();
        if (userService.update(user) != 0){
            //修改每个商品对应的用户名
            List<Content> contentList = contentService.selectByUsername((String) JwtUtil.getClamis(token, encry).get("username"));
            contentList.stream().forEach(content -> {
                content.setUpdatedDate(new Date());
                content.setUsername(userNewName);
                contentService.update(content);
            });
            return JwtUtil.getToken(user, "salt", 60 * 30);
        }else {
            return "失败";
        }
    }

    public boolean judgeUserNotFinish(User user){
        if (!StringUtils.isBlank(user.getPassword()) && !StringUtils.isBlank(user.getStudentId()) && !StringUtils.isBlank(user.getUsername())){
            return false;
        }
        return true;
    }
}

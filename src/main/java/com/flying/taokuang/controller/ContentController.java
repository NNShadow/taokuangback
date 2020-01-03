package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Content;
import com.flying.taokuang.dataobject.Result;
import com.flying.taokuang.service.ContentService;
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

/**
 * @author NNShadow
 * @date 2019/8/28 17:44
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    private final static String encry = "salt";

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseData responseData;

    /**
     * 添加商品，未加判空
     *
     * @param content 实例化（商品）
     * @param token
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result add(Content content, @RequestParam(value = "token", required = false) String token) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }
        if (content != null) {
            content.setCreatedDate(new Date());
            content = contentInit(content);
            //获取发布者姓名
            int userId = userService.selectByUserId((int) JwtUtil.getClamis(token, encry).get("userId")).getUserId();
            content.setUserId(userId);
            if (contentService.insert(content) != 0) {
                return responseData.write("添加成功", 200, new HashMap<>());
            }
        }
        return responseData.write("添加失败", 404, new HashMap<>());
    }

    /**
     * 删除商品，未加判空，未判断所属
     *
     * @param contentId 商品id
     * @param token
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result delete(@RequestParam(value = "contentId") int contentId, @RequestParam(value = "token", required = false) String token) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());

        }
        if (contentId != 0) {
            if (contentService.deleteById(contentId) != 0) {
                return responseData.write("deleteById", 200, new HashMap<>());
            }
        }
        return responseData.write("删除失败", 404, new HashMap<>());
    }

    /**
     * 修改商品，未加判空
     *
     * @param content 实例化（商品）
     * @param token
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result modify(Content content, @RequestParam(value = "token", required = false) String token) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }
        if (content != null) {
            content = contentInit(content);
            //获取发布者姓名
            int userId = userService.selectByUserId((int) JwtUtil.getClamis(token, encry).get("userId")).getUserId();
            content.setUserId(userId);
            if (contentService.update(content) != 0) {
                return responseData.write("修改成功", 200, new HashMap<>());
            }
        }
        return responseData.write("修改失败", 404, new HashMap<>());
    }

    /**
     * 初始化商品信息
     *
     * @param content 实例化（商品）
     * @return
     */
    public Content contentInit(Content content) {
        content.setUpdatedDate(new Date());
        content.setBird(0);
        content.setBuy(0);
        content.setBusiness(0);
        return content;
    }
}

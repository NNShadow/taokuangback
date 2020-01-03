package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Result;
import com.flying.taokuang.dataobject.User;
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

import java.util.HashMap;

/**
 * @author NNShadow
 * @date 2019/8/28 18:39
 */
@RequestMapping("/select")
@RestController
public class SelectController {
    private final static String encry = "salt";

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ResponseData responseData;

    /**
     * 查找所有商品
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result all(@RequestParam(value = "token", required = false) String token) {
        return responseData.write("所有商品查找成功", 200, contentService.selectAll());
    }

    /**
     * 查找我的商品
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/my", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result my(@RequestParam(value = "token", required = false) String token) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }

        int userId = (int) JwtUtil.getClamis(token, encry).get("userId");
        User user = userService.selectByUserId(userId);
        return responseData.write("我的商品", 200, contentService.selectByUserId(user.getUserId()));
    }

    /**
     * 按商品名称搜索
     *
     * @param token
     * @param type  商品类型
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result title(@RequestParam(value = "token", required = false) String token, @RequestParam(value = "type") String type) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }
        if (type != null) {
            return responseData.write("按商品名称搜索", 200, contentService.selectByType(type));
        }
        return responseData.write("缺少商品名称", 404, new HashMap<>());
    }

    /**
     * 模糊搜索
     *
     * @param token
     * @param keyword 关键词
     * @return
     */
    @RequestMapping(value = "/keyword", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result search(@RequestParam(value = "token", required = false) String token, @RequestParam(value = "keyword") String keyword) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }
        if (keyword != null) {
            return responseData.write("按商品名称搜索", 200, contentService.selectByKeyword(keyword));
        }
        return responseData.write("缺少关键词", 404, new HashMap<>());
    }
}

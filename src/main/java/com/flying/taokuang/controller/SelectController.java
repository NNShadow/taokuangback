package com.flying.taokuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.ContentService;
import com.flying.taokuang.service.UserService;
import com.flying.taokuang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 查找所有商品
     * @param token
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String all(@RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("msg", "所有商品查找成功");
        result.put("contentList", contentService.selectAll());
        return result.toJSONString();
    }

    /**
     * 查找我的商品
     * @param token
     * @return
     */
    @RequestMapping(value = "/my", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String my(@RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }

        int userId = (int) JwtUtil.getClamis(token, encry).get("userId");
        User user = userService.selectByUserId(userId);
        result.put("msg", "我的商品");
        result.put("success", true);
        result.put("contentList", contentService.selectByUserId(user.getUserId()));
        return result.toJSONString();
    }

    /**
     * 按商品名称搜索
     * @param token
     * @param type 商品类型
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String title(@RequestParam(value = "token", required = false) String token, @RequestParam(value = "type") String type){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }
        if (type != null){
            result.put("msg", "按商品名称搜索");
            result.put("success", true);
            result.put("contentList", contentService.selectByType(type));
            return result.toJSONString();
        }
        result.put("msg", "缺少商品名称");
        result.put("success", false);
        result.put("contentList", null);
        return result.toJSONString();
    }

    /**
     * 模糊搜索
     * @param token
     * @param keyword 关键词
     * @return
     */
    @RequestMapping(value = "/keyword", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String search(@RequestParam(value = "token", required = false) String token, @RequestParam(value = "keyword") String keyword){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }
        if (keyword != null){
            result.put("msg", "按商品名称搜索");
            result.put("success", true);
            result.put("contentList", contentService.selectByKeyword(keyword));
            return result.toJSONString();
        }
        result.put("msg", "缺少关键词");
        result.put("success", false);
        result.put("contentList", null);
        return result.toJSONString();
    }
}

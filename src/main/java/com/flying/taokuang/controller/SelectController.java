package com.flying.taokuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.service.ContentService;
import com.flying.taokuang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private ContentService contentService;

    /**
     * 查找所有商品
     * @param token
     * @return
     */
    @RequestMapping("/all")
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
    @RequestMapping("/my")
    public String my(@RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }

        String username = (String) JwtUtil.getClamis(token, encry).get("username");
        result.put("msg", "我的商品");
        result.put("success", true);
        result.put("contentList", contentService.selectByUsername(username));
        return result.toJSONString();
    }

    /**
     * 按商品名称搜索
     * @param token
     * @param type
     * @return
     */
    @RequestMapping("/type")
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

    //根据关键词，模糊搜索
    @RequestMapping("/keyword")
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

package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Content;
import com.flying.taokuang.service.ContentService;
import com.flying.taokuang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Content> all(@RequestParam(value = "token", required = false) String token){
        return contentService.selectAll();
    }

    /**
     * 查找我的商品
     * @param token
     * @return
     */
    @RequestMapping("/my")
    public String my(@RequestParam(value = "token", required = false) String token){
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            return "token错误";
        }

        String username = (String) JwtUtil.getClamis(token, encry).get("username");
        return contentService.selectByUsername(username).toString();
    }

    //商品名称搜索
    @RequestMapping("/type")
    public String title(@RequestParam(value = "token", required = false) String token, @RequestParam(value = "type") String type){
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            return "token错误";
        }
        if (type != null){
            return contentService.selectByType(type).toString();
        }
        return "无类型";
    }

    //根据关键词，模糊搜索
    @RequestMapping("/keyword")
    public String search(@RequestParam(value = "token", required = false) String token, @RequestParam(value = "keyword") String keyword){
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            return "token错误";
        }
        if (keyword != null){
            return contentService.selectByKeyword(keyword).toString();
        }
        return "无关键词";
    }
}

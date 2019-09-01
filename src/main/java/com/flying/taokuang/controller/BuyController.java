package com.flying.taokuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.dataobject.Content;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.CollectionService;
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
 * @date 2019/8/30 21:13
 */
@RestController
@RequestMapping("/user")
public class BuyController {
    private static final String encry = "salt";

    @Autowired
    private ContentService contentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CollectionService collectionService;

    /**
     * 购买
     * @param token
     * @param contentId 商品id
     * @return
     */
    @RequestMapping(value = "/buy", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String buy(@RequestParam(value = "token", required = false) String token,
                      @RequestParam(value = "contentId") int contentId){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }

        Content content = contentService.selectById(contentId);
        User user = userService.selectById((int) JwtUtil.getClamis(token, encry).get("userId"));
        if (contentService.buy(content, user.getUsername()) == 1){
            //购买成功后删除收藏
            collectionService.deleteByCollectionId(contentId);
            result.put("msg", "购买成功");
            result.put("success", true);
            return result.toJSONString();
        }
        result.put("msg", "购买失败");
        result.put("success", false);
        return result.toJSONString();
    }

    /**
     * 鸽子
     * @param token
     * @param contentId 商品id
     * @return
     */
    @RequestMapping(value = "/gezi", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String gezi(@RequestParam(value = "token", required = false) String token,
                       @RequestParam(value = "contentId") int contentId){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }

        Content content = contentService.selectById(contentId);
        if (!StringUtils.isBlank(content.getBuyer()) && content.getBuy() == 1){
            content.setGezi(1);
            contentService.update(content);
            result.put("msg", "鸽了");
            result.put("success", true);
            return result.toJSONString();
        }
        result.put("msg", "鸽失败了");
        result.put("success", false);
        return result.toJSONString();
    }
}

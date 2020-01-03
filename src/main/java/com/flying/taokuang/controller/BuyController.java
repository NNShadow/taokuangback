package com.flying.taokuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.dataobject.Content;
import com.flying.taokuang.dataobject.Result;
import com.flying.taokuang.dataobject.User;
import com.flying.taokuang.service.CollectionService;
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

    @Autowired
    private ResponseData responseData;

    /**
     * 购买
     *
     * @param token
     * @param contentId 商品id
     * @return
     */
    @RequestMapping(value = "/buy", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result buy(@RequestParam(value = "token", required = false) String token,
                      @RequestParam(value = "contentId") int contentId) {
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }

        Content content = contentService.selectByContentId(contentId);
        User user = userService.selectByUserId((int) JwtUtil.getClamis(token, encry).get("userId"));
        if (contentService.buy(content, user.getUserId()) == 1) {
            //购买成功后删除收藏
            collectionService.deleteByCollectionId(contentId);
            return responseData.write("购买成功", 200, new HashMap<>());
        }
        return responseData.write("购买成功", 404, new HashMap<>());
    }

    /**
     * 鸽子
     *
     * @param token
     * @param contentId 商品id
     * @return
     */
    @RequestMapping(value = "/gezi", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result gezi(@RequestParam(value = "token", required = false) String token,
                       @RequestParam(value = "contentId") int contentId) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }

        Content content = contentService.selectByContentId(contentId);
        if (content.getBuyerId() != 0 && content.getBuy() == 1) {
            content.setBird(1);
            contentService.update(content);
            return responseData.write("鸽子", 200, new HashMap<>());
        }
        return responseData.write("鸽失败了", 404, new HashMap<>());
    }
}

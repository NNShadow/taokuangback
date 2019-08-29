package com.flying.taokuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.dataobject.Collection;
import com.flying.taokuang.service.CollectionService;
import com.flying.taokuang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author NNShadow
 * @date 2019/8/28 16:49
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {
    private final static String encry = "salt";

    @Autowired
    private CollectionService collectionService;

    /**
     * 查看收藏
     * @param token
     * @return
     */
    @RequestMapping("/select")
    public String select(@RequestParam(value = "token",required = false) String token){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }
        int collectorId = (int) JwtUtil.getClamis(token, encry).get("id");
        List<Collection> collectionList = collectionService.selectByCollectorId(collectorId);
        result.put("collectionList", collectionList);
        result.put("msg", "token错误");
        result.put("success", true);
        return result.toJSONString();
    }

    /**
     * 收藏
     * @param token
     * @param collectionId
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestParam(value = "token",required = false) String token, int collectionId){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }

        if (collectionId != 0){
            Collection collection = new Collection();
            //获取评论者id
            int userId = (int) JwtUtil.getClamis(token, encry).get("id");
            collection.setCollectionId(collectionId);
            collection.setCollectorId(userId);
            collection.setCreatedDate(new Date());
            collection.setUpdateDate(new Date());
            collectionService.insert(collection);
            result.put("msg", "收集成功");
            result.put("success", true);
            return result.toJSONString();
        }
        result.put("msg", "收集失败");
        result.put("success", false);
        return result.toJSONString();
    }

    /**
     * 取消收藏
     * @param token
     * @param collectionId
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "token",required = false) String token, int collectionId){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }

        if (collectionId != 0){
            //获取评论者id，修改评论者
            int userId = (int) JwtUtil.getClamis(token, encry).get("id");
            collectionService.deleteByUserIdAndCollectionId(userId, collectionId);
            result.put("msg", "取消收藏成功");
            result.put("success", true);
            return result.toJSONString();
        }
        result.put("msg", "取消收藏失败");
        result.put("success", false);
        return result.toJSONString();
    }
}

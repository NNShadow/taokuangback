package com.flying.taokuang.controller;

import com.flying.taokuang.dataobject.Collection;
import com.flying.taokuang.dataobject.Result;
import com.flying.taokuang.service.CollectionService;
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

    @Autowired
    private ResponseData responseData;

    /**
     * 查看收藏
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result select(@RequestParam(value = "token", required = false) String token) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }
        int collectorId = (int) JwtUtil.getClamis(token, encry).get("userId");
        List<Collection> collectionList = collectionService.selectByCollectorId(collectorId);
        return responseData.write("token错误", 200, collectionList);
    }

    /**
     * 收藏
     *
     * @param token
     * @param collectionId 收藏品id（物品id）
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result add(@RequestParam(value = "token", required = false) String token,
                      @RequestParam(value = "collectionId") int collectionId) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }

        if (collectionId >= 0) {
            Collection collection = new Collection();
            //获取评论者id
            int userId = (int) JwtUtil.getClamis(token, encry).get("userId");
            collection.setCollectionId(collectionId);
            collection.setCollectorId(userId);
            collection.setCreatedDate(new Date());
            collection.setUpdateDate(new Date());
            if (collectionService.insert(collection) == 1) {
                return responseData.write("收集成功", 200, new HashMap<>());
            }
        }
        return responseData.write("收集失败", 404, new HashMap<>());
    }

    /**
     * 取消收藏
     *
     * @param token
     * @param collectionId 收藏品id（物品id）
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Result delete(@RequestParam(value = "token", required = false) String token,
                         @RequestParam(value = "collectionId") int collectionId) {
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)) {
            return responseData.write("token错误", 404, new HashMap<>());
        }

        if (collectionId >= 0) {
            //获取评论者id，修改评论者
            int userId = (int) JwtUtil.getClamis(token, encry).get("userId");
            collectionService.deleteByUserIdAndCollectionId(userId, collectionId);
            return responseData.write("取消收藏成功", 200, new HashMap<>());
        }
        return responseData.write("取消收藏失败", 404, new HashMap<>());
    }
}

package com.flying.taokuang.controller;

import com.alibaba.fastjson.JSONObject;
import com.flying.taokuang.dataobject.Content;
import com.flying.taokuang.service.ContentService;
import com.flying.taokuang.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    /**
     * 添加商品，未加判空
     * @param content
     * @param token
     * @return
     */
    @RequestMapping("/add")
    public String add(Content content, @RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }
        if (content != null){
            content.setCreatedDate(new Date());
            content.setUpdatedDate(new Date());
            if (contentService.insert(content) != 0){
                result.put("msg", "添加成功");
                result.put("success", true);
                return result.toJSONString();
            }
        }
        result.put("msg", "添加失败");
        result.put("success", false);
        return result.toJSONString();
    }

    /**
     * 删除商品，未加判空，判断所属
     * @param contentId
     * @param token
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "contentId") int contentId, @RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }
        if (contentId != 0){
            if (contentService.deleteById(contentId) != 0){
                result.put("msg", "删除成功");
                result.put("success", true);
                return result.toJSONString();
            }
        }
        result.put("msg", "删除失败");
        result.put("success", false);
        return result.toJSONString();
    }

    /**
     * 修改商品，未加判空
     * @param content
     * @param token
     * @return
     */
    @RequestMapping("/modify")
    public String modify(Content content, @RequestParam(value = "token", required = false) String token){
        JSONObject result = new JSONObject();
        //验证token
        if (StringUtils.isBlank(token) || !JwtUtil.isExpiration(token, encry)){
            result.put("msg", "token错误");
            result.put("success", false);
            return result.toJSONString();
        }
        if (content != null){
            content.setUpdatedDate(new Date());
            if (contentService.update(content) != 0){
                result.put("msg", "修改成功");
                result.put("success", true);
                return result.toJSONString();
            }
        }
        result.put("msg", "修改失败");
        result.put("success", false);
        return result.toJSONString();
    }

}

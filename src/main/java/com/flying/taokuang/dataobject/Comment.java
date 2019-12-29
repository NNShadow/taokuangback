package com.flying.taokuang.dataobject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2019/08/24
 */
public class Comment implements Common {
    private Integer id;

    /**
     * 评论的物品的id
     */
    private int contentGoodsId;

    /**
     * 评论的物品的名字
     */
    private String contentGoodsName;

    /**
     * 评论人的id
     */
    private int contentCommenterId;

    /**
     * 评论人的名字
     */
    private String contentCommenterName;

    /**
     * 评论内容
     */
    private String content;

    private Date createdDate;

    private Date updatedDate;

    public String getContentGoodsName() {
        return contentGoodsName;
    }

    public void setContentGoodsName(String contentGoodsName) {
        this.contentGoodsName = contentGoodsName;
    }

    public int getContentCommenterId() {
        return contentCommenterId;
    }

    public void setContentCommenterId(int contentCommenterId) {
        this.contentCommenterId = contentCommenterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentCommenterName() {
        return contentCommenterName;
    }

    public void setContentCommenterName(String contentCommenterName) {
        this.contentCommenterName = contentCommenterName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getContentGoodsId() {
        return contentGoodsId;
    }

    public void setContentGoodsId(int contentGoodsId) {
        this.contentGoodsId = contentGoodsId;
    }

    @Override
    public Map toDict() {
        Map<Object, Object> map = new HashMap<>();
        map.put("contentGoodsId", contentGoodsId);
        map.put("contentGoodsName", contentGoodsName);
        map.put("contentCommenterId", contentCommenterId);
        map.put("contentCommenterName", contentCommenterName);
        map.put("content", content);
        return map;
    }
}
package com.flying.taokuang.dataobject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2019/08/24
 */
public class Comment implements Common {
    private Integer commentId;

    /**
     * 评论的物品的id
     */
    private int contentId;

    /**
     * 评论的物品的名字
     */
    private String contentName;

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
    private String text;

    private Date createdDate;

    private Date updatedDate;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public int getContentCommenterId() {
        return contentCommenterId;
    }

    public void setContentCommenterId(int contentCommenterId) {
        this.contentCommenterId = contentCommenterId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContentCommenterName() {
        return contentCommenterName;
    }

    public void setContentCommenterName(String contentCommenterName) {
        this.contentCommenterName = contentCommenterName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Override
    public Map toDict() {
        Map<Object, Object> map = new HashMap<>();
        map.put("contentGoodsId", contentId);
        map.put("contentGoodsName", contentName);
        map.put("contentCommenterId", contentCommenterId);
        map.put("contentCommenterName", contentCommenterName);
        map.put("content", text);
        return map;
    }
}
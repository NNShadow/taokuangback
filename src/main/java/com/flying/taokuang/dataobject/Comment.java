package com.flying.taokuang.dataobject;

import java.util.Date;

/**
 * 
 * @date 2019/08/24
 */
public class Comment {
    private Integer id;
    /**
     * 评论的物品的id
     */
    private int contentGoodsId;

    /**
     * 评论人
     */
    private String contentCommenter;

    /**
     * 评论内容
     */
    private String content;

    private Date createdDate;

    private Date updatedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentCommenter() {
        return contentCommenter;
    }

    public void setContentCommenter(String contentCommenter) {
        this.contentCommenter = contentCommenter;
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
}
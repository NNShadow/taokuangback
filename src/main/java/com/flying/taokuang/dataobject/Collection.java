package com.flying.taokuang.dataobject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2019/08/24
 */
public class Collection implements Common {
    private Integer id;

    /**
     * 收藏者id
     */
    private int collectorId;

    /**
     * 收藏者的名字
     */
    private String collectorName;

    /**
     * 被收藏的物品
     */
    private int collectionId;

    /**
     * 被收藏的物品的名字
     */
    private String collectionName;

    /**
     * 被收藏数量（暂时不用）
     */
    private Integer collectionNum;

    /**
     * 创建日期
     */
    private Date createdDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public Map toDict() {
        Map<Object, Object> map = new HashMap<>();
        map.put("collectorId", collectorId);
        map.put("collectorName", collectorName);
        map.put("collectionId", collectionId);
        map.put("collectionName", collectionName);
        return map;
    }
}
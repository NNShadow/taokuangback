package com.flying.taokuang.dataobject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2019/08/24
 */
public class Content implements Common {
    private Integer id;

    /**
     * 用户的id
     */
    private int userId;

    /**
     * 用户的姓名
     */
    private String userName;

    /**
     * 商品照片
     */
    private String goodsPic;

    /**
     * 鸽子
     */
    private Integer bird;

    /**
     * 买家的id
     */
    private int buyerId;

    /**
     * 买家的姓名
     */
    private String buyerName;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 联系
     */
    private String contact;

    /**
     * 类型
     */
    private String type;

    /**
     * 金额
     */
    private String money;

    /**
     * 是否交易
     */
    private Integer business;

    /**
     * 购买
     */
    private Integer buy;

    /**
     * 位置
     */
    private String place;

    /**
     * 描述
     */
    private String context;

    private Date createdDate;

    private Date updatedDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public Integer getBird() {
        return bird;
    }

    public void setBird(Integer bird) {
        this.bird = bird;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getBuy() {
        return buy;
    }

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    @Override
    public Map toDict() {
        Map<Object, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("goodsPic", goodsPic);
        map.put("bird", bird);
        map.put("buyerId", buyerId);
        map.put("buyerName", buyerName);
        map.put("title", title);
        map.put("contact", contact);
        map.put("type", type);
        map.put("money", money);
        map.put("business", business);
        map.put("buy", buy);
        map.put("place", place);
        map.put("context", context);
        return map;
    }
}
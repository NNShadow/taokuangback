package com.flying.taokuang.dataobject;

import java.util.Date;

/**
 * 
 * @date 2019/08/24
 */
public class Content {
    private Integer id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 商品照片
     */
    private String goodsPic;

    /**
     * 鸽子
     */
    private Integer gezi;

    /**
     * 买家
     */
    private String buyer;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public Integer getGezi() {
        return gezi;
    }

    public void setGezi(Integer gezi) {
        this.gezi = gezi;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
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
}
package com.guozhengood.user.domain;

import com.guozhengood.user.util.StringUtil;

import java.util.Date;

public class ProductCategory extends BaseModel{
    private Integer id;

    private String categoryName;

    private String categoryImg;

    private Date createTime;

    private Date updateTime;

    private Integer orderNum;

    public ProductCategory(Integer id, String categoryName, String categoryImg, Date createTime, Date updateTime, Integer orderNum) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImg = categoryImg;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.orderNum = orderNum;
    }

    public ProductCategory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg == null ? null : categoryImg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public boolean check() {
        if(StringUtil.isNotEmpty(categoryName)){
            return true;
        }
        return false;
    }
}
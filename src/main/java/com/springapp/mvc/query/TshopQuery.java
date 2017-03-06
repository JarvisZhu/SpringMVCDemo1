package com.springapp.mvc.query;

import javacommon.base.BaseQuery;

/**
 * Created by Administrator on 2015/9/20.
 */
public class TshopQuery extends BaseQuery {
    private Integer shopid;
    private String shopname;
    private Integer userid;

    private String username;
    private Integer usercount;

    public TshopQuery() {

    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }
}

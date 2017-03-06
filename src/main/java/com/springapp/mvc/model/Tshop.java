package com.springapp.mvc.model;

import javacommon.base.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_shop")
public class Tshop extends BaseEntity implements Serializable {

    private Integer shopid;
    @NotBlank
    @Length(max = 255)
    private String shopname;

    private Integer userid;

    public Tshop() {
    }

    public Tshop(Integer shopid) {
        this.shopid = shopid;
    }

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "identity")
    @Column(name = "SHOPID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    @Column(name = "SHOPNAME", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @Column(name = "USERID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    private Tuser tuser;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "USERID",nullable = false, insertable = false, updatable = false)
    })
    public Tuser getTuser() {
        return tuser;
    }

    public void setTuser(Tuser tuser) {
        this.tuser = tuser;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getShopid())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Tshop == false) return false;
        if (this == obj) return true;
        Tshop other = (Tshop) obj;
        return new EqualsBuilder()
                .append(getShopid(), other.getShopid())
                .isEquals();
    }
}

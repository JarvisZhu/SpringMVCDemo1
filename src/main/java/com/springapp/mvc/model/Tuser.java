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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class Tuser extends BaseEntity implements Serializable {

    public static final String FORMAT_CREATETIME = DATE_TIME_FORMAT;

    private java.lang.Integer userid;
    @NotBlank
    @Length(max = 16)
    private java.lang.String username;

    private Integer usercount;

    private Date createtime;

    @Length(max=65535)
    private String content;

    public Tuser() {
    }

    public Tuser(java.lang.Integer userid) {
        this.userid = userid;
    }


    public void setUserid(java.lang.Integer value) {
        this.userid = value;
    }

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "identity")
    @Column(name = "USERID", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public java.lang.Integer getUserid() {
        return this.userid;
    }

    @Column(name = "USERNAME", unique = false, nullable = false, insertable = true, updatable = true, length = 16)
    public java.lang.String getUsername() {
        return this.username;
    }

    public void setUsername(java.lang.String value) {
        this.username = value;
    }

    @Column(name = "USERCOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

    @Transient
    public String getCreatetimeString() {
        return format(getCreatetime(), FORMAT_CREATETIME);
    }
    public void setCreatetimeString(String value) {
        setCreatetime(parse(value, FORMAT_CREATETIME));
    }

    @Column(name = "CREATETIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }

    @Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
    public java.lang.String getContent() {
        return this.content;
    }

    public void setContent(java.lang.String value) {
        this.content = value;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString();
    }


    /**
     * 下面这种一对多的关系很少加，要使用一对多，就必须先在Tshop里配置多对一，mappedBy的值就是Tshop中的tuser字段;
     */
    private Set<Tshop> tshopset = new HashSet<>();

    @OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "tuser")
    public Set<Tshop> getTshopset() {
        return tshopset;
    }

    public void setTshopset(Set<Tshop> tshopset) {
        this.tshopset = tshopset;
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getUserid())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Tuser == false) return false;
        if (this == obj) return true;
        Tuser other = (Tuser) obj;
        return new EqualsBuilder()
                .append(getUserid(), other.getUserid())
                .isEquals();
    }
}

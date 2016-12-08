package com.mxep.model.common;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1200613867608461346L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private char firstLetter;

    @Column(name = "pid")
    private Integer pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid",insertable = false,updatable = false)
    private Province province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}

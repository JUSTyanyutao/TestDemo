package com.mxep.model.log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
@Entity
public class SmsTemplate implements Serializable {

    private static final long serialVersionUID = -4354340935148130056L;

    private Integer id;

    /**
     *  模版代码
     */
    private String code;

    /**
     *  内容
     */
    private String content;

    /**
     *  描述
     */
    private String description;

    /**
     *  创建时间
     */
    private Date createTime;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

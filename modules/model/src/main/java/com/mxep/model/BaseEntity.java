package com.mxep.model;

import com.mxep.model.enums.EnumFlag;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity - base
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -8532794912326373561L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识位
     *
     * @see com.mxep.model.enums.EnumFlag
     */
    private byte flag = EnumFlag.Normal.value;

    /**
     * 获取编号
     *
     * @return 编号
     */
    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取标识位
     *
     * @see com.mxep.model.enums.EnumFlag
     * @return 标识位
     */
    public byte getFlag() {
        return flag;
    }

    /**
     * 设置标识位
     *
     * @see com.mxep.model.enums.EnumFlag
     * @param flag 标识位
     */
    public void setFlag(byte flag) {
        this.flag = flag;
    }
}

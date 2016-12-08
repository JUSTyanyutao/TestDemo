package com.mxep.model.goods;

import com.mxep.model.base.Label;
import com.mxep.model.enums.EnumFlag;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
@Entity
@Where(clause = "flag = 1")
public class CarModel implements Serializable{

    private static final long serialVersionUID = 1200613867608461346L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "series_id")
    private Integer seriesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private CarSeries carSeries;

    private String name;

    /**
     *  尺寸
     */
    private String carSize;

    private byte status;

    private byte flag = EnumFlag.Normal.value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public CarSeries getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(CarSeries carSeries) {
        this.carSeries = carSeries;
    }

    /**
     *  尺寸
     * @return
     */
    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }
}

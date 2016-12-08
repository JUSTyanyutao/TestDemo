package com.mxep.model.goods;

import com.mxep.model.enums.EnumFlag;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
@Entity
@Where(clause = "flag = 1")
public class CarBrand implements Serializable {

    private static final long serialVersionUID = 1200613867608461346L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String logoUrl;

    private char firstChar;

    private byte status;

    private byte flag = EnumFlag.Normal.value;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "carBrand")
    private List<CarSeries> carSeriesList;

    public List<CarSeries> getCarSeriesList() {
        return carSeriesList;
    }

    public void setCarSeriesList(List<CarSeries> carSeriesList) {
        this.carSeriesList = carSeriesList;
    }

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public char getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(char firstChar) {
        this.firstChar = firstChar;
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

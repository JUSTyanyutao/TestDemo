package com.mxep.model.goods;

import com.mxep.model.enums.EnumFlag;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
@Entity
@Where(clause = "flag = 1")
public class CarSeries implements Serializable{

    private static final long serialVersionUID = 1200613867608461346L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "brand_id")
    private Integer brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private CarBrand carBrand;

    private String name;

    private byte status;

    private byte flag =  EnumFlag.Normal.value;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "carSeries")
    private List<CarModel> carModelList;

    public List<CarModel> getCarModelList() {
        return carModelList;
    }

    public void setCarModelList(List<CarModel> carModelList) {
        this.carModelList = carModelList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
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

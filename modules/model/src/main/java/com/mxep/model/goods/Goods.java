package com.mxep.model.goods;

import com.mxep.model.BaseSortedEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity - 商品
 *
 * @author xingkong1221
 * @since 2015-11-16
 */
@Entity
@Table
public class Goods extends BaseSortedEntity {

    private static final long serialVersionUID = 1200613867608461346L;

    /**
     * 名称
     */
    private String name;

    /**
     *  置顶图片
     */
    private String recommendPic;

    /**
     * 缩略图（列表图片）
     */
    private String thumb;

    /**
     * 短介绍
     */
    private String shortIntro;

    /**
     *  价格
     */
    private BigDecimal price = BigDecimal.ZERO;

    /**
     *  原先价格
     */
    private BigDecimal originalPrice = BigDecimal.ZERO;

    /**
     *   品牌
     */
    private Integer filmBrandId;

    /**
     *   品牌
     */
    private  FilmBrand filmBrand;

    /**
     * 分类编号
     */
    private Integer categoryId;

    /**
     * 分类
     */
    private Category category;

    /**
     * 展示图片（多张图片用“,”分隔）
     */
    private String pictures;

    /**
     * 商品 上架 下架 状态
     */
    private int status;

    /**
     *  商品 类型
     */
    private String attrType;

    /**
     *  商品 特性
     */
    private String attrFeatures;

    /**
     *  汽车部位
     */
    private String carParts;

    /**
     *  置顶   1推荐  0普通
     */
    private byte recommendFlag;

    /**
     *  类型  1商品  2服务 3 美容洗车
     */
    private byte type;

    /**
     *    适用用户类型 1个人 2企业
     */
    private byte userType;

    /**
     *  汽车 尺寸
     */
    private String carSize;

    /**
     *  商品 详情
     */
    private GoodsInfo goodsInfo;

    /**
     *  服务
     */
    private List<Package> packageList;

    /**
     * 实例化一个商品
     */
    public Goods() {
    }


    public String getRecommendPic() {
        return recommendPic;
    }

    public void setRecommendPic(String recommendPic) {
        this.recommendPic = recommendPic;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "dm_goods_package" ,joinColumns = {@JoinColumn( name = "goods_id")}, inverseJoinColumns = {@JoinColumn(name = "package_id")})
    @NotFound(action = NotFoundAction.IGNORE)
    public List<Package> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<Package> packageList) {
        this.packageList = packageList;
    }

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "goods")
    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Column(name = "film_brand_id")
    public Integer getFilmBrandId() {
        return filmBrandId;
    }

    public void setFilmBrandId(Integer filmBrandId) {
        this.filmBrandId = filmBrandId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_brand_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public FilmBrand getFilmBrand() {
        return filmBrand;
    }

    public void setFilmBrand(FilmBrand filmBrand) {
        this.filmBrand = filmBrand;
    }

    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrFeatures() {
        return attrFeatures;
    }

    public void setAttrFeatures(String attrFeatures) {
        this.attrFeatures = attrFeatures;
    }

    public String getCarParts() {
        return carParts;
    }

    public void setCarParts(String carParts) {
        this.carParts = carParts;
    }

    public byte getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(byte recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getUserType() {
        return userType;
    }

    public void setUserType(byte userType) {
        this.userType = userType;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }
}

package com.mxep.model.goods;

import com.mxep.model.BaseSortedEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/10/9 0009.
 *
 * 品牌表
 */
@Entity
@Where(clause = "flag=1")
public class FilmBrand extends BaseSortedEntity{

    private static final long serialVersionUID = 1200613867608461346L;

    /**
     *  名称
     */
    private String name;

    /**
     *  状态 1 上架   0下架
     */
    private byte status;

    /**
     *   图片
     */
    private String pic;

    /**
     *  推荐 置顶   1置顶  0普通
     */
    private byte recommendFlag;

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public byte getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(byte recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

}

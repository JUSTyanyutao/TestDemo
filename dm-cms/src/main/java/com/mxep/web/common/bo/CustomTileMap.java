package com.mxep.web.common.bo;

import com.mxep.core.utils.gis.MapUtils;

import java.io.File;
import java.io.Serializable;

/**
 * Created by ranfi on 10/21/15.
 */
public class CustomTileMap implements Serializable {

    private double ltlng;        //左上角经度
    private double ltlat;        //左上角纬度
    private double rblng;        //右下角经度
    private double rblat;        //右下角纬度
    private int initLevel = 18;  //初始地图等级,后续的参数运算都基于该参数
    private int width;           //手绘地图宽度
    private int height;          //手绘地图高度
    private int maxLevel;         //根据手绘地图的分辨率，计算的地图最大等级


    public CustomTileMap(double ltlng, double ltlat, double rblng, double rblat, int initLevel) {
        this.ltlng = ltlng;
        this.ltlat = ltlat;
        this.rblng = rblng;
        this.rblat = rblat;
        this.initLevel = initLevel;
    }

    public CustomTileMap(double ltlng, double ltlat, double rblng, double rblat, int initLevel, int width, int height) {
        this.ltlng = ltlng;
        this.ltlat = ltlat;
        this.rblng = rblng;
        this.rblat = rblat;
        this.initLevel = initLevel;
        this.width = width;
        this.height = height;
    }

    public void setLtlng(double ltlng) {
        this.ltlng = ltlng;
    }

    public void setLtlat(double ltlat) {
        this.ltlat = ltlat;
    }

    public void setRblng(double rblng) {
        this.rblng = rblng;
    }

    public void setRblat(double rblat) {
        this.rblat = rblat;
    }

    public void setInitLevel(int initLevel) {
        this.initLevel = initLevel;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLtlng() {
        return ltlng;
    }

    public double getLtlat() {
        return ltlat;
    }

    public double getRblng() {
        return rblng;
    }

    public double getRblat() {
        return rblat;
    }

    public int getInitLevel() {
        return initLevel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * 实际左上角像素x坐标
     *
     * @return
     */
    public double getLtPixelX() {
        return MapUtils.lngToPixel(ltlng, initLevel);
    }

    /**
     * 实际左上角像素y坐标
     *
     * @return
     */
    public double getLtPixelY() {
        return MapUtils.latToPixel(ltlat, initLevel);
    }

    /**
     * 实际右下角像素x坐标
     *
     * @return
     */
    public double getRbPixelX() {
        return MapUtils.lngToPixel(rblng, initLevel);
    }

    /**
     * 实际右下角像素y坐标
     *
     * @return
     */
    public double getRbPixelY() {
        return MapUtils.latToPixel(rblat, initLevel);
    }

    /**
     * 实际左上角角瓦片列号
     *
     * @return
     */
    public int getLtTileX() {
        return MapUtils.getTileX(ltlng, initLevel);
    }

    /**
     * 实际左上角角瓦片行号
     *
     * @return
     */
    public int getLtTileY() {
        return MapUtils.getTileY(ltlat, initLevel);
    }

    /**
     * 实际右下角瓦片列号
     *
     * @return
     */
    public int getRbTileX() {
        return MapUtils.getTileX(rblng, initLevel);
    }

    /**
     * 实际右下角瓦片行号
     *
     * @return
     */
    public int getRbTileY() {
        return MapUtils.getTileY(rblat, initLevel);
    }

    /**
     * 初始等级地图实际宽度
     *
     * @return
     */
    public int getRealMapWidth() {
        return (int) Math.ceil(getRbPixelX() - getLtPixelX());
    }

    /**
     * 初始等级地图实际高度
     *
     * @return
     */
    public int getRealMapHeight() {
        return (int) Math.ceil(getRbPixelY() - getLtPixelY());
    }

    /**
     * 地图包含完整瓦片的宽度
     *
     * @return
     */
    public int getRepairMapWidth() {
        return (getRbTileX() - getLtTileX() + 1) * 256;
    }

    /**
     * 地图包含完整瓦片的高度
     *
     * @return
     */
    public int getRepairMapHeight() {
        return (getRbTileY() - getLtTileY() + 1) * 256;
    }

    /**
     * 地图放大倍数
     *
     * @return
     */
    public int getZoomMultiple() {
        double r = Math.round((double) width / getRealMapWidth());
        return (int) Math.pow(2, (int) Math.floor(r) / 2);
    }

    /**
     * 根据手绘地图的分辨率，计算的地图最大等级
     *
     * @return
     */
    public int getMaxLevel() {
        return initLevel + getZoomMultiple() / 2;
    }

    /**
     * 实际左上角坐标和最小瓦片左上角的偏移x像素
     *
     * @return
     */
    public int getLtOffsetX() {
        return (int) Math.ceil(getLtPixelX() - getLtTileX() * 256);
    }

    /**
     * 实际左上角坐标和最小瓦片左上角的偏移x像素
     *
     * @return
     */
    public int getLtOffsetY() {
        return (int) Math.ceil(getLtPixelY() - getLtTileY() * 256);
    }


    public static void main(String[] args) {
        System.out.println("取整" + Math.pow(2, (int) Math.floor(Math.round((double) 3590 / 1020)) / 2));

        System.out.println(new File("/Users/ranfi/Desktop/纳米科技园.png").getParent());
    }
}

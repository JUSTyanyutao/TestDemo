package com.mxep.web.common.bo;

import java.util.List;

/**
 * select2插件构造实体
 * <p>
 * Created by ranfi on 4/19/16.
 */
public class SelectOption {


    private Integer id;

    private String text;

    private List<SelectOption> children;

    private String img;    //选项前展示图片

    private boolean disabled = false;   //是否可用


    public SelectOption(Integer id, String text) {
        this.id = id;
        this.text = text;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SelectOption> getChildren() {
        return children;
    }

    public void setChildren(List<SelectOption> children) {
        this.children = children;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}

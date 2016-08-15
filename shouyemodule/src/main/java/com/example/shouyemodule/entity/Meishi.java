package com.example.shouyemodule.entity;


import java.util.List;

/**
 * Created by WTW on 2016/8/12.
 */

public class Meishi {

    private String parent_cate_name;
    private String parent_cate_id;
    private String parent_cate_img_url;
    private List<MeishiText> childCategoryList;

    public String getParent_cate_img_url() {
        return parent_cate_img_url;
    }

    public void setParent_cate_img_url(String parent_cate_img_url) {
        this.parent_cate_img_url = parent_cate_img_url;
    }

    public String getParent_cate_name() {
        return parent_cate_name;
    }

    public void setParent_cate_name(String parent_cate_name) {
        this.parent_cate_name = parent_cate_name;
    }

    public String getParent_cate_id() {
        return parent_cate_id;
    }

    public void setParent_cate_id(String parent_cate_id) {
        this.parent_cate_id = parent_cate_id;
    }

    public List<MeishiText> getChildCategoryList() {
        return childCategoryList;
    }

    public void setChildCategoryList(List<MeishiText> childCategoryList) {
        this.childCategoryList = childCategoryList;
    }

    @Override
    public String toString() {
        return "Meishi{" +
                "parent_cate_name='" + parent_cate_name + '\'' +
                ", parent_cate_id='" + parent_cate_id + '\'' +
                ", parent_cate_img_url='" + parent_cate_img_url + '\'' +
                ", childCategoryList=" + childCategoryList +
                '}';
    }
}

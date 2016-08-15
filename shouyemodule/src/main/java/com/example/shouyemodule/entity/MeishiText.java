package com.example.shouyemodule.entity;



public class MeishiText {
    private String child_category_id;
    private String child_category_name;
    private String parent_cate_id;

    public String getChild_category_id() {
        return child_category_id;
    }

    public void setChild_category_id(String child_category_id) {
        this.child_category_id = child_category_id;
    }

    public String getChild_category_name() {
        return child_category_name;
    }

    public void setChild_category_name(String child_category_name) {
        this.child_category_name = child_category_name;
    }

    public String getParent_cate_id() {
        return parent_cate_id;
    }

    public void setParent_cate_id(String parent_cate_id) {
        this.parent_cate_id = parent_cate_id;
    }

    @Override
    public String toString() {
        return "MeishiText{" +
                "child_category_id='" + child_category_id + '\'' +
                ", child_category_name='" + child_category_name + '\'' +
                ", parent_cate_id='" + parent_cate_id + '\'' +
                '}';
    }
}

package com.example.shouyemodule.entity;



public class ShopImage  {
    private String detail_id;
    private String img_id;
    private String img_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    @Override
    public String toString() {
        return "ShopImageData{" +
                "detail_id='" + detail_id + '\'' +
                ", img_id='" + img_id + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}

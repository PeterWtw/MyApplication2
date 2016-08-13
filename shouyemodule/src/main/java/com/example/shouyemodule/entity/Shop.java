package com.example.shouyemodule.entity;


import java.util.List;

/**
 * Created by WTW on 2016/8/12.
 */

public class Shop  {

    private String merchant_name;
    private String per_capita_consumption;
    private String measure_unit;
    private String business_location;
    private String closing_time;
    private String opening_time;
    private List<ShopImage> imgUrlList;
    private String img_url;


    public String getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }


    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getPer_capita_consumption() {
        return per_capita_consumption;
    }

    public void setPer_capita_consumption(String per_capita_consumption) {
        this.per_capita_consumption = per_capita_consumption;
    }

    public String getMeasure_unit() {
        return measure_unit;
    }

    public void setMeasure_unit(String measure_unit) {
        this.measure_unit = measure_unit;
    }

    public String getBusiness_location() {
        return business_location;
    }

    public void setBusiness_location(String business_location) {
        this.business_location = business_location;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    public List<ShopImage> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<ShopImage> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }




    @Override
    public String toString() {
        return "Shop{" +
                "merchant_name='" + merchant_name + '\'' +
                ", per_capita_consumption='" + per_capita_consumption + '\'' +
                ", measure_unit='" + measure_unit + '\'' +
                ", business_location='" + business_location + '\'' +
                ", opening_time='" + opening_time + '\'' +
                ", imgUrlList=" + imgUrlList +
                '}';
    }
}

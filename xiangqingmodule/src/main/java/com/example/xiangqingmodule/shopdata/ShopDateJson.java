package com.example.xiangqingmodule.shopdata;

import java.util.List;
import java.util.Objects;

/**
 * Created by wihltmnet on 2016/8/10.
 */

public class ShopDateJson {
    private String business_location;
    private String child_category_id;
    private String city_id;
    private String detail_id;
    private String closing_time;
    private String contact;
    private String detail_info;

    private String isApprove;
    private String is_take_out;
    private String measure_unit;
    private String merchant_name;
    private String opening_time;
    private String parent_category_id;
    private String per_capita_consumption;
    private String phone;
    private String user_id;
    private List<ShopImageData> imgUrlList;

    public List<ShopImageData> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<ShopImageData> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPer_capita_consumption() {
        return per_capita_consumption;
    }

    public void setPer_capita_consumption(String per_capita_consumption) {
        this.per_capita_consumption = per_capita_consumption;
    }

    public String getParent_category_id() {
        return parent_category_id;
    }

    public void setParent_category_id(String parent_category_id) {
        this.parent_category_id = parent_category_id;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getMeasure_unit() {
        return measure_unit;
    }

    public void setMeasure_unit(String measure_unit) {
        this.measure_unit = measure_unit;
    }

    public String getIs_take_out() {
        return is_take_out;
    }

    public void setIs_take_out(String is_take_out) {
        this.is_take_out = is_take_out;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getDetail_info() {
        return detail_info;
    }

    public void setDetail_info(String detail_info) {
        this.detail_info = detail_info;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getChild_category_id() {
        return child_category_id;
    }

    public void setChild_category_id(String child_category_id) {
        this.child_category_id = child_category_id;
    }

    public String getBusiness_location() {
        return business_location;
    }

    public void setBusiness_location(String business_location) {
        this.business_location = business_location;
    }

    @Override
    public String toString() {
        return "ShopDateJson{" +
                "business_location='" + business_location + '\'' +
                ", child_category_id='" + child_category_id + '\'' +
                ", city_id='" + city_id + '\'' +
                ", detail_id='" + detail_id + '\'' +
                ", closing_time='" + closing_time + '\'' +
                ", contact='" + contact + '\'' +
                ", detail_info='" + detail_info + '\'' +
                ", isApprove='" + isApprove + '\'' +
                ", is_take_out='" + is_take_out + '\'' +
                ", measure_unit='" + measure_unit + '\'' +
                ", merchant_name='" + merchant_name + '\'' +
                ", opening_time='" + opening_time + '\'' +
                ", parent_category_id='" + parent_category_id + '\'' +
                ", per_capita_consumption='" + per_capita_consumption + '\'' +
                ", phone='" + phone + '\'' +
                ", user_id='" + user_id + '\'' +
                ", imgUrlList=" + imgUrlList +
                '}';
    }
}

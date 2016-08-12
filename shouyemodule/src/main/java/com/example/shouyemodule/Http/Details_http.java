package com.example.shouyemodule.Http;



public class Details_http {
    //全局接口
    public static final String HTTP_overallurl="http://123.206.87.139/LoveHomeTownServer/";
    //获取城市信息
    public static final String HTTP_city="http://123.206.87.139/LoveHomeTownServer/printCity";
    //获取父分类信息
    public static final String HTTP_date="http://123.206.87.139/LoveHomeTownServer/printCategory";
    //查询发布信息
    public static final String HTTP_detailInfo="http://123.206.87.139/LoveHomeTownServer/detailInfo";
    //请求参数
    //未审核  0   已审核  1   （默认为0）
    public static final String HTTP_audting="http://123.206.87.139/LoveHomeTownServer/detailInfo?is_approve=";
}

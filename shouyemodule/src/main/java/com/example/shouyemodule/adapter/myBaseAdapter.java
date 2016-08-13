package com.example.shouyemodule.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shouyemodule.R;
import com.example.shouyemodule.entity.Shop;
import com.example.shouyemodule.entity.ShopImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WTW on 2016/8/12.
 */

public class myBaseAdapter extends BaseAdapter {
    private TextView merchant_name, per_capita_consumption, business_location,
            measure_unit, time;
    private List<ShopImage> imgUrlList;
    ArrayList<Shop> data;
    private ImageView shou_list_img;
    Context context;

    public myBaseAdapter( Context context,ArrayList<Shop> data){
        this.data=data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size()-1;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View cview, ViewGroup viewGroup) {

        cview = LayoutInflater.from(context).inflate(R.layout.shou_list_item, null);
        Log.e("TAG","第"+i+"次");
        if(i>=1) {
            i=i+1;
        }
        Shop shop = data.get(i);
        merchant_name = (TextView) cview.findViewById(R.id.shou_list_name);
        per_capita_consumption = (TextView) cview.findViewById(R.id.shou_list_price);
        measure_unit = (TextView) cview.findViewById(R.id.shou_list_avg);
        business_location = (TextView) cview.findViewById(R.id.shou_list_Address);
        time = (TextView) cview.findViewById(R.id.shou_list_time);
        shou_list_img=(ImageView)cview.findViewById(R.id.shou_list_image);

        imgUrlList=shop.getImgUrlList();
        merchant_name.setText(shop.getMerchant_name());
        per_capita_consumption.setText(shop.getPer_capita_consumption());
        measure_unit.setText(shop.getMeasure_unit());
        business_location.setText(shop.getBusiness_location());
        time.setText(shop.getOpening_time() + "-" + shop.getClosing_time());
        Picasso.with(context).load(imgUrlList.get(0).getImg_url()).into(shou_list_img);

        return cview;

    }

}

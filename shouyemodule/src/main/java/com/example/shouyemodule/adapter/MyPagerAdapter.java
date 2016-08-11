package com.example.shouyemodule.adapter;

import android.util.Log;
import android.view.View;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * Created by WTW on 2016/8/10.
 */

public class MyPagerAdapter extends PagerAdapter{


    private ArrayList<ImageView> data;

    public MyPagerAdapter(ArrayList<ImageView> data){
        this.data=data;
    }
    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position,Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 将View放置到当前显示界面上,这个显示界面就是参数container
        position=position % data.size();

        //Log.e("TAG","增加页"+position);
        ImageView v = data.get(position);

        ViewParent vp =v.getParent();
        if (vp!=null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(v);
            //Log.e("TAG","删除一个");
        }

        container.addView(v);
        //Log.e("TAG","增加一个");
        return v;
    }

}

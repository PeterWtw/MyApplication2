package com.example.shouyemodule.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by WTW on 2016/8/11.
 */

public class GridViewPageAdapter extends PagerAdapter {

    private ArrayList<GridView> data;

    public GridViewPageAdapter(ArrayList<GridView> data){
        this.data=data;

    }

    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    public void destroyItem(ViewGroup container, int position, Object object) {

        Log.e("TAG","进入删除");
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 将View放置到当前显示界面上,这个显示界面就是参数container
        GridView gv =data.get(position);
        container.addView(gv);
        Log.e("TAG","增加一个");
        return gv;
    }
}

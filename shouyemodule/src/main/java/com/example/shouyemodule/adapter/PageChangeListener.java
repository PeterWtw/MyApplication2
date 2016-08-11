package com.example.shouyemodule.adapter;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.example.shouyemodule.R;

import java.util.ArrayList;

/**
 * Created by WTW on 2016/8/10.
 */

public class PageChangeListener implements ViewPager.OnPageChangeListener {
    private ArrayList<ImageView> data;
    //int pageNumber;
    public  PageChangeListener(ArrayList<ImageView> data){
        this.data=data;
        //this.pageNumber=pageNumber;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //pageNumber=position;
        Log.e("TAG","pageNumber :"+position);
        for(int i=0;i<data.size();i++){
            if(i==position%data.size()%2){

                data.get(i).setImageResource(R.drawable.seleted);
            } else {

                data.get(i).setImageResource(R.drawable.no_seleted);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

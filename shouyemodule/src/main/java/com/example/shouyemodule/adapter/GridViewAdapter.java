package com.example.shouyemodule.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.shouyemodule.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WTW on 2016/8/11.
 */

public class GridViewAdapter extends BaseAdapter{

    private int[] icon;
    private String[] iconName;
    LayoutInflater inflater;

   public GridViewAdapter(int[] icon,String[] iconName, LayoutInflater inflater){
       this.icon=icon;
       this.iconName=iconName;
       this.inflater=inflater;
   }

    public int getCount() {
        return icon.length;
    }

    @Override
    public Object getItem(int i) {
        return icon[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.grid_item,null);
        ((ImageView)view.findViewById(R.id.grid_image)).setImageResource(icon[i]);
        ((TextView)view.findViewById(R.id.grid_text)).setText(iconName[i]);

        return view;
    }
}

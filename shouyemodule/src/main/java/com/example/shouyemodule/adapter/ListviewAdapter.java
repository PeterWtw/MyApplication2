package com.example.shouyemodule.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shouyemodule.Gridview_Activity;
import com.example.shouyemodule.R;
import com.example.shouyemodule.entity.Meishi;
import com.example.shouyemodule.entity.Shop;

import java.util.ArrayList;

/**
 * Created by WTW on 2016/8/15.
 */

public class ListviewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Meishi> data;
    private TextView txt;

    public ListviewAdapter(Context context, ArrayList<Meishi> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
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
        Log.e("TAG","第"+i+"次");
        cview= LayoutInflater.from(context).inflate(R.layout.meishi_item_layout,null);
        txt=(TextView)cview.findViewById(R.id.mei_item_txt);
        txt.setText(data.get(i).getParent_cate_name());
        Log.e("TAG","进来了吗"+data.get(i).getParent_cate_name());
        return cview;
    }

}

package com.example.shouyemodule.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shouyemodule.R;
import com.example.shouyemodule.entity.Meishi;
import com.example.shouyemodule.entity.MeishiText;

import java.util.ArrayList;

/**
 * Created by WTW on 2016/8/15.
 */

public class ListviewAdapter2 extends BaseAdapter {
    private Context context;

    private ArrayList<MeishiText> data;

    private TextView txt;

    public ListviewAdapter2(Context context, ArrayList<MeishiText> data){
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
        cview= LayoutInflater.from(context).inflate(R.layout.meishi_item_layout2,null);
        txt=(TextView)cview.findViewById(R.id.mei_item_txt2);
        txt.setText(data.get(i).getChild_category_name());

        return cview;
    }

}

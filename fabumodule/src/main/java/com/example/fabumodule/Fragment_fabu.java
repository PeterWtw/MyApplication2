package com.example.fabumodule;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_fabu extends Fragment {
    private static GridView gview;
    private static List<Map<String, Object>> data_list;
    private static SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = { R.mipmap.meishi, R.mipmap.yule,
            R.mipmap.fangchan, R.mipmap.che, R.mipmap.hunqing,
            R.mipmap.zhuangxiu, R.mipmap.jiaoyu, R.mipmap.gongzuo,
            R.mipmap.baihuo, R.mipmap.tiaozhao, R.mipmap.shangwu,
            R.mipmap.bianmin,R.mipmap.laoxianghui,R.mipmap.qita};
    private String[] iconName = { "美食", "娱乐", "房产", "车", "婚庆", "装修", "教育",
            "工作", "百货", "跳蚤", "商务", "便民","老乡会","其他" };

    View mainview;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainview = inflater.inflate(R.layout.fabu_layout, null);

        gview = (GridView) mainview.findViewById(R.id.gview);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View v=inflater.inflate(R.layout.fabuwindow_layout,null);
                PopupWindow popupWindow=new PopupWindow(v,500,800);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(mainview , Gravity.BOTTOM,0);
            }
        });

        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.item, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
        return mainview;
    }

}


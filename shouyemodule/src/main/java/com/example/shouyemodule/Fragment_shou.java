package com.example.shouyemodule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shouyemodule.adapter.GridViewAdapter;
import com.example.shouyemodule.adapter.MyPagerAdapter;
import com.example.shouyemodule.adapter.PageChangeListener;

import java.util.ArrayList;



/**
 * Created by WTW on 2016/8/9.
 */

public class Fragment_shou extends Fragment{

    private static View view;
    private static View view1;
    private static ImageView imageView;
    private static ViewPager viewpager1;
    private static ViewPager viewpager2;
    private static GridView gridView1;
    private static GridView gridView2;
    private static FragmentManager fm;
    private static int pageNumber = 0;
    private static boolean flag = true;
    private int[] icon = { R.drawable.meishi, R.drawable.yule,
            R.drawable.fangchan, R.drawable.che, R.drawable.hunqing,
            R.drawable.zhuangxiu, R.drawable.jiaoyu, R.drawable.gongzuo,
            };
    private String[] iconName = { "美食", "娱乐", "房产", "车", "婚庆", "装修", "教育",
            "工作"};
    private int[] icon1 = { R.drawable.baihuo, R.drawable.tiaozhao,
            R.drawable.shangwu, R.drawable.bianmin, R.drawable.laoxianghui,
            R.drawable.qita};
    private String[] iconName1 = { "百货", "跳蚤", "商务", "便民", "老乡会", "其他"};
    int[] imgs = {R.drawable.viewpage1, R.drawable.viewpage2,R.drawable.viewpage1,R.drawable.viewpage2};

    ArrayList<ImageView> data = new ArrayList();
    ArrayList<ImageView> data1 = new ArrayList();
    ArrayList<View> data2 = new ArrayList();



    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            Log.e("TAG","信息"+msg.arg1);
            viewpager1.setCurrentItem(msg.arg1);
        }

    };

    @Override
    public View onCreateView(final LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_shou,null);

        ImageView dengbtn=(ImageView)view.findViewById(R.id.head_image_button);
        dengbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getContext(),DengluActivity.class);
                startActivity(intent);
            }
        });
        data.clear();
        data1.clear();

        data1.add((ImageView) view.findViewById(R.id.dot1));
        data1.add((ImageView) view.findViewById(R.id.dot2));

        viewpager1=(ViewPager)view.findViewById(R.id.viewpage1);

        for(int i=0;i<imgs.length;i++){
            View v1 = inflater.inflate(R.layout.vp_imgitem, null);
            imageView=(ImageView)v1.findViewById(R.id.img_item);
            imageView.setBackgroundResource(imgs[i]);
            data.add(imageView);
        }

        MyPagerAdapter adapter=new MyPagerAdapter(data);
        viewpager1.setAdapter(adapter);

        PageChangeListener listener=new PageChangeListener(data1);
        viewpager1.setOnPageChangeListener(listener);

        viewpager2=(ViewPager)view.findViewById(R.id.viewpage2);

        view1=inflater.inflate(R.layout.gridview_layout1,null);
        gridView1=(GridView) view1.findViewById(R.id.gview1);
        GridViewAdapter adapter1=new GridViewAdapter(icon,iconName,inflater);
        gridView1.setAdapter(adapter1);

        gridView2=(GridView) view1.findViewById(R.id.gview1);
        GridViewAdapter adapter2=new GridViewAdapter(icon1,iconName1,inflater);
        gridView2.setAdapter(adapter2);

        data2.add(gridView1);
        data2.add(gridView2);

        if(flag){
            Log.e("TAG","进入线程");
            new Thread() {

                @Override
                public void run() {

                    while (true) {

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Log.e("TAG",this.getName());
                        pageNumber++;
                        Log.e("TAG","page"+pageNumber);
                        Message message = handler.obtainMessage();
                        message.arg1 = pageNumber;
                        handler.sendMessage(message);

                    }
                }
            }.start();
            Log.e("TAG","开始线程");
        }
        flag=false;

        return view;

    }
}

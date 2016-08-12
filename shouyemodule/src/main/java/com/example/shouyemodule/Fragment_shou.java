package com.example.shouyemodule;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shouyemodule.adapter.GridViewAdapter;
import com.example.shouyemodule.adapter.GridViewPageAdapter;
import com.example.shouyemodule.adapter.MyPagerAdapter;
import com.example.shouyemodule.adapter.PageChangeListener;

import java.util.ArrayList;



/**
 * Created by WTW on 2016/8/9.
 */

public class Fragment_shou extends Fragment{

    private static View view;
    private static View view1;
    private static View view2;
    private static ImageView imageView;
    private static ViewPager viewpager1;
    private static ViewPager viewpager2;
    private static GridView gridView1;
    private static GridView gridView2;

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

    ArrayList<ImageView> vpdata1 = new ArrayList();
    ArrayList<ImageView> dotdata1 = new ArrayList();
    ArrayList<GridView>  vpdata2 = new ArrayList();
    ArrayList<ImageView>  dotdata2 = new ArrayList();

    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            //Log.e("TAG","信息"+msg.arg1);
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
        final TextView cityname=(TextView) view.findViewById(R.id.head_txt1);
        cityname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 View popview=inflater.inflate(R.layout.city_window,null);
                 PopupWindow pop=new PopupWindow(popview,200,300);
                 pop.setBackgroundDrawable(new BitmapDrawable());
                 pop.setFocusable(true);
                 pop.showAsDropDown(cityname,0,0);
            }
        });
        vpdata1.clear();
        dotdata1.clear();
        vpdata2.clear();
        dotdata2.clear();

        dotdata1.add((ImageView) view.findViewById(R.id.dot1));
        dotdata1.add((ImageView) view.findViewById(R.id.dot2));
        dotdata2.add((ImageView) view.findViewById(R.id.dot3));
        dotdata2.add((ImageView) view.findViewById(R.id.dot4));



        for(int i=0;i<imgs.length;i++){
            View v1 = inflater.inflate(R.layout.vp_imgitem, null);
            imageView=(ImageView)v1.findViewById(R.id.img_item);
            imageView.setBackgroundResource(imgs[i]);
            vpdata1.add(imageView);
        }

        viewpager1=(ViewPager)view.findViewById(R.id.viewpage1);
        viewpager1.setAdapter(new MyPagerAdapter(vpdata1));
        viewpager1.setOnPageChangeListener(new PageChangeListener(dotdata1));


        view1=inflater.inflate(R.layout.gridview_layout,null);
        gridView1=(GridView) view1.findViewById(R.id.gview_shou);
        GridViewAdapter adapter1=new GridViewAdapter(icon,iconName,inflater);
        gridView1.setAdapter(adapter1);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(getContext(),Gridview_Activity.class);
                startActivity(intent);

            }
        });


        view2=inflater.inflate(R.layout.gridview_layout,null);
        gridView2=(GridView) view2.findViewById(R.id.gview_shou);
        GridViewAdapter adapter2=new GridViewAdapter(icon1,iconName1,inflater);
        gridView2.setAdapter(adapter2);

        vpdata2.add(gridView1);
        vpdata2.add(gridView2);

        viewpager2=(ViewPager)view.findViewById(R.id.viewpage2);
        viewpager2.setAdapter(new GridViewPageAdapter(vpdata2));
        viewpager2.setOnPageChangeListener(new PageChangeListener(dotdata2));



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
                        //Log.e("TAG",this.getName());
                        pageNumber++;
                        //Log.e("TAG","page"+pageNumber);
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

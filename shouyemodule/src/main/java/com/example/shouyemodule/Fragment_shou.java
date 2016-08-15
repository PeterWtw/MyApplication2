package com.example.shouyemodule;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shouyemodule.Http.data_http;
import com.example.shouyemodule.adapter.GridViewAdapter;
import com.example.shouyemodule.adapter.GridViewPageAdapter;
import com.example.shouyemodule.adapter.MyPagerAdapter;
import com.example.shouyemodule.adapter.PageChangeListener;
import com.example.shouyemodule.adapter.myBaseAdapter;
import com.example.shouyemodule.entity.Shop;
import com.example.shouyemodule.entity.ShopImage;

import com.example.xiangqingmodule.DetailsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WTW on 2016/8/9.
 */

public class Fragment_shou extends Fragment {

    private View view;
    private View view1;
    private View view2;
    private ImageView imageView;
    private ImageView shou_list_phone;
    private ViewPager viewpager1;
    private ViewPager viewpager2;
    private GridView gridView1;
    private GridView gridView2;
    private BaseAdapter baseadapter;
    private ListView listView;
    String list;


    private static int pageNumber = 0;
    private static boolean flag = true;
    private int[] icon = {R.drawable.meishi, R.drawable.yule,
            R.drawable.fangchan, R.drawable.che, R.drawable.hunqing,
            R.drawable.zhuangxiu, R.drawable.jiaoyu, R.drawable.gongzuo,
    };
    private String[] iconName = {"美食", "娱乐", "房产", "车", "婚庆", "装修", "教育",
            "工作"};
    private int[] icon1 = {R.drawable.baihuo, R.drawable.tiaozhao,
            R.drawable.shangwu, R.drawable.bianmin, R.drawable.laoxianghui,
            R.drawable.qita};
    private String[] iconName1 = {"百货", "跳蚤", "商务", "便民", "老乡会", "其他"};
    int[] imgs = {R.drawable.viewpage1, R.drawable.viewpage2, R.drawable.viewpage1, R.drawable.viewpage2};

    ArrayList<ImageView> vpdata1 = new ArrayList();
    ArrayList<ImageView> dotdata1 = new ArrayList();
    ArrayList<GridView> vpdata2 = new ArrayList();
    ArrayList<ImageView> dotdata2 = new ArrayList();
    ArrayList<Shop> shopdatas = new ArrayList();

    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            //Log.e("TAG","信息"+msg.arg1);
            viewpager1.setCurrentItem(msg.arg1);
        }

    };

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_shou, null);
        getdata();

        ImageView dengbtn = (ImageView) view.findViewById(R.id.head_image_button);
        dengbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), DengluActivity.class);
                startActivity(intent);
            }
        });
        //View vv=LayoutInflater.from(getContext()).inflate(R.layout.shou_list_item,null);


        final TextView cityname = (TextView) view.findViewById(R.id.head_txt1);
        cityname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popview = inflater.inflate(R.layout.city_window, null);
                PopupWindow pop = new PopupWindow(popview, 200, 300);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setFocusable(true);
                pop.showAsDropDown(cityname, 0, 0);
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


        for (int i = 0; i < imgs.length; i++) {
            View v1 = inflater.inflate(R.layout.vp_imgitem, null);
            imageView = (ImageView) v1.findViewById(R.id.img_item);
            imageView.setBackgroundResource(imgs[i]);
            vpdata1.add(imageView);
        }

        viewpager1 = (ViewPager) view.findViewById(R.id.viewpage1);
        viewpager1.setAdapter(new MyPagerAdapter(vpdata1));
        viewpager1.setOnPageChangeListener(new PageChangeListener(dotdata1));


        view1 = inflater.inflate(R.layout.gridview_layout, null);
        gridView1 = (GridView) view1.findViewById(R.id.gview_shou);
        GridViewAdapter adapter1 = new GridViewAdapter(icon, iconName, inflater);
        gridView1.setAdapter(adapter1);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(getContext(), Gridview_Activity.class);
                intent.putExtra("data",list);
                intent.putExtra("kind",iconName[i]);
                Log.e("TAG","发送"+list);
                startActivity(intent);

            }
        });


        view2 = inflater.inflate(R.layout.gridview_layout, null);
        gridView2 = (GridView) view2.findViewById(R.id.gview_shou);
        GridViewAdapter adapter2 = new GridViewAdapter(icon1, iconName1, inflater);
        gridView2.setAdapter(adapter2);

        vpdata2.add(gridView1);
        vpdata2.add(gridView2);

        viewpager2 = (ViewPager) view.findViewById(R.id.viewpage2);
        viewpager2.setAdapter(new GridViewPageAdapter(vpdata2));
        viewpager2.setOnPageChangeListener(new PageChangeListener(dotdata2));

        listView = (ListView) view.findViewById(R.id.shou_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String shopName=shopdatas.get(i).getMerchant_name();
                final  String phone=shopdatas.get(i).getPhone();
                Intent intent=new Intent();
                intent.setClass(getContext(),DetailsActivity.class);
                intent.putExtra("shop_name",shopName);
                Toast.makeText(getContext(),shopName,Toast.LENGTH_SHORT).show();
                startActivity(intent);

                shou_list_phone=(ImageView)view.findViewById(R.id.shou_list_phone);
                shou_list_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setTitle("确定要拨打："+phone);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent callphone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                startActivity(callphone);
                            }
                        });
                        builder.setNegativeButton("取消",null);

                        builder.show();

                    }
                });

            }
        });

        if (flag) {
            //Log.e("TAG", "进入线程");
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
            //Log.e("TAG", "开始线程");
        }
        flag = false;

        return view;
    }

    public void getdata() {
        RequestParams params = new RequestParams(data_http.HTTP_audting);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                //Log.e("TAG",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    list=jsonObject.getString("list");

                    Gson gson=new Gson();
                    ArrayList<Shop> datas=gson.fromJson(list,new TypeToken<ArrayList<Shop>>(){}.getType());

                    if(datas==null){
                        Toast.makeText(getContext(), "数据为空", Toast.LENGTH_SHORT).show();
                    }else {
                        shopdatas.clear();
                        shopdatas.addAll(datas);

                        baseadapter=new myBaseAdapter(getContext(),shopdatas);

                        listView.setAdapter(baseadapter);
                        baseadapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {

            }
        });

    }

}

package com.example.shouyemodule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shouyemodule.Http.data_http;
import com.example.shouyemodule.adapter.ListviewAdapter;
import com.example.shouyemodule.adapter.ListviewAdapter2;
import com.example.shouyemodule.adapter.myBaseAdapter;
import com.example.shouyemodule.entity.Meishi;
import com.example.shouyemodule.entity.MeishiText;
import com.example.shouyemodule.entity.Shop;
import com.example.xiangqingmodule.DetailsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by WTW on 2016/8/11.
 */

public class Gridview_Activity extends Activity {

    private ListView meishi_list;
    private ArrayList<Shop> datas;
    private ArrayList<Meishi> dat;
    private ArrayList<MeishiText> datt;
    String list;
    ListView listView;
    ListView listView2;



    public  void gridclick(View v){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.griditem_layout);

        meishi_list=(ListView)findViewById(R.id.meishi_list);
        Intent intent=getIntent();
        String shopmsg=intent.getStringExtra("data");
        Log.e("TAG","拿到了吗"+shopmsg);
        if(shopmsg!=null) {
            Gson gson = new Gson();
            datas = gson.fromJson(shopmsg, new TypeToken<ArrayList<Shop>>() {
            }.getType());
            myBaseAdapter adapter = new myBaseAdapter(Gridview_Activity.this, datas);
            meishi_list.setAdapter(adapter);
            meishi_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String shopName=datas.get(i).getMerchant_name();

                    Intent intent=new Intent();
                    intent.setClass(Gridview_Activity.this,DetailsActivity.class);
                    intent.putExtra("shop_name",shopName);
                    Toast.makeText(Gridview_Activity.this,shopName,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }else {
            Toast.makeText(Gridview_Activity.this,"请检查网络",Toast.LENGTH_SHORT).show();
        }


        String kind=intent.getStringExtra("kind");
        ((TextView)findViewById(R.id.item_title)).setText(kind);

        getDatas();
        final LinearLayout meishi=(LinearLayout)findViewById(R.id.layout_shop);
        meishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display dp=getWindow().getWindowManager().getDefaultDisplay();
                PopupWindow pop=new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT,(int)(dp.getHeight()*0.5));
                final View poplayout= LayoutInflater.from(Gridview_Activity.this).inflate(R.layout.meishi_window,null);
                pop.setContentView(poplayout);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setFocusable(true);
                pop.showAsDropDown(meishi,0,0);

                listView=(ListView) poplayout.findViewById(R.id.meishi_windowlist1);
                listView2=(ListView) poplayout.findViewById(R.id.meishi_windowlist2);
                listView.setAdapter(new ListviewAdapter(Gridview_Activity.this,dat));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        datt=(ArrayList<MeishiText>)dat.get(i).getChildCategoryList();
                        listView2.setAdapter(new ListviewAdapter2(Gridview_Activity.this,datt));
                    }
                });

                datt=(ArrayList<MeishiText>)dat.get(0).getChildCategoryList();
                listView2.setAdapter(new ListviewAdapter2(Gridview_Activity.this,datt));



            }
        });
    }
    public void getDatas(){
        RequestParams params=new RequestParams(data_http.HTTP_date);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","数据"+result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    list=jsonObject.getString("list");
                    Gson gson=new Gson();
                    dat=gson.fromJson(list,new TypeToken<ArrayList<Meishi>>(){}.getType());
                    Log.e("TAG","数据2"+dat);


                }catch(Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(Gridview_Activity.this, "网络异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(Gridview_Activity.this, "取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {

            }
        });


    }
}

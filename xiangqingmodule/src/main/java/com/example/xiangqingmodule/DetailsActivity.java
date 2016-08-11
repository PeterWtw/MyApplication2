package com.example.xiangqingmodule;

import android.app.Activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiangqingmodule.detailshttpurl.DetailsHttp;
import com.example.xiangqingmodule.shopdata.ShopDateJson;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;


import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;


public class DetailsActivity extends Activity {

    ImageView details_back_btn,details_fengxiang,details_collect,jupopuw_zhiliang,jupopuw_taidu,jupopuw_guanggao,jupopuw_other;
    TextView details_shop_name,details_average,details_time,details_address,details_contacts,details_phone_number,details_shop_content,
            jupopuw_cancel,jupopuw_confirm;
    ViewPager details_shop_image;
    LinearLayout details_phone,details_jubao;
    EditText jupopuw_edittext;
    String shopdata;
    View v;
    PopupWindow jubaopopu;
    ArrayList<ShopDateJson> shoplist=new ArrayList();


    public void detailsclik(View view){
        int i = view.getId();
        //返回按钮
        if (i == R.id.details_back_btn) {
            finish();
        }
        //分享按钮
        else if (i==R.id.details_fengxiang){
            //分享
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        }
        //收藏
        else if (i==R.id.details_collect){
            //收藏
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }
        //打电话
        else if(i==R.id.details_phone){
            Toast.makeText(this, "打电话", Toast.LENGTH_SHORT).show();
        }
        //举报
        else if(i==R.id.details_jubao){
            Toast.makeText(this, "举报", Toast.LENGTH_SHORT).show();
           //设置PopupWindow
            v= LayoutInflater.from(DetailsActivity.this).inflate(R.layout.jubaopopuw,null);
            jubaopopu=new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            jubaopopu.setBackgroundDrawable(new BitmapDrawable());
            jubaopopu.setFocusable(true);
            //显示PopupWindow
            jubaopopu.showAtLocation(details_back_btn,Gravity.CENTER,0,0);
            //点击和消失PopupWindow的背景设置
            WindowManager.LayoutParams bage=getWindow().getAttributes();
            bage.alpha=0.5f;
            getWindow().setAttributes(bage);
            jubaopopu.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams disbage=getWindow().getAttributes();
                    disbage.alpha=1f;
                    getWindow().setAttributes(disbage);
                }
            });
            //举报PopuWindow中的控件
            jupopuw_zhiliang= (ImageView) v.findViewById(R.id.jupopuw_zhiliang);
            jupopuw_taidu= (ImageView) v.findViewById(R.id.jupopuw_taidu);
            jupopuw_guanggao= (ImageView) v.findViewById(R.id.jupopuw_guanggao);
            jupopuw_other= (ImageView) v.findViewById(R.id.jupopuw_other);
            jupopuw_cancel= (TextView) v.findViewById(R.id.jupopuw_cancel);
            jupopuw_confirm= (TextView) v.findViewById(R.id.jupopuw_confirm);

            //能隐藏的控件
            jupopuw_edittext= (EditText) v.findViewById(R.id.jupopuw_edittext);
            //点击取消按钮
            jupopuw_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jubaopopu.dismiss();
                }
            });
            //点击提交的事件
            jupopuw_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(DetailsActivity.this, "请选择原因", Toast.LENGTH_SHORT).show();

                }
            });
            //点击PopupWindow第一个选择
            jupopuw_zhiliang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jupopuw_zhiliang.setImageResource(R.drawable.select_hover);
                    jupopuw_taidu.setImageResource(R.drawable.select);
                    jupopuw_guanggao.setImageResource(R.drawable.select);
                    jupopuw_other.setImageResource(R.drawable.select);
                    jupopuw_edittext.setVisibility(View.GONE);
                    jupopupwdecide();
                }
            });
            //点击PopupWindow第二个选择
            jupopuw_taidu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jupopuw_zhiliang.setImageResource(R.drawable.select);
                    jupopuw_taidu.setImageResource(R.drawable.select_hover);
                    jupopuw_guanggao.setImageResource(R.drawable.select);
                    jupopuw_other.setImageResource(R.drawable.select);
                    jupopuw_edittext.setVisibility(View.GONE);
                    jupopupwdecide();
                }
            });
            //点击PopupWindow第三个选择
            jupopuw_guanggao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jupopuw_zhiliang.setImageResource(R.drawable.select);
                    jupopuw_taidu.setImageResource(R.drawable.select);
                    jupopuw_guanggao.setImageResource(R.drawable.select_hover);
                    jupopuw_other.setImageResource(R.drawable.select);
                    jupopuw_edittext.setVisibility(View.GONE);
                    jupopupwdecide();
                }
            });
            //点击PopupWindow第四个选择
            jupopuw_other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jupopuw_zhiliang.setImageResource(R.drawable.select);
                    jupopuw_taidu.setImageResource(R.drawable.select);
                    jupopuw_guanggao.setImageResource(R.drawable.select);
                    jupopuw_other.setImageResource(R.drawable.select_hover);
                    jupopuw_edittext.setVisibility(View.VISIBLE);
                    jupopupwdecide();
                }
            });

        }
    }



 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detailsay);

        //填充控件
        inview();
        //网络获取数据
        geturldata();



    }

    //网络获取数据
    public void geturldata(){
        RequestParams params = new RequestParams(DetailsHttp.HTTP_overallurl + DetailsHttp.HTTP_detailInfo+DetailsHttp.HTTP_audting+"1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                shopdata = result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("TAG", "不可用");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.v("TAG", "失败");
            }

            @Override
            public void onFinished() {
                Log.v("TAG", "完成");

                analyze();
            }
        });
    }

    //Json解析方法
    public void analyze(){

        try {
            JSONObject jsondata = new JSONObject(shopdata);
            String list = jsondata.getString("list");
            //GSON解析
            Gson gson = new Gson();
            ArrayList<ShopDateJson> jsons=gson.fromJson(list,new TypeToken<ArrayList<ShopDateJson>>(){}.getType());
            shoplist.addAll(jsons);
            //填充数据
            {
             //空




            }
            Log.v("TAG",shoplist.get(7).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //获取控件的方法
    public void inview(){
        details_back_btn= (ImageView) findViewById(R.id.details_back_btn);
        details_fengxiang= (ImageView) findViewById(R.id.details_fengxiang);
        details_collect= (ImageView) findViewById(R.id.details_collect);
        details_shop_image= (ViewPager) findViewById(R.id.details_shop_image);
        details_shop_name= (TextView) findViewById(R.id.details_shop_name);
        details_average= (TextView) findViewById(R.id.details_average);
        details_time= (TextView) findViewById(R.id.details_time);
        details_address= (TextView) findViewById(R.id.details_address);
        details_contacts= (TextView) findViewById(R.id.details_contacts);
        details_phone_number= (TextView) findViewById(R.id.details_phone_number);
        details_shop_content= (TextView) findViewById(R.id.details_shop_content);
        details_phone= (LinearLayout) findViewById(R.id.details_phone);
        details_jubao= (LinearLayout) findViewById(R.id.details_jubao);

    }
    //举报PopupWindo取消和确定按钮的监听事件
    public void jupopupwdecide(){

        //取消事件
        jupopuw_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jubaopopu.dismiss();
            }
        });
        //提交确定事件
        jupopuw_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailsActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                jubaopopu.dismiss();
            }
        });
    }


}

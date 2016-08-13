package com.example.xiangqingmodule;

import android.Manifest;
import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import it.sephiroth.android.library.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


public class DetailsActivity extends Activity {

    ImageView details_back_btn, details_fengxiang, details_collect, jupopuw_zhiliang, jupopuw_taidu, jupopuw_guanggao, jupopuw_other;
    TextView details_shop_name, details_average, details_time, details_address, details_contacts, details_phone_number, details_shop_content,
            jupopuw_cancel, jupopuw_confirm, callconfirm, callcancel,callphone_popupwindow;
    ViewPager details_shop_image;
    //ImageView details_shop_image;
    LinearLayout details_phone, details_jubao;
    EditText jupopuw_edittext;
    String shopdata;
    View v, callphonepplayout;
    PopupWindow jubaopopu,callphonepp;
    ArrayList shopname=new ArrayList();
    ArrayList<ShopDateJson> shoplist = new ArrayList();
    ArrayList<ImageView> urlimage=new ArrayList();
    PagerAdapter pagerAdapter;
    //商店的数量
    int shopcount;
    //选择的是第几个商店
    int shopnumber;
    //当前商店图片的数量
    int shopinagecount;
    int count=0;

    Thread s;
    Handler hander=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                count++;
                Log.e("TAG",count+"");
                details_shop_image.setCurrentItem(count);
            }
        }

    };

    class my_Thread extends Thread{
        public void run() {
            try {
                while(true){
                    Thread.sleep(1000*8);
                    hander.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }

    }

    public void detailsclik(View view) {
        int i = view.getId();
//返回按钮
        if (i == R.id.details_back_btn) {
            finish();
        }
//分享按钮
        else if (i == R.id.details_fengxiang) {
            //分享
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        }
//收藏
        else if (i == R.id.details_collect) {
            //收藏
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }
//打电话
        else if (i == R.id.details_phone) {

            //填充布局
            callphonepplayout = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.callphonepopupwindow, null);
            //找控件
            callconfirm = (TextView) callphonepplayout.findViewById(R.id.callconfirm);
            callcancel = (TextView) callphonepplayout.findViewById(R.id.callcancel);
            callphone_popupwindow= (TextView) callphonepplayout.findViewById(R.id.callphone_popupwindow);

            //显示电话话吗
            callphone_popupwindow.setText("确认拨打电话："+shoplist.get(shopnumber).getPhone());

            //打电话的PopupWindow的显示
            callphonepp = new PopupWindow(callphonepplayout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            callphonepp.setBackgroundDrawable(new BitmapDrawable());
            callphonepp.setFocusable(true);
            callphonepp.showAtLocation(details_back_btn, Gravity.CENTER, 0, 0);
            WindowManager.LayoutParams bage = getWindow().getAttributes();
            bage.alpha = 0.5f;
            getWindow().setAttributes(bage);
            //打电话的PopupWindow的消失事件
            callphonepp.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams newbage = getWindow().getAttributes();
                    newbage.alpha = 1f;
                    getWindow().setAttributes(newbage);
                }
            });
            //确定拨打电话按钮
            callconfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callphone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+shoplist.get(shopnumber).getPhone()));
                    if (ActivityCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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




            //取消按钮事件
            callcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callphonepp.dismiss();
                }
            });

        }
//举报
        else if(i==R.id.details_jubao){

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
             //获得点击的商店名字
                Intent shme=getIntent();
                String shop_name=shme.getStringExtra("shop_name");

                //将所有的商店名字放入到shopname这个集合中
                shopcount=shoplist.size();
                for(int i=0;i<shopcount;i++){
                  shopname.add(shoplist.get(i).getMerchant_name());
                }
                //监听点击的是哪家商店名开始填充数据
                shopdatafill(shop_name);

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
        //details_shop_image= (ImageView) findViewById(R.id.details_shop_image);
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
    //数据填充方法
    public void shopdatafill(String shopdataname){
        for( int i=0;i<shopname.size();i++){
            String nameshop=shopname.get(i).toString();
            if(shopdataname.equals(nameshop)){
                shopnumber=i;
            }
        }
        //当前商店信息的图片数量
        shopinagecount=shoplist.get(shopnumber).getImgUrlList().size();
        //将图片地址放入urlimage集合中

        for(int i=0;i<shopinagecount;i++){
            ImageView imageView=new ImageView(DetailsActivity.this);
            Picasso.with(DetailsActivity.this).load(shoplist.get(shopnumber).getImgUrlList().get(i).getImg_url()).into(imageView);
          // String imageurl=shoplist.get(shopnumber).getImgUrlList().get(i).getImg_url();
            urlimage.add(imageView);

        }



        details_shop_name.setText("商店名："+shoplist.get(shopnumber).getMerchant_name());
        details_average.setText("人均："+shoplist.get(shopnumber).getPer_capita_consumption()+"/人起");
        details_time.setText("营业时间："+shoplist.get(shopnumber).getOpening_time()+"-"+shoplist.get(shopnumber).getClosing_time());
        details_address.setText("地址："+shoplist.get(shopnumber).getBusiness_location());
        details_phone_number.setText("电话："+shoplist.get(shopnumber).getPhone());
        details_shop_content.setText(shoplist.get(shopnumber).getDetail_info());
        details_contacts.setText("联系人："+shoplist.get(shopnumber).getMerchant_name());
        lunbotu();

    }

    public void lunbotu(){


        //ViewPage设置适配器

         pagerAdapter=new PagerAdapter() {

             @Override
             public void destroyItem(ViewGroup container, int position, Object object) {

             }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                position=position%urlimage.size();
                if(position<0){
                    position=urlimage.size()+position;
                }
                ImageView ima=urlimage.get(position);
                ViewParent vp=ima.getParent();
                if(vp!=null){
                    ViewGroup parent= (ViewGroup) vp;
                    parent.removeView(ima);
                }
                ima.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(ima);


                return ima;
            }



            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

        };
        //设置适配器
        details_shop_image.setAdapter(pagerAdapter);

        s=new my_Thread();
        s.start();


    }

}

package com.example.xiangqingmodule;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiangqingmodule.shopdata.ShopCollectDate;

import org.w3c.dom.Text;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

public class MyCollect extends Activity {
    ImageView collect_back,collect_listview_image;
    ListView collect_listview;

    TextView collect_shop_name,collect_avrage,collect_address,collect_time;

    ArrayList<ShopCollectDate> shop;
    SQLiteOpenHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        //获取控件
        inint();
        //退出按钮事件
        click();




        helper=new SQLiteOpenHelper(MyCollect.this,"collectsql.db",null,2) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                Log.v("TAG","建立表进入");
                String sql="create table collect_tb(" +
                        "_id integer primary key autoincrement," +
                        "shop_name varchar," +
                        "shop_cost varchar,"+
                        "shop_address varchar," +
                        "shop_time varchar," +
                        "shop_imageurl varchar)"  ;
                sqLiteDatabase.execSQL(sql);

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqlite, int i, int i1) {

            }
        };
        db = helper.getReadableDatabase();

        shop=getAllshopdata();



        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {

                return shop.size();
            }

            @Override
            public Object getItem(int i) {

                return shop.get(i);
            }

            @Override

            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view= LayoutInflater.from(MyCollect.this).inflate(R.layout.collect_listview_layout,null);
                collect_shop_name=(TextView)view.findViewById(R.id.collect_shop_name);
                collect_avrage=(TextView)view.findViewById(R.id.collect_avrage);
                collect_address=(TextView)view.findViewById(R.id.collect_address);
                collect_time=(TextView)view.findViewById(R.id.collect_time);
                collect_listview_image= (ImageView) view.findViewById(R.id.collect_listview_image);

                //商店名字         0
                //商店平均消费     1
                //商店地址         2
                //商店营业时间     3
                //商店图片地址     4


                collect_shop_name.setText(shop.get(i).shop_naem);
                collect_avrage.setText(shop.get(i).shop_cast);
                collect_address.setText(shop.get(i).shop_address);
                collect_time.setText(shop.get(i).shop_time);
                Picasso.with(MyCollect.this).load(shop.get(i).shop_image).into(collect_listview_image);


                return view;
            }
        };
        collect_listview.setAdapter(adapter);


    }

    //获取控件方法
    public void inint(){
        collect_back=(ImageView)findViewById(R.id.collect_back);
        collect_listview=(ListView)findViewById(R.id.collect_listview);

    }
    //退出按钮事件方法
    public void click(){
        collect_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public ArrayList<ShopCollectDate> getAllshopdata(){

        ArrayList<ShopCollectDate> shop_data = new ArrayList<>();
        String sql = "select * from collect_tb ";
        Cursor c = db.rawQuery(sql , null);
        while(c.moveToNext()){
            ShopCollectDate scd=new ShopCollectDate();
            scd.shop_naem=c.getString(1);//商店名字         0
            scd.shop_cast=c.getString(2);//商店平均消费     1
            scd.shop_address=c.getString(3);//商店地址         2
            scd.shop_time=c.getString(4);//商店营业时间     3
            scd.shop_image=c.getString(5);//商店图片地址     4
            shop_data.add(scd);
        }
        return shop_data;
    }

}

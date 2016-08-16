package com.example.shouyemodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shouyemodule.adapter.myBaseAdapter;
import com.example.shouyemodule.entity.Shop;
import com.example.xiangqingmodule.DetailsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by WTW on 2016/8/16.
 */

public class Laoxiang_Activity extends Activity {
    private ListView lao_list;
    private ArrayList<Shop> datas;

    public void laoclick(View v){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laoxiang_layout);
        lao_list=(ListView)findViewById(R.id.lao_list);
        Intent intent=getIntent();
        String shops=intent.getStringExtra("data");
        if(shops!=null) {
            Gson gson = new Gson();
            datas = gson.fromJson(shops, new TypeToken<ArrayList<Shop>>() {
            }.getType());
            myBaseAdapter adapter = new myBaseAdapter(Laoxiang_Activity.this, datas);
            lao_list.setAdapter(adapter);
            lao_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String shopName=datas.get(i).getMerchant_name();

                    Intent intent=new Intent();
                    intent.setClass(Laoxiang_Activity.this,DetailsActivity.class);
                    intent.putExtra("shop_name",shopName);
                    Toast.makeText(Laoxiang_Activity.this,shopName,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }else {
            Toast.makeText(Laoxiang_Activity.this,"请检查网络",Toast.LENGTH_SHORT).show();
        }



    }
}

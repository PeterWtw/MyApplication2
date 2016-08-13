package com.example.shouyemodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shouyemodule.Http.data_http;
import com.example.shouyemodule.adapter.myBaseAdapter;
import com.example.shouyemodule.entity.Shop;
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
        }else {
            Toast.makeText(Gridview_Activity.this,"请检查网络",Toast.LENGTH_SHORT).show();
        }
        String kind=intent.getStringExtra("kind");
        ((TextView)findViewById(R.id.item_title)).setText(kind);

        LinearLayout meishi=(LinearLayout)findViewById(R.id.layout_shop);

    }

}

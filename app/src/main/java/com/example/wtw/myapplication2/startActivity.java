package com.example.wtw.myapplication2;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabumodule.Fragment_fabu;
import com.example.shouyemodule.Fragment_shou;
import com.example.usermodule.Fragment_user;


/**
 * Created by WTW on 2016/8/9.
 */

public class startActivity extends FragmentActivity {

    private static FragmentManager fm;
    private TextView t1;
    private TextView t2;
    private TextView t3;

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    public void myclick(View v){
        fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        switch(v.getId()){
            case R.id.index_layout:
                t1.setTextColor(Color.parseColor("#ff50c577"));
                img1.setImageResource(R.drawable.home_press);
                t2.setTextColor(Color.parseColor("#535353"));
                img2.setImageResource(R.drawable.publish);
                t3.setTextColor(Color.parseColor("#535353"));
                img3.setImageResource(R.drawable.wode);


                ft.replace(R.id.fragment_content,new Fragment_shou());
                ft.commit();
                break;
            case R.id.publish_layout:
                t2.setTextColor(Color.parseColor("#ff50c577"));
                img2.setImageResource(R.drawable.publish_press);
                t1.setTextColor(Color.parseColor("#535353"));
                img1.setImageResource(R.drawable.home);
                t3.setTextColor(Color.parseColor("#535353"));
                img3.setImageResource(R.drawable.wode);

                ft.replace(R.id.fragment_content,new Fragment_fabu());
                ft.commit();
                break;
            case R.id.my_layout:
                t3.setTextColor(Color.parseColor("#ff50c577"));
                img3.setImageResource(R.drawable.wode_press);
                t1.setTextColor(Color.parseColor("#535353"));
                img1.setImageResource(R.drawable.home);
                t2.setTextColor(Color.parseColor("#535353"));
                img2.setImageResource(R.drawable.publish);

                ft.replace(R.id.fragment_content,new Fragment_user());
                ft.commit();
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        t1=(TextView)findViewById(R.id.index_text);
        t2=(TextView)findViewById(R.id.publish_text);
        t3=(TextView)findViewById(R.id.my_text);

        img1=(ImageView)findViewById(R.id.index_image);
        img2=(ImageView)findViewById(R.id.publish_image);
        img3=(ImageView)findViewById(R.id.my_image);

        t1.setTextColor(Color.parseColor("#ff50c577"));
        img1.setImageResource(R.drawable.home_press);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.fragment_content,new Fragment_shou());
        ft.commit();

    }
}

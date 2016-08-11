package com.example.usermodule;

/**
 * Created by andi_liang on 2016/8/10.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//作为碎片要证明自己是碎片所以要继承碎片
public class Deng_fragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.deng_layout,null);
        view.findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.e("TAG","222");
               FragmentManager fm=getChildFragmentManager();
               FragmentTransaction ft=fm.beginTransaction();
                Deng_fragment f1=new Deng_fragment();
               ft.remove(f1);
                ft.addToBackStack(null);
                ft.commit();

//
//                //替换
//                ft.replace(R.id.my_content,);
//                Log.e("TAG","点击促发".toString());
//                ft.commit();
            }
        });
        return view;


    }
}

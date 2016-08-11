package com.example.usermodule;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_user extends Fragment {
    FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_user,null);

        TextView textview1 = (TextView)view.findViewById(R.id.spotBowling_login);
        TextView textview2 = (TextView) view.findViewById(R.id.Mypublish);
        TextView textview3 = (TextView) view.findViewById(R.id.Mydraft);
        TextView textview4 = (TextView) view.findViewById(R.id.Mycollect);
        TextView textview5 = (TextView) view.findViewById(R.id.Myinformation);
        TextView textview6 = (TextView) view.findViewById(R.id.PlatformDeclare);
        TextView textview7 = (TextView) view.findViewById(R.id.LoveHometown);
        TextView textview8 = (TextView) view.findViewById(R.id.ShareSoftware);
        TextView textview9 = (TextView) view.findViewById(R.id.AlterPassword);
        TextView textview10 = (TextView) view.findViewById(R.id.PurgeCache);
        fm=getChildFragmentManager();
        View.OnClickListener handle = new View.OnClickListener() {

            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.spotBowling_login) {
                    FragmentTransaction ft = fm.beginTransaction();
                    Deng_fragment f1 = new Deng_fragment();
                    ft.replace(R.id.my_content, f1);
                    Log.e("TAG", "点击促发".toString());
                    ft.commit();
                } else if (i == R.id.Mypublish) {
                    FragmentTransaction ft = fm.beginTransaction();
                    Deng_fragment f2 = new Deng_fragment();
                    ft.replace(R.id.my_content, f2);
                    ft.commit();
                } else if (i == R.id.Mydraft) {
                    FragmentTransaction ft = fm.beginTransaction();
                    Deng_fragment f3 = new Deng_fragment();
                    ft.replace(R.id.my_content, f3);
                    ft.commit();
                } else if (i == R.id.Mycollect) {
                    FragmentTransaction ft = fm.beginTransaction();
                    Deng_fragment f4 = new Deng_fragment();
                    ft.replace(R.id.my_content, f4);
                    ft.commit();
                } else if (i == R.id.Myinformation) {
                    FragmentTransaction ft = fm.beginTransaction();
                    Deng_fragment f5 = new Deng_fragment();
                    ft.replace(R.id.my_content, f5);
                    ft.commit();
                } else if (i == R.id.PlatformDeclare) {
                    FragmentTransaction ft = fm.beginTransaction();
                    platfrom_declaere f6 = new platfrom_declaere();
                    ft.replace(R.id.my_content, f6);
                    ft.commit();
                } else if (i == R.id.LoveHometown) {
                    FragmentTransaction ft = fm.beginTransaction();
                    love_hometown f7 = new love_hometown();
                    ft.replace(R.id.my_content, f7);
                    ft.commit();
                } else if (i == R.id.ShareSoftware) {
                } else if (i == R.id.AlterPassword) {
                } else if (i == R.id.PurgeCache) {
                }
            }

        };
        textview1.setOnClickListener(handle);
        textview2.setOnClickListener(handle);
        textview3.setOnClickListener(handle);
        textview4.setOnClickListener(handle);
        textview5.setOnClickListener(handle);
        textview6.setOnClickListener(handle);
        textview7.setOnClickListener(handle);
        textview8.setOnClickListener(handle);
        textview9.setOnClickListener(handle);
        textview10.setOnClickListener(handle);

        return view;
    }
}
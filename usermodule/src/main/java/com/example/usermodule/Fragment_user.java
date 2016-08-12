package com.example.usermodule;


import android.content.Intent;
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


        /*
        TextView textview2 = (TextView) view.findViewById(R.id.Mypublish);
        TextView textview3 = (TextView) view.findViewById(R.id.Mydraft);
        TextView textview4 = (TextView) view.findViewById(R.id.Mycollect);
        TextView textview5 = (TextView) view.findViewById(R.id.Myinformation);
        TextView textview6 = (TextView) view.findViewById(R.id.PlatformDeclare);
        TextView textview7 = (TextView) view.findViewById(R.id.LoveHometown);
        TextView textview8 = (TextView) view.findViewById(R.id.ShareSoftware);
        TextView textview9 = (TextView) view.findViewById(R.id.AlterPassword);
        TextView textview10 = (TextView) view.findViewById(R.id.PurgeCache);
        */
        //fm=getChildFragmentManager();
        TextView textView1=(TextView) view.findViewById(R.id.denglu_Btn);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getContext(),Deng_activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
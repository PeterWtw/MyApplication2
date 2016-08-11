package com.example.usermodule;

/**
 * Created by andi_liang on 2016/8/10.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//作为碎片要证明自己是碎片所以要继承碎片
public class love_hometown extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.love_hometown, null);
        return view;
    }

}

package com.example.usermodule.util;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/8/12.
 */

public class MyApplication_user extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }
}

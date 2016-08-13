package com.example.shouyemodule.Application;

import android.app.Application;

import org.xutils.x;



public class myApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}

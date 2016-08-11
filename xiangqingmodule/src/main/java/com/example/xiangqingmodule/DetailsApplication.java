package com.example.xiangqingmodule;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wihltmnet on 2016/8/10.
 */

public class DetailsApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}

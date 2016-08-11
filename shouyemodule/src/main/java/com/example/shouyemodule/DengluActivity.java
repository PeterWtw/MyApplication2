package com.example.shouyemodule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by WTW on 2016/8/11.
 */

public class DengluActivity extends Activity {

    public void fanhuiclick(View v){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu_layout);
    }
}

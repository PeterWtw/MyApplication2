package com.example.usermodule;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/9.
 */

public class User_Rerget extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.user_register);
        TextView textView= (TextView) findViewById(R.id.phone_user);
    }
}

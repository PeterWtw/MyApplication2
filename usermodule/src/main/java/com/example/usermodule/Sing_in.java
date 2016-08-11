package com.example.usermodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

/**
 * Created by Administrator on 2016/8/9.
 */

public class Sing_in extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.sing_in);
        findViewById(R.id.User_reg_land).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sing_in.this,User_Rerget.class);
                startActivityForResult(intent , 1000);
            }
        });
    }
}

package com.example.usermodule;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usermodule.util.TelePhoneNum;




/**
 * Created by Administrator on 2016/8/9.
 */

public class Passworld_Forget extends Activity {
    EditText editText;
    private  void indate(){
        editText = (EditText) findViewById(R.id.phone_num);

        findViewById(R.id.Verification_Code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= editText.getText().toString();
                if (!TelePhoneNum.isMobile(msg)){
                    Toast.makeText(Passworld_Forget.this,"请输入正确的电话号码",Toast.LENGTH_SHORT).show();;
                }else {

                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.passworld_forget);
        indate();

    }

}

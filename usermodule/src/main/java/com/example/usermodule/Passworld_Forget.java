package com.example.usermodule;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/8/9.
 */

public class Passworld_Forget extends Activity {
    EditText editText,Text_code;
    Button Ver_Code,Get_Code;
    String msg,wold;
    Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.obj.equals(0)){
                Ver_Code.setText("重新获取");
                Ver_Code.setEnabled(true);
            }else{
                Ver_Code.setText(msg.obj+"秒之后重新获取");
            }
        }
    };
    public void myclick(View v){
        finish();
    }
    private  void indate(){
        editText = (EditText) findViewById(R.id.phone_num);
        Text_code= (EditText) findViewById(R.id.text_code);
        Get_Code= (Button) findViewById(R.id.get_Code);
        Ver_Code= (Button) findViewById(R.id.Verification_Code);
        Get_Code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg= editText.getText().toString();
                wold=Text_code.getText().toString();
                if(msg.equals("")){
                    Toast.makeText(Passworld_Forget.this,"请输入电话号码",Toast.LENGTH_SHORT).show();
                }else if(! msg.matches("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$")){
                    Toast.makeText(Passworld_Forget.this,"请输入正确的电话号码",Toast.LENGTH_SHORT).show();
                }else if (wold.equals("")){
                    Toast.makeText(Passworld_Forget.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Ver_Code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 msg= editText.getText().toString();
                if (! msg.matches("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$")){
                    Toast.makeText(Passworld_Forget.this,"请输入正确的电话号码",Toast.LENGTH_SHORT).show();
                }else {
                    Ver_Code.setEnabled(false);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i=30;i>=0;i--){
                                Message msg =  new Message();
                                msg.obj = i;
                                timeHandler.sendMessage(msg);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }).start();

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passworld_forget);
        indate();
    }
}

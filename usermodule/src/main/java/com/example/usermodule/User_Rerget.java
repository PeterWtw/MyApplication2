package com.example.usermodule;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usermodule.util.Http_util;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/9.
 */

public class User_Rerget extends Activity {
    EditText Num_text,Code_text,Pass_text,Pass_text2;
    TextView Tiao_kuan;
    Button  Button_2,Button_1;
    CheckBox Box_click;
    String Phone, pass,pass2,cod;
    public void myclick(View v){
        finish();
    }
    Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.obj.equals(0)){
                Button_1.setText("重新获取");
                Button_1.setEnabled(true);
            }else{
                Button_1.setText(msg.obj+"秒之后重新获取");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        indate();
    }
    private void indate(){
        Num_text= (EditText) findViewById(R.id.num_text);
        Code_text= (EditText) findViewById(R.id.code_text);
        Pass_text= (EditText) findViewById(R.id.pass_text);
        Pass_text2= (EditText) findViewById(R.id.pass_text2);
        Tiao_kuan= (TextView) findViewById(R.id.tiao_kuan);
        Button_1= (Button) findViewById(R.id.button_1);
        Button_2= (Button) findViewById(R.id.button_2);
        Box_click= (CheckBox) findViewById(R.id.box_click);

      Button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phone= Num_text.getText().toString();
                cod=Code_text.getText().toString();
                pass= Pass_text.getText().toString();
                pass2= Pass_text2.getText().toString();
                RequestParams Rq=new RequestParams(Http_util.Code+"/checkCode"+"?phone="+Phone);
                x.http().get(Rq, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(User_Rerget.this,"核对成功",Toast.LENGTH_SHORT).show();

                        Log.e("Tac",result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
             if(Phone.equals("")){
                 Toast.makeText(User_Rerget.this,"输入电话号码",Toast.LENGTH_SHORT).show();
             }else if (!Phone.matches("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$")){
                 Toast.makeText(User_Rerget.this, "手机号码不合法", Toast.LENGTH_SHORT).show();
                }else if (cod.equals("")){
                    Toast.makeText(User_Rerget.this, "输入验证码", Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(User_Rerget.this, "输入密码", Toast.LENGTH_SHORT).show();
                }else if (pass2.equals("")){
                    Toast.makeText(User_Rerget.this, "再输入密码", Toast.LENGTH_SHORT).show();
                }else if (!pass.equals(pass2)){
                    Toast.makeText(User_Rerget.this, "密码不一致", Toast.LENGTH_SHORT).show();
                }else if (Box_click.isChecked()==false){
                    Toast.makeText(User_Rerget.this, "请同意服务条款", Toast.LENGTH_SHORT).show();
                }
            }
        });
       Button_1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Phone= Num_text.getText().toString();
               if (!Phone.matches("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$")){
                   Toast.makeText(User_Rerget.this, "手机号码不合法", Toast.LENGTH_SHORT).show();
                   return;
               }else {
                   Button_1.setEnabled(false);
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

                   //获取验证码接口
                   RequestParams rp=new RequestParams(Http_util.Code+"?phone="+Phone);
                   Log.e("TAG","rp_yzm"+rp);
                   x.http().get(rp, new Callback.CommonCallback<String>() {
                       @Override
                       public void onSuccess(String result) {
                           Toast.makeText(User_Rerget.this,"发送成功",Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void onError(Throwable ex, boolean isOnCallback) {

                       }

                       @Override
                       public void onCancelled(CancelledException cex) {

                       }

                       @Override
                       public void onFinished() {

                       }

                   });


               }
           }


        });
    }
}

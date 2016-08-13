package com.example.usermodule;

/**
 * Created by andi_liang on 2016/8/10.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Deng_activity extends Activity{
    EditText Edt_login_phone,Edt_login_password;
    Button Btn_login,Btn_register,Btn_forget_password;
    String phone,pass;
    public void userclick(View v){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deng_layout);
        indata();
    }
    private  void  indata(){
        Edt_login_phone= (EditText) findViewById(R.id.edt_login_phone);
        Edt_login_password= (EditText) findViewById(R.id.edt_login_password);
        //登录
        Btn_login= (Button) findViewById(R.id.btn_login);
        //忘记密码
        Btn_forget_password= (Button) findViewById(R.id.btn_forget_password);
        Btn_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Deng_activity.this,Passworld_Forget.class);
                startActivity(intent);
            }
        });
        //用户注册
        Btn_register= (Button) findViewById(R.id.btn_register);
        Btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Deng_activity.this,User_Rerget.class);
                startActivity(it);
            }
        });
        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               phone= Edt_login_phone.getText().toString();
               pass=Edt_login_password.getText().toString();
                if (phone.equals("")){
                    Toast.makeText(Deng_activity.this,"请输入手机号码",Toast.LENGTH_SHORT).show();
                }else if (!phone.matches("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$")){
                    Toast.makeText(Deng_activity.this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(Deng_activity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}

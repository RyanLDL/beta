package com.example.beta1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beta1.Data.DataLab;
import com.example.beta1.Data.UserBean;
import com.example.beta1.R;

public class RegisterActivity extends AppCompatActivity {
    private Button button,getbtn;
    private TextView phonenum,pwd,yzm, Code;
    String a,b,c,d;
    UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        getSupportActionBar().hide();//
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//去除标题栏
        setContentView(R.layout.activity_register);
        button = findViewById(R.id.registerbtn);
        phonenum = findViewById(R.id.phonenum);
        pwd = findViewById(R.id.pwd);
        yzm = findViewById(R.id.yzm);
        Code = findViewById(R.id.code);
        getbtn = findViewById(R.id.getbtn);
        MyTime myTime=new MyTime(60000,1000);

        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTime.start();
                d = code();
                Code.setText(d);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = phonenum.getText().toString();
                b = pwd.getText().toString();
                c = yzm.getText().toString();
                if (phonenumIsTrue(a) & pwdIsTrue(b) & yzmIsTrue(c)){
                    DataLab.getInstance().getUserBean().userinfo.put(a,b);
                    Intent intent = new Intent(RegisterActivity.this,Login.class);
                    intent.putExtra("uid",a);
                    intent.putExtra("upwd",b);
                    startActivity(intent);
                }
            }
        });

    }
    public String code(){
        String sum = "";
        for (int i = 0; i < 6; i++) {
            int random = (int)(Math.random()*10);
            sum +=random+"";
        }
        return sum;
    }
    public Boolean phonenumIsTrue(String str){
        if (a == null || a.length() != 11){
            Toast.makeText(getApplicationContext(),"请输入正确手机号码",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public Boolean pwdIsTrue(String str){
        if (b == null || b.length() < 8){
            Toast.makeText(getApplicationContext(),"请输入至少8位密码",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public Boolean yzmIsTrue(String str){
        if (c.equals(d)){
            return true;
        }
        Toast.makeText(getApplicationContext(),"请输入正确的验证码",Toast.LENGTH_LONG).show();
        return false;
    }
    private class MyTime extends CountDownTimer{

        public MyTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onTick(long l) {
            getbtn.setBackgroundColor(R.color.white);
            getbtn.setClickable(false);
            getbtn.setText(l/1000+"秒");
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onFinish() {
            getbtn.setBackgroundColor(Color.parseColor("#54D159"));
            getbtn.setText("重新获取验证码");
            getbtn.setClickable(true);
        }
    }
}
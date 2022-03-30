package com.example.beta1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beta1.Data.DataLab;
import com.example.beta1.R;

public class Login extends AppCompatActivity {
    private Button loginBtn,RegisterBtn,fgbtn;
    private TextView userId,userPwd;
    String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        getSupportActionBar().hide();//
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//去除标题栏
        setContentView(R.layout.activity_login);
        loginBtn=findViewById(R.id.LoginBtn);
        RegisterBtn = findViewById(R.id.RegisterBtn);
        userId = findViewById(R.id.UserIdText);
        userPwd = findViewById(R.id.UserPwdText);
        fgbtn = findViewById(R.id.forget);
        Intent intent =getIntent();
        userId.setText(intent.getStringExtra("uid"));
        userPwd.setText(intent.getStringExtra("upwd"));
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userId.getText().toString();
                String pwd = userPwd.getText().toString();
                Log.d(TAG, "onClick: "+DataLab.getInstance().getUserBean().userinfo);
                if (DataLab.getInstance().getUserBean().userinfo.containsKey(id)){
                    String truePwd = DataLab.getInstance().getUserBean().userinfo.get(id).toString();
                    if (truePwd.equals(pwd)){
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"账号不存在，请注册",Toast.LENGTH_LONG).show();
                }
            }
        });
        fgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,changeActivity.class);
                if (DataLab.getInstance().getUserBean().userinfo.containsKey(userId.getText().toString())){
                    intent.putExtra("id",userId.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"不存在该账号，请注册",Toast.LENGTH_LONG);
                }

            }
        });
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(Login.this,RegisterActivity.class);
            startActivity(intent);
            }
        });
    }
}
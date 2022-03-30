package com.example.beta1.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import com.example.beta1.R;

import java.util.Timer;
import java.util.TimerTask;

import site.gemus.openingstartanimation.NormalDrawStrategy;
import site.gemus.openingstartanimation.OpeningStartAnimation;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        getSupportActionBar().hide();//
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//去除标题栏
        setContentView(R.layout.activity_welcom);

     OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(this)
                .setDrawStategy(new NormalDrawStrategy())
                .setAppName("归园")
                .setAppIcon(getDrawable(R.drawable.pic))
                .setColorOfAppName(R.color.appname)
                .setAppStatement("让心灵回归田园")
                .setAnimationFinishTime(1300)
                .create();
        openingStartAnimation.show(this);
        TimerTask task=new TimerTask() {//计时器
            @Override
            public void run() {
                Intent intent=new Intent(WelcomActivity.this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        Timer timer=new Timer();
        timer.schedule(task,1300);
    }
}
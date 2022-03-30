package com.example.beta1.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.beta1.R;
import com.example.beta1.cache.PreloadManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(new String[]{Manifest.permission.INTERNET,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        getSupportActionBar().hide();//
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//去除标题栏
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView,BlankFragment.class,null)
                            .commit();
                    return true;
                case R.id.publish:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView,PublishFragment.class,null)
                            .commit();
                    return true;
                case R.id.mine:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView,MineFragment.class,null)
                            .commit();
                    return true;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    public void finish() {
        super.finish();
        PreloadManager.getInstance(getApplicationContext()).removeAllPreloadTask();
    }
}
package com.example.beta1.view;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beta1.Adapter.Adapter;
import com.example.beta1.R;
import com.google.android.material.navigation.NavigationView;


public class MineFragment extends Fragment {
    private static final String TAG = "TAG";
    private View rootview;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;//滑动菜单
    private Adapter adapter;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_mine,container,false);
        }
//        initView();
        init();
        return rootview;
    }

//    private void initView() {
//        recyclerView = rootview.findViewById(R.id.rv);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
//        adapter.setOnItemClickListenner(new Adapter.OnItemClickListenner() {
//            @Override
////            public void onItemClick(View view, int position) {
////                Intent
////            }
//        });
//    }

    private void init() {
        drawerLayout = rootview.findViewById(R.id.draw);
        drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        navigationView = rootview.findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.first:
                    Log.d(TAG, "init: 我的");
                    break;
                case R.id.second:
                    Log.d(TAG, "init: 历史");
                    break;
                case R.id.third:
                    Log.d(TAG, "init: 客服");
                    break;
                case R.id.forth:
                    Log.d(TAG, "init: 设置");
                    break;
                default:
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.END);//关闭菜单
            return true;

        });
    }

}

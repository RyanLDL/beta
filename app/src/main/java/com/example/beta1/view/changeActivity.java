package com.example.beta1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beta1.Data.DataLab;
import com.example.beta1.R;

public class changeActivity extends AppCompatActivity {
    private TextView t1,t2,t3;
    private Button btn;
    private static final String TAG="TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        t1 = findViewById(R.id.id);
        t2 = findViewById(R.id.pwd1);
        t3 = findViewById(R.id.pwd2);
        btn = findViewById(R.id.confirmation);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("id");
        t1.setText(uid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataLab.getInstance().getUserBean().userinfo.containsKey(t1.getText().toString())){
                    if (t2.getText().toString().equals(t3.getText().toString())){
                        DataLab.getInstance().getUserBean().userinfo.put(t1.getText().toString(),t2.getText().toString());
                        Log.d(TAG, "onClick: "+DataLab.getInstance().getUserBean().userinfo.get(t1.getText().toString()));
                        Intent intent1 =new Intent(changeActivity.this,Login.class);
                        intent1.putExtra("uid",t1.getText().toString());
                        intent1.putExtra("upwd",t2.getText().toString());
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"两次输入密码不同，请重新输入",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
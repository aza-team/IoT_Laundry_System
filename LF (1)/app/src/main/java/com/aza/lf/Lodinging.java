package com.aza.lf;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.os.SystemClock.sleep;

public class Lodinging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodinging);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* 메뉴액티비티를 실행하고 로딩화면을 죽인다.*/
                Intent HomeIntent = new Intent(Lodinging.this, HomeActivity.class);
                Lodinging.this.startActivity(HomeIntent);
                Lodinging.this.finish();
            }
        }, 10000);
    }

    }

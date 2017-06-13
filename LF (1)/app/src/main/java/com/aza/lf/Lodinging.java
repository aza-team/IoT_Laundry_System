package com.aza.lf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.os.SystemClock.sleep;

public class Lodinging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodinging);

        try{
            sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Intent HomeIntent = new Intent(Lodinging.this, HomeActivity.class);
        Lodinging.this.startActivity(HomeIntent);
        return;
    }
}

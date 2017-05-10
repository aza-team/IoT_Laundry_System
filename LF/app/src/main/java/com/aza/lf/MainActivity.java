package com.aza.lf;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bt_alarm = (Button) findViewById(R.id.bt_alarm);
        final Button bt_more = (Button) findViewById(R.id.bt_more);

        final Button bt_home = (Button) findViewById(R.id.bt_home);
        final Button bt_reserve = (Button) findViewById(R.id.bt_reserve);
        final Button bt_mine = (Button) findViewById(R.id.bt_mine);

        final LinearLayout menu_bar = (LinearLayout) findViewById(R.id.menu_bar);
        final LinearLayout notice = (LinearLayout) findViewById(R.id.notice);
        final LinearLayout under_bar = (LinearLayout) findViewById(R.id.under_bar);

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_bar.setVisibility(View.GONE);
                notice.setVisibility(View.GONE);
                under_bar.setVisibility(View.GONE);
                bt_home.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                bt_reserve.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bt_mine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment, new HomeFragment());
                fragmentTransaction.commit();
            }

        });

        bt_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_bar.setVisibility(View.GONE);
                notice.setVisibility(View.GONE);
                under_bar.setVisibility(View.GONE);
                bt_home.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bt_reserve.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                bt_mine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment, new ReserveFragment());
                fragmentTransaction.commit();
            }

        });

        bt_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_bar.setVisibility(View.GONE);
                notice.setVisibility(View.GONE);
                under_bar.setVisibility(View.GONE);
                bt_home.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bt_reserve.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bt_mine.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment, new MineFragment());
                fragmentTransaction.commit();
            }

        });
    }
}

package com.aza.lf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ReserveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        final Button bt_alarm = (Button) findViewById(R.id.bt_alarm);
        final Button bt_more = (Button) findViewById(R.id.bt_more);

        final Button bt_home = (Button) findViewById(R.id.bt_home);
        final Button bt_reserve = (Button) findViewById(R.id.bt_reserve);
        final Button bt_mine = (Button) findViewById(R.id.bt_mine);

        final LinearLayout menu_bar = (LinearLayout) findViewById(R.id.menu_bar);
        final LinearLayout under_bar = (LinearLayout) findViewById(R.id.under_bar);

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomeIntent = new Intent(ReserveActivity.this, HomeActivity.class);
                ReserveActivity.this.startActivity(HomeIntent);
            }
        });

        bt_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ReserveIntent = new Intent(ReserveActivity.this, ReserveActivity.class);
                ReserveActivity.this.startActivity(ReserveIntent);
            }
        });

        bt_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MineIntent = new Intent(ReserveActivity.this, MineActivity.class);
                ReserveActivity.this.startActivity(MineIntent);
            }
        });
    }


}

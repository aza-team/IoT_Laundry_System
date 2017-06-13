package com.aza.lf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ReserveActivity extends AppCompatActivity {
    DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        // ActionBar에 타이틀 변경
        getSupportActionBar().setTitle("예약하기");
        //ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF17375E));
        ImageView iv = (ImageView)findViewById(R.id.IV);
        TextView tv1 = (TextView)findViewById(R.id.TV1);
        TextView tv2 = (TextView)findViewById(R.id.TV2);
        TextView tv3 = (TextView)findViewById(R.id.TV3);
        TextView tv4 = (TextView)findViewById(R.id.TV4);
        ImageButton IB = (ImageButton)findViewById(R.id.IB);

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
        bt_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MineIntent = new Intent(ReserveActivity.this, MineActivity.class);
                ReserveActivity.this.startActivity(MineIntent);
            }
        });
        dao = new DAO();
        String result;
        String result2;

        Intent intent = getIntent();
        final int laundryno;
        laundryno = intent.getIntExtra("laundryno",0);
        result = dao.getstate(laundryno+"");
        result2 = dao.getreservation(laundryno+"");
        String state = "";
        SharedPreferences SH = getSharedPreferences("Sh",MODE_PRIVATE);
        final String id = SH.getString("id","");
        Log.i("id!!!!!!",id+"");
             if(result.equals("0")){
                iv.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.use_ok));
                state = "대기중";
                 IB.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                        dao.setmylaundryno(id,laundryno+"");
                         Toast.makeText(ReserveActivity.this, "내세탁기에 추가되었습니다. 인증을 거친 후 사용하시기 바랍니다.", Toast.LENGTH_SHORT).show();
                         finish();
                     }
                 });
            }else if (result.equals("1")){
                 if(result2.equals("0")) {
                     iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rsv_ok_dewater));
                     state = "세탁중";
                     IB.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dao.setmylaundryno(id,laundryno+"");
                             dao.setreservation(id,laundryno+"");
                             Toast.makeText(ReserveActivity.this, "내세탁기에 추가되었습니다. 현 사용자의 세탁종료후 사용하시기 바랍니다.", Toast.LENGTH_SHORT).show();

                         }
                     });

                 }else{
                     iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rsv_no_wash));
                     state = "세탁중";
                     IB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.disable));
                     IB.setOnClickListener(null);
                 }
            }else if (result.equals("2")){
                 if(result2.equals("0")) {
                     iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rsv_ok_dewater));
                     state = "탈수중";
                     IB.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             dao.setmylaundryno(id,laundryno+"");
                             dao.setreservation(id,laundryno+"");
                             Toast.makeText(ReserveActivity.this, "내세탁기에 추가되었습니다. 현 사용자의 세탁종료후 사용하시기 바랍니다.", Toast.LENGTH_SHORT).show();

                         }
                     });
                 }else{
                     iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rsv_no_dewater));
                     state = "탈수중";
                     IB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.disable));
                     IB.setOnClickListener(null);
                 }
            }


        String temp = "번호 : "+"<u>"+(laundryno+1)+"</u>";
        tv1.setText(Html.fromHtml(temp));
        temp = "상태 : "+state;
        tv2.setText(temp);
        temp = "시간 : " + "not setting";
        tv3.setText(temp);
        temp = "참고사항 : " +"not setting";
        tv4.setText(temp);

    }
}

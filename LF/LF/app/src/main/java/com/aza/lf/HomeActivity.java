package com.aza.lf;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.attr.button;


public class HomeActivity extends ActionBarActivity{
    //private DrawerLayout drawer_menu;
    boolean bLog = false; // false : 로그아웃 상태


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ActionBar에 타이틀 변경
        getSupportActionBar().setTitle("TEST");
        */
        //ActionBar의 배경색 변경
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));

        final Button bt_alarm = (Button) findViewById(R.id.bt_alarm);
        final Button bt_more = (Button) findViewById(R.id.bt_more);

        final Button bt_home = (Button) findViewById(R.id.bt_home);
        final Button bt_reserve = (Button) findViewById(R.id.bt_reserve);
        final Button bt_mine = (Button) findViewById(R.id.bt_mine);

        final LinearLayout under_bar = (LinearLayout) findViewById(R.id.under_bar);

        //하단메뉴 클릭 시 화면전환
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomeIntent = new Intent(HomeActivity.this, HomeActivity.class);
                HomeActivity.this.startActivity(HomeIntent);
            }
        });
        bt_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ReserveIntent = new Intent(HomeActivity.this, ReserveActivity.class);
                HomeActivity.this.startActivity(ReserveIntent);
            }
        });
        bt_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MineIntent = new Intent(HomeActivity.this, MineActivity.class);
                HomeActivity.this.startActivity(MineIntent);
            }
        });
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // 옵션메뉴가 화면에 보여질때 마다 호출됨
        Log.d("test", "onPrepareOptionsMenu");
        if(bLog){ // 로그인 한 상태: 로그인은 안보이게, 로그아웃은 보이게
            menu.getItem(1).setEnabled(true);
            menu.getItem(2).setEnabled(false);
        }else{ // 로그 아웃 한 상태 : 로그인 보이게, 로그아웃은 안보이게
            menu.getItem(1).setEnabled(false);
            menu.getItem(2).setEnabled(true);
        }
        bLog = !bLog;   // 값을 반대로 바꿈

        return super.onPrepareOptionsMenu(menu);
    }

    // 메뉴를 눌렀을 때 이벤트 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 메뉴의 항목을 선택(클릭)했을 때 호출되는 콜백메서드
        Log.d("test", "onOptionsItemSelected");

        int id = item.getItemId();
        switch(id) {
            case R.id.it_push:
                Toast.makeText(getApplicationContext(), "푸시알림", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_login:
                Toast.makeText(getApplicationContext(), "로그인", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_logout:
                Toast.makeText(getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.it_sort:
                Toast.makeText(getApplicationContext(), "정렬하기", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //옵션 메뉴를 만든다. (액션바 + 메뉴)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴버튼이 처음 눌러졌을 때 실행되는 콜백메서드
        // 메뉴버튼을 눌렀을 때 보여줄 menu 에 대해서 정의
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        Log.d("test", "onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

}

package com.aza.lf;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
    ListView listview;
    ListViewAdapter adapter;
    SwipeRefreshLayout SRlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ActionBar에 타이틀 변경
        getSupportActionBar().setTitle("LF");

        //ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF17375E));

        final Button bt_alarm = (Button) findViewById(R.id.bt_alarm);
        final Button bt_more = (Button) findViewById(R.id.bt_more);

        final Button bt_home = (Button) findViewById(R.id.bt_home);
        final Button bt_reserve = (Button) findViewById(R.id.bt_reserve);
        final Button bt_mine = (Button) findViewById(R.id.bt_mine);

        final LinearLayout under_bar = (LinearLayout) findViewById(R.id.under_bar);

/*
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Laundry_MENU) ;
        ListView listview = (ListView) findViewById(R.id.lv_laundry_menu) ;
        listview.setAdapter(adapter) ;
*/
        //하단메뉴 클릭 시 화면전환
        bt_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MineIntent = new Intent(HomeActivity.this, MineActivity.class);
                HomeActivity.this.startActivity(MineIntent);
            }
        });




        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.lv_laundry_menu);
        listview.setAdapter(adapter);


        /*
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.use_ok),"1번","경과시간 : " + "분");
        */
        SRlayout = (SwipeRefreshLayout)findViewById(R.id.list);
        SRlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.deleteItem();
                //새로고침 코드 입력
                for(int i =0;i<=4; i++){
                    int num = i;
                    String state;
                    String result = "";
                    String result2 = "";
                    DAO dao = new DAO();
                    result = dao.getstate(""+num);
                    result2 = dao.getreservation(""+num);
                    if(result.equals("0")){
                        adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.use_ok),
                                (i+1)+"번","경과시간 : 분");
                    }else if (result.equals("1")){
                        if(result2.equals("0")) {
                            adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rsv_ok_wash),
                                    (i+1) + "번", "경과시간 : 분");
                        }else{
                            adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rsv_no_wash),
                                    (i+1)+"번","경과시간 : 분");
                        }
                    }else if (result.equals("2")){
                        if(result2.equals("0")) {
                            adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rsv_ok_dewater),
                                    (i+1) + "번", "경과시간 : 분");
                        }else{
                            adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rsv_no_dewater),
                                    (i+1)+"번","경과시간 : 분");

                        }
                    }

                   adapter.notifyDataSetChanged();
                }
                //새로고침 완료
                SRlayout.setRefreshing(false);
            }
        });
        for(int i = 0; i<=4 ; i++){
            int num = i;
            int count = adapter.getCount();
            String state;
            String result = "";
            String result2 = "";
            DAO dao = new DAO();
            result = dao.getstate(""+num);
            result2 = dao.getreservation(""+num);


            if(result.equals("0")){
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.use_ok),
                        (count+1)+"번","경과시간 : 분");
            }else if (result.equals("1")){
                if(result2.equals("0")) {
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rsv_ok_wash),
                            (count + 1) + "번", "경과시간 : 분");
                }
                else{
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rsv_no_wash),
                            (count+1)+"번","경과시간 : 분");
                }
            }else if (result.equals("2")){
                if(result2.equals("0")) {
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rsv_ok_dewater),
                            (count + 1) + "번", "경과시간 : 분");
                }
                else{
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rsv_no_dewater),
                            (count+1)+"번","경과시간 : 분");
                }
            }
        }



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(HomeActivity.this,ReserveActivity.class);
                intent.putExtra("laundryno",position);
                HomeActivity.this.startActivity(intent);
            }
        });
/*
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.use_ok),
                "1번", "경과시간 : "+"분") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rsv_no_wash),
                "2번", "경과시간 : "+"분") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rsv_ok_dewater),
                "3번", "경과시간 : "+"분") ;
*/



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
        menu.getItem(0).setEnabled(true);
        menu.getItem(3).setEnabled(true);
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

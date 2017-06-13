package com.aza.lf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class MineActivity extends AppCompatActivity {
    ImageView bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ActionBar에 타이틀 변경
        getSupportActionBar().setTitle("내세탁기");
        //ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF17375E));

        setContentView(R.layout.activity_mine);
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        final ImageView bt = (ImageView) findViewById(R.id.certification);
        String s1 = "ㆍ 세탁실 내의 와이파이와 연결";
        SpannableStringBuilder builder = new SpannableStringBuilder(s1);
        builder.setSpan(new ForegroundColorSpan(Color.RED), 9, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Button bt_home = (Button)findViewById(R.id.bt_home);
        Button bt_reserve = (Button)findViewById(R.id.bt_reserve);
        Button bt_mine = (Button)findViewById(R.id.bt_mine);
        //하단메뉴 클릭 시 화면전환
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomeIntent = new Intent(MineActivity.this, HomeActivity.class);
                MineActivity.this.startActivity(HomeIntent);
            }
        });

        tv1.append(builder);
        String s2 = "ㆍ 사용시, ";
        String s3 = "사용자인증";
        String s4 = " 버튼을 클릭";
        tv2.setText(Html.fromHtml(s2 + "<u>" + s3 + "</u>" + s4));

        String s5 = "ㆍ 예약을 취소할 시,";
        String s6 = "예약취소";
        String s7 = " 클릭";

        tv3.setText(Html.fromHtml(s5 + "<u>" + s6 + "</u>" + s7));

        final TextView tv = (TextView) findViewById(R.id.cencle);
        //예약취소버튼을 눌렀을때
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setBackgroundResource(R.drawable.cencle2);
                /*
                예약취소하였을때 구성할것
                 */
            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String certification = "";
                DAO dao = new DAO();
                SharedPreferences SH = getSharedPreferences("Sh", MODE_PRIVATE);
                String laundryno = new DAO().mylaundry(SH.getString("id", "0"));
                certification = dao.getCertification(SH.getString("id","0")); // (매개변수)번 세탁기의 인증여부 가져오기 1: 인증 0: 인증x
                if (certification.equals("1")) {
                    Toast.makeText(MineActivity.this, "이미 인증된 세탁기 입니다.", Toast.LENGTH_SHORT).show();
                } else {

                    //네트워크로 통한 사용자 인증
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    Log.i("cat", "이까지성공");
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    Log.i("cat2", "여기부터실패");
                    String Currentmac;
                    String yjp_guest = "ec:8c:a2:1f:d6:ec";
                    String yjp_guset2 = "d8:38:fc:22:90:a8";
                    String yjp_guest3 = "d8:38:fc:22:90:ac";
                    String myHome = "64:e5:99:37:86:c2";
                    String bean = "00:26:66:86:24:b8";
                    String hollys = "88:36:6c:21:17:d2";

                    int m_iNetworkType = activeNetwork.getType();
                    //BSSID로 구분
                    if (m_iNetworkType == cm.TYPE_WIFI) {
                        Currentmac = wifiManager.getConnectionInfo().getBSSID();
                        Log.i("현재BSSID", Currentmac);
                        Log.i("내부BSSID", yjp_guest);

                        if (Currentmac.trim().equals(yjp_guest) || Currentmac.trim().equals(yjp_guset2)
                                || Currentmac.trim().equals(yjp_guest3) || Currentmac.trim().equals(myHome)
                                || Currentmac.trim().equals(bean) || Currentmac.trim().equals(hollys)) {

                            //네트워크 인증되었을때 발생하는 이벤트

                            new DAO().certificationDAO(SH.getString("id","0"), "1");//첫번째 내 아이디, 두번째 0: 인증x 1:인증확인
                            Toast.makeText(MineActivity.this, "certification suecces", Toast.LENGTH_SHORT).show();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        sleep(2000);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            bt.setBackgroundResource(R.drawable.ing);
                                        }
                                    });
                                    try {
                                        sleep(2000);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            bt.setBackgroundResource(R.drawable.spindry);
                                        }
                                    });
                                    try {
                                        sleep(2000);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            bt.setBackgroundResource(R.drawable.finish);
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            Toast.makeText(MineActivity.this, "YJP-Guest에 연결해주세요", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MineActivity.this, "wifi is not connecting", Toast.LENGTH_SHORT).show();
                        Log.i("fail", "네트워크 인증 실패");

                        //네트워크 인증 실패했을때 발생하는 이벤트

                    }
                }
            }
        });
    }

    private void time() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.currentTimeMillis();

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



}

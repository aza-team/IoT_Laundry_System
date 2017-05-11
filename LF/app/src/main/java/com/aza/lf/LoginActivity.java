package com.aza.lf;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    private String UserPassword;
    private String userPassword;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText et_id = (EditText) findViewById(R.id.et_id);
        final EditText et_pw = (EditText) findViewById(R.id.et_pw);

        Button bt_member = (Button) findViewById(R.id.bt_member);
        Button bt_login = (Button) findViewById(R.id.bt_login);
        //회원가입버튼클릭시
        bt_member.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent MemberIntent = new Intent(LoginActivity.this, MemberActivity.class);
                LoginActivity.this.startActivity(MemberIntent);
            }
        });
        //회원가입버튼클릭시;
        //로그인버튼클릭시
        bt_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String result="";
                try{
                    loginCheck task1 = new loginCheck();
                    result = task1.execute(et_id.getText().toString(),et_pw.getText().toString()).get();
                }catch (Exception e){
                    Log.i("통신결과 에러","a");
                    e.printStackTrace();
                }
                if(result.equals("1")){
                    //로그인 성공시 넘어가는 페이지
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("로그인성공하였습니다.!").setPositiveButton("확인",null).create();
                    dialog.show();
                    return;
                }
                else if(result.equals("2")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("password를 확인해주세요").setPositiveButton("확인",null).create();
                    dialog.show();
                    return;
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("아이디를 확인해주세요").setPositiveButton("확인",null).create();
                    dialog.show();
                    return;
                }
            }
        });
        //로그인버튼클릭시;
    }
}
class loginCheck extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;



    @Override
    protected String doInBackground(String[] strings) {
        try{
            String str;
            sendMsg = "id=" + strings[0]+"&pwd="+strings[1];
            URL url = new URL("http://172.20.5.73:8080/cat/login.jsp?" + sendMsg);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type","application/x-www-from-urlencoded");
            conn.setRequestMethod("GET");
            if(conn.getResponseCode()==conn.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"EUC-KR");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while((str = reader.readLine()) !=null){
                    buffer.append(str);
                }
                receiveMsg=buffer.toString();
            }else{
                Log.i("통신결과", conn.getResponseCode()+"에러");
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return receiveMsg;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}


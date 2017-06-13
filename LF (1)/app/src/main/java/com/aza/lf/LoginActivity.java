package com.aza.lf;

import android.content.Intent;
import android.content.SharedPreferences;
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
import java.io.OutputStream;
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
        /*
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                LoginActivity.this.startActivity(HomeIntent);
            }
        });
        */
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
                    //로그인 성공시 이벤트 처리
                    SharedPreferences SH = getSharedPreferences("Sh", MODE_PRIVATE);
                    SharedPreferences.Editor editor = SH.edit();
                    editor.putString("id",et_id.getText().toString());
                    editor.putString("password",et_pw.getText().toString());
                    editor.commit();

                    Intent HomeIntent = new Intent(LoginActivity.this, Lodinging.class);
                    LoginActivity.this.startActivity(HomeIntent);
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
        try {
            sendMsg = "id="+strings[0] + "&pwd=" + strings[1];
            URL url = new URL("http://58.237.71.218:8080/cat/login.jsp");
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Context-type","application/x-www-form-urlencoded");
            urlConnection.setRequestMethod("POST");
            OutputStream opstrm = urlConnection.getOutputStream();
            opstrm.write(sendMsg.getBytes());
            opstrm.flush();
            opstrm.close();
            String buffer = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while((buffer = in.readLine()) != null){
                receiveMsg = buffer;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}


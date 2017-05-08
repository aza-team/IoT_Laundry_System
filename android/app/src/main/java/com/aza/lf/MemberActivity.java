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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MemberActivity extends AppCompatActivity {

    private String userID;
    private String userPassword;
    private String userPhone;
    private String userEmail;
    private AlertDialog dialog;
    private boolean validate = false;
    private String 중복체크="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        final EditText et_id = (EditText) findViewById(R.id.et_id);
        final EditText et_pw = (EditText) findViewById(R.id.et_pw);
        final EditText et_phone = (EditText) findViewById(R.id.et_phone);
        final EditText et_email = (EditText) findViewById(R.id.et_email);
        final Button bt_validate = (Button) findViewById(R.id.bt_validate);

        //취소버튼클릭
        Button bt_mem_no=(Button)findViewById(R.id.bt_mem_no);
        bt_mem_no.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent LoginIntent = new Intent(MemberActivity.this,LoginActivity.class);
                MemberActivity.this.startActivity(LoginIntent);
                Log.i("a","good");
            }
        });
        //취소버튼클릭;
        //중복버튼
        bt_validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String result="";
                try{
                    idCheck task1 = new idCheck();
                    result = task1.execute(et_id.getText().toString()).get();
                }catch (Exception e){
                    Log.i("통신결과 에러","a");
                    e.printStackTrace();
                }

                //아이디확인
                if(result.equals("2")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                    dialog = builder.setMessage("사용 가능한 아이디입니다.").setPositiveButton("확인",null).create();
                    중복체크 = et_id.getText().toString();
                    dialog.show();
                    validate = true;
                    return;
                }
                else if(result.equals("0")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인",null).create();
                    dialog.show();
                    validate = false;
                    return;
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                    dialog = builder.setMessage("이미 사용중인 아이디입니다.").setPositiveButton("확인",null).create();
                    dialog.show();
                    validate = false;
                    return;
                }
            }
        });
        //중복버튼;
        //확인버튼
        Button bt_mem_ok = (Button)findViewById(R.id.bt_mem_ok);
        bt_mem_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String result="";
                if(validate==false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                    dialog = builder.setMessage("중복체크를 확인해주세요.").setPositiveButton("확인",null).create();
                    dialog.show();
                    validate = false;
                    return;
                }
                else if(!중복체크.equals(et_id.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                    dialog = builder.setMessage("중복체크를 확인해주세요.").setPositiveButton("확인",null).create();
                    dialog.show();
                    validate = false;
                    return;
                }
                else{

                try{
                    memberCheck task = new memberCheck();
                    result = task.execute(et_id.getText().toString(),et_pw.getText().toString(),et_phone.getText().toString(),et_email.getText().toString()).get();
                }catch (Exception e){
                    Log.i("통신결과 에러","a");
                    e.printStackTrace();
                }

                //아이디확인
                if(validate==false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                    dialog = builder.setMessage("중복체크를 확인해주세요.").setPositiveButton("확인",null).create();
                    dialog.show();
                    validate = false;
                    return;
                }
                else if (validate ==true) {
                    if (중복체크.equals(et_id.getText())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                        dialog = builder.setMessage("중복체크를 확인해주세요.").setPositiveButton("확인", null).create();
                        dialog.show();
                        validate = false;
                        return;
                    } else if (result.equals("2")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                        dialog = builder.setMessage("password를 확인해주세요.").setPositiveButton("확인", null).create();
                        dialog.show();
                        return;
                    } else if (result.equals("3")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                        dialog = builder.setMessage("핸드폰번호를 확인해주세요.").setPositiveButton("확인", null).create();
                        dialog.show();
                        return;
                    } else if (result.equals("4")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                        dialog = builder.setMessage("이메일을 확인해주세요.").setPositiveButton("확인", null).create();
                        dialog.show();
                        return;
                    } else {
                        Intent LoginIntent = new Intent(MemberActivity.this, LoginActivity.class);
                        MemberActivity.this.startActivity(LoginIntent);
                    }
                }
            }
            }
        });
        //확인버튼;
    }


}

class memberCheck extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;



    @Override
    protected String doInBackground(String[] strings) {
        try{
            String str;
            sendMsg = "id=" + strings[0]+"&pwd="+strings[1]+"&phone="+strings[2]+"&email="+strings[3];
            URL url = new URL("http://172.30.1.37:8080/cat/test.jsp?" + sendMsg);
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

class idCheck extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;



    @Override
    protected String doInBackground(String[] strings) {
        try{
            String str;
            sendMsg = "id=" + strings[0];
            URL url = new URL("http://172.30.1.37:8080/cat/idcheck.jsp?" + sendMsg);
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
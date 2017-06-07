package com.aza.lf;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kmnii on 2017-06-04.
 */

public class DAO {
    String Id;
    String No;
    String Certification;
    public DAO(){

    }

    public String getCertification(String no){
        No = no;
        String result = "";

        Gcertifi gcertifi = new Gcertifi();
        try {
            result = gcertifi.execute(No).get();
            Log.i("인증2",gcertifi.execute(No).get());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ""+result;
    }

    class Gcertifi extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String[] strings) {

            try{
                sendMsg = "no="+strings[0];
                URL url = new URL("http://58.237.71.218:8080/cat/getCertification.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while((buffer = in.readLine()) != null){
                    receiveMsg = buffer;
                }
                in.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return receiveMsg;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }


    public void certificationDAO(String no, String certification) {

        No = no;
        Certification = certification;

        Certifi certifi = new Certifi();
        certifi.execute(No, Certification);
        Log.i("test", No);
        Log.i("test", Certification);
    }

        class Certifi extends AsyncTask<String, Void, String> {

            String sendMsg, receiveMsg;



            @Override
            protected String doInBackground(String[] strings) {

                try{
                    sendMsg = "no="+strings[0] + "&certification="+strings[1];
                    URL url = new URL("http://58.237.71.218:8080/cat/usercertification.jsp?");
                    HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                    urlconnection.setDoInput(true);
                    urlconnection.setDoOutput(true);
                    urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    urlconnection.setRequestMethod("POST");
                    OutputStream opstrm = urlconnection.getOutputStream();
                    opstrm.write(sendMsg.getBytes());
                    opstrm.flush();
                    opstrm.close();
                    String buffer = null;
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                    while((buffer = in.readLine()) != null){
                        receiveMsg = buffer;
                    }
                    in.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                return receiveMsg;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }
        }

    public String mylaundry(String id) {
        Id = id;
        String result = "";
        Mylaundry mylaundry = new Mylaundry();
        try {
            result = mylaundry.execute(Id).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    class Mylaundry extends AsyncTask<String, Void, String>{
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String[] strings) {

            try{
                sendMsg = "id="+strings[0];
                URL url = new URL("http://58.237.71.218:8080/cat/getMylaundry.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while((buffer = in.readLine()) != null){
                    receiveMsg = buffer;
                }
                in.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return receiveMsg;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}

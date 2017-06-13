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

    public DAO() {

    }

    // 사용자 테이블의 인증 여부 가져옴 0: 인증 x 1 : 인증 o
    //*변경사항* 세탁기 테이블의 인증여부를 가져왔었지만 사용자 테이블로 변경함
    public String getCertification(String id) {
        Id = id;
        String result = "";

        Gcertifi gcertifi = new Gcertifi();
        try {
            result = gcertifi.execute(Id).get();
            Log.i("인증2", gcertifi.execute(Id).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + result;
    }

    class Gcertifi extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "id=" + strings[0];
                URL url = new URL("http://58.237.71.218:8080/cat/getCertification.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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

    // 인증되었을 시 인증 값 테이블에 넣어주는 역할
// *변경사항* 인증되었을 시 사용자 아이디를 받아 certification을 1로채울지 0으로 채울지 정함
    public void certificationDAO(String id, String certification) {

        Id = id;

        Certification = certification;

        Certifi certifi = new Certifi();
        certifi.execute(Id, Certification);
        Log.i("test", Id);
        Log.i("test", Certification);
    }

    class Certifi extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;


        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "id=" + strings[0] + "&certification=" + strings[1];
                URL url = new URL("http://58.237.71.218:8080/cat/usercertification.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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

    // 사용자테이블의 내 세탁기 번호를 가져오도록 하는 메서드
    public String mylaundry(String id) {
        Id = id;
        String result = "";
        Mylaundry mylaundry = new Mylaundry();
        try {
            result = mylaundry.execute(Id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    class Mylaundry extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "id=" + strings[0];
                URL url = new URL("http://58.237.71.218:8080/cat/getMylaundry.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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

    // 세탁기의 상태를 가져오도록 하는 메소드 0:대기 1:세탁 2:탈수
    public String getstate(String no) {
        String result = "";
        Getstate gs = new Getstate();
        try {
            result = gs.execute(no).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    class Getstate extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "no=" + strings[0];
                URL url = new URL("http://58.237.71.218:8080/cat/getstate.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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

    // 세탁기의 예약 활성화여부를 가져오는 메서드 0 : 예약x 1: 예약대기중
    public String getreservation(String no) {
        String result = "";
        Getreservation gs = new Getreservation();
        try {
            result = gs.execute(no).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    class Getreservation extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "no=" + strings[0];
                URL url = new URL("http://58.237.71.218:8080/cat/getreservation.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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
    // 예약시 사용자 테이블에 mylaundryno update
    // 동시에 세탁테이블의 userid에 id추가
    public void setmylaundryno(String id, String laundryno) {

        setmylaundryno set= new setmylaundryno();
        set.execute(id,laundryno);

        setuserid set1 = new setuserid();
        set1.execute(id,laundryno);
    }


    class setmylaundryno extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;


        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "id=" + strings[0] + "&mylaundry=" + strings[1];
                URL url = new URL("http://58.237.71.218:8080/cat/setmylaundry.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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
    //setuserid
    class setuserid extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;


        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "id=" + strings[0] + "&no=" + strings[1];
                URL url = new URL("http://58.237.71.218:8080/cat/setuserid.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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

    // 사용중인 세탁기를 예약할때
    public void setreservation(String id, String laundryno) {

        setmylaundryno set1 =  new setmylaundryno();
        set1.execute(id, laundryno);
        reservation reser = new reservation();
        reser.execute(laundryno,"1");
        reservationid id1 = new reservationid();
        id1.execute(laundryno,id);
    }

    class reservation extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;


        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "id=" + strings[0] + "&flag=" + strings[1];
                URL url = new URL("http://58.237.71.218:8080/cat/setreservation.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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
    class reservationid extends AsyncTask<String, Void, String> {

        String sendMsg, receiveMsg;


        @Override
        protected String doInBackground(String[] strings) {

            try {
                sendMsg = "no=" + strings[0] + "&id=" + strings[1];
                URL url = new URL("http://58.237.71.218:8080/cat/setreservationid.jsp?");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setDoInput(true);
                urlconnection.setDoOutput(true);
                urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlconnection.setRequestMethod("POST");
                OutputStream opstrm = urlconnection.getOutputStream();
                opstrm.write(sendMsg.getBytes());
                opstrm.flush();
                opstrm.close();
                String buffer = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                while ((buffer = in.readLine()) != null) {
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

}

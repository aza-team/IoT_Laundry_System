package com.aza.lf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
    public DBManager(Context context){
        super(context,"myDB",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table members (name text, sex text, sms text, interest text;");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
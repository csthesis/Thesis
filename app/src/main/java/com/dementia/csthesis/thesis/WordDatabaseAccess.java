package com.dementia.csthesis.thesis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class WordDatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static WordDatabaseAccess instance;
    Cursor c = null;

    private WordDatabaseAccess(Context context){
        this.openHelper = new WordDatabaseOpenHelper(context);
    }

    public static WordDatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new WordDatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getReadableDatabase();

    }

    public void close(){
        if(db != null){
            this.db.close();
        }
    }

    public String getData(String id, String table){
        Cursor res;

        String[] x = {};
        res = db.rawQuery("SELECT FULL FROM "+table+" WHERE ID = '"+id+"'",x);
        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            String full = res.getString(0);
            buffer.append(""+full);
        }

        return buffer.toString();

    }

    public Bitmap getImg(String id, String table){
        Cursor res;

        res = db.rawQuery("SELECT IMG FROM " +table+ " WHERE ID = '" +id+ "'",null);

        if(res.moveToFirst()){
            byte[] imgByte = res.getBlob(0);
            res.close();
            return BitmapFactory.decodeByteArray(imgByte, 0 , imgByte.length);
        }
        if(res != null && res.isClosed()){
            res.close();
        }

        return null;


    }

}

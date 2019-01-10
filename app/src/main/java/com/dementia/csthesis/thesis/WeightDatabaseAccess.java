package com.dementia.csthesis.thesis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class WeightDatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static WeightDatabaseAccess instance;
    Cursor c = null;

    public static final String TABLE_NAME = "WEIGHTS_TABLE";
    public static final String TABLE_ID = "ID";

    public static final String AUDIO = "AUDIO";
    public static final String VISUAL = "VISUAL";
    public static final String WORD = "WORD";
    public static final String LOGIC = "LOGIC";
    public static final String MEMORY = "MEMORY";
    public static final String REFLEX = "REFLEX";

    private WeightDatabaseAccess(Context context){
        this.openHelper = new WeightDatabaseOpenHelper(context);
    }

    public static WeightDatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new WeightDatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();

    }

    public void close(){
        if(db != null){
            this.db.close();
        }
    }

    public Float getWeight(String column){
        Cursor res;

        Float x = 0f;

        res = db.rawQuery("SELECT "+column+" FROM WEIGHTS_TABLE WHERE ID = 1", null);

        if(res.moveToFirst()){
            x = res.getFloat(0);
        }
        return x;

    }

    public void deleteWeight(){
        ContentValues cv = new ContentValues();
        cv.put(TABLE_ID, 1);
        cv.put(AUDIO, 0);
        cv.put(WORD, 0);
        cv.put(LOGIC, 0);
        cv.put(MEMORY, 0);
        cv.put(REFLEX, 0);

        db.update(TABLE_NAME, cv, TABLE_ID + "= 1", null);
    }

    public void insertWeight(String column, float weight){
        ContentValues cv = new ContentValues();
        cv.put(column, weight);
        db.update(TABLE_NAME, cv, TABLE_ID + "= 1", null);
    }


    public Float getgameweight(String column, String table){
        Cursor res;

        Float x = 0f;

        res = db.rawQuery("SELECT "+column+" FROM "+table+" WHERE ID = 1", null);

        if(res.moveToFirst()){
            x = res.getFloat(0);
        }
        return x;

    }


    public void insertgameweight(String column, float weight, String table){
        ContentValues cv = new ContentValues();
        cv.put(column, weight);
        db.update(table, cv, TABLE_ID + "= 1", null);
    }
}

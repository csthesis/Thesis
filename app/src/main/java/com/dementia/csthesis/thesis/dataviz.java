package com.dementia.csthesis.thesis;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class dataviz extends SQLiteAssetHelper{

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "wordgame.db";


    public dataviz(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

}

package com.dementia.csthesis.thesis;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class WordDatabaseOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "wordGuess.db";
    private static final int VERSION = 1;

    public WordDatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
}

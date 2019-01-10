package com.dementia.csthesis.thesis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.UserDictionary;

public class datavizword {
    private SQLiteOpenHelper datavizz;
    private SQLiteDatabase db;
    private static datavizword inst;
    Cursor c = null;

    private datavizword(Context context) {
        this.datavizz = new dataviz(context);
    }

    public static datavizword getInstance(Context context) {
        if (inst == null) {
            inst = new datavizword(context);
        }
        return inst;
    }

    public void open() {
        this.db = datavizz.getReadableDatabase();

    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public String getData(String id,String Col) {
        Cursor res;

        String[] x = {};
        res = db.rawQuery("SELECT "+Col+" FROM Words WHERE ID = '" + id +"'", x);
        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {
            String full = res.getString(0);
            buffer.append("" + full);
        }

        return buffer.toString();

    }

    public Bitmap getImg(String id) {
        Cursor res;

        res = db.rawQuery("SELECT Image FROM Words WHERE ID = '" + id + "'", null);

        if (res.moveToFirst()) {
            byte[] imgByte = res.getBlob(0);
            res.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (res != null && res.isClosed()) {
            res.close();
        }

        return null;
    }
}
package com.example.studenthelpdesk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private  static final String DATABASE_NAME="STUDENT_HELP_DESK";
    static final int DATABASE_VERSION=1;
    static final String TABLE_NAME="USERS";
    static final String KEY_ID="id";
    static final String KEY_EMAIL="email";
    static final String KEY_PASSWORD="password";


    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_EMAIL + " TEXT, " + KEY_PASSWORD +" Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
